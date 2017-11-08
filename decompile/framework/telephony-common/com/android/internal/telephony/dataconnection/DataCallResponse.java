package com.android.internal.telephony.dataconnection;

import android.net.LinkAddress;
import android.net.LinkProperties;
import android.net.NetworkUtils;
import android.net.RouteInfo;
import android.os.SystemProperties;
import android.telephony.Rlog;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class DataCallResponse {
    private final boolean DBG = true;
    private final String LOG_TAG = "DataCallResponse";
    public int active = 0;
    public String[] addresses = new String[0];
    public int cid = 0;
    public String[] dnses = new String[0];
    public String[] gateways = new String[0];
    public String ifname = "";
    public int mtu = 0;
    public String[] pcscf = new String[0];
    public int status = 0;
    public int suggestedRetryTime = -1;
    public String type = "";
    public int version = 0;

    public enum SetupResult {
        SUCCESS,
        ERR_BadCommand,
        ERR_UnacceptableParameter,
        ERR_GetLastErrorFromRil,
        ERR_Stale,
        ERR_RilError;
        
        public DcFailCause mFailCause;

        public String toString() {
            return name() + "  SetupResult.mFailCause=" + this.mFailCause;
        }
    }

    public String toString() {
        int i = 0;
        StringBuffer sb = new StringBuffer();
        sb.append("DataCallResponse: {").append("version=").append(this.version).append(" status=").append(this.status).append(" retry=").append(this.suggestedRetryTime).append(" cid=").append(this.cid).append(" active=").append(this.active).append(" type=").append(this.type).append(" ifname=").append(this.ifname).append(" mtu=").append(this.mtu).append(" addresses=[*");
        sb.append("] dnses=[");
        for (String addr : this.dnses) {
            sb.append(addr);
            sb.append(",");
        }
        if (this.dnses.length > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("] gateways=[*");
        sb.append("] pcscf=[");
        String[] strArr = this.pcscf;
        int length = strArr.length;
        while (i < length) {
            sb.append(strArr[i]);
            sb.append(",");
            i++;
        }
        if (this.pcscf.length > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]}");
        return sb.toString();
    }

    public SetupResult setLinkProperties(LinkProperties linkProperties, boolean okToUseSystemPropertyDns, int subId) {
        String addr;
        String dnsAddr;
        SetupResult result;
        if (linkProperties == null) {
            linkProperties = new LinkProperties();
        } else {
            linkProperties.clear();
        }
        if (this.status == DcFailCause.NONE.getErrorCode()) {
            String propertyPrefix = "net." + this.ifname + ".";
            linkProperties.setInterfaceName(this.ifname);
            if (this.addresses == null || this.addresses.length <= 0) {
                throw new UnknownHostException("no address for ifname=" + this.ifname);
            }
            InetAddress ia;
            for (String addr2 : this.addresses) {
                addr2 = addr2.trim();
                if (!addr2.isEmpty()) {
                    int addrPrefixLen;
                    String[] ap = addr2.split("/");
                    if (ap.length == 2) {
                        addr2 = ap[0];
                        addrPrefixLen = Integer.parseInt(ap[1]);
                    } else {
                        addrPrefixLen = 0;
                    }
                    try {
                        ia = NetworkUtils.numericToInetAddress(addr2);
                        if (ia.isAnyLocalAddress()) {
                            continue;
                        } else {
                            if (addrPrefixLen == 0) {
                                addrPrefixLen = ia instanceof Inet4Address ? 32 : 128;
                            }
                            Rlog.d("DataCallResponse", "addr/pl=* ");
                            linkProperties.addLinkAddress(new LinkAddress(ia, addrPrefixLen));
                        }
                    } catch (IllegalArgumentException e) {
                        throw new UnknownHostException("Non-numeric gateway addr=" + addr2);
                    } catch (IllegalArgumentException e2) {
                        throw new UnknownHostException("Non-numeric dns addr=" + dnsAddr);
                    } catch (IllegalArgumentException e3) {
                        throw new UnknownHostException("Non-numeric dns addr=" + addr2);
                    } catch (IllegalArgumentException e4) {
                        throw new UnknownHostException("Bad parameter for LinkAddress, ia=" + ia.getHostAddress() + "/" + addrPrefixLen);
                    } catch (IllegalArgumentException e5) {
                        throw new UnknownHostException("Non-numeric ip addr=" + addr2);
                    } catch (UnknownHostException e6) {
                        Rlog.d("DataCallResponse", "setLinkProperties: UnknownHostException " + e6);
                        e6.printStackTrace();
                        result = SetupResult.ERR_UnacceptableParameter;
                    }
                }
            }
            if (this.dnses != null && this.dnses.length > 0) {
                for (String addr22 : this.dnses) {
                    addr22 = addr22.trim();
                    if (!addr22.isEmpty()) {
                        ia = NetworkUtils.numericToInetAddress(addr22);
                        if (!ia.isAnyLocalAddress()) {
                            linkProperties.addDnsServer(ia);
                            if (okToUseSystemPropertyDns && (ia instanceof Inet4Address)) {
                                Rlog.d("DataCallResponse", "setLinkProperties: set system property in " + subId + " dns: " + addr22);
                                SystemProperties.set("persist.radio.dns.sub" + subId, addr22);
                                okToUseSystemPropertyDns = false;
                            }
                        }
                    }
                }
            } else if (okToUseSystemPropertyDns) {
                String[] dnsServers = new String[]{SystemProperties.get("persist.radio.dns.sub" + subId, ""), "8.8.8.8"};
                Rlog.d("DataCallResponse", "setLinkProperties: use system property in " + subId + " dns: " + dnsServers[0]);
                for (String dnsAddr2 : dnsServers) {
                    dnsAddr2 = dnsAddr2.trim();
                    if (!dnsAddr2.isEmpty()) {
                        ia = NetworkUtils.numericToInetAddress(dnsAddr2);
                        if (!ia.isAnyLocalAddress()) {
                            linkProperties.addDnsServer(ia);
                        }
                    }
                }
            } else {
                Rlog.e("DataCallResponse", "Empty dns response and no system default dns");
            }
            if (this.gateways == null || this.gateways.length == 0) {
                String sysGateways = SystemProperties.get(propertyPrefix + "gw");
                if (sysGateways != null) {
                    this.gateways = sysGateways.split(" ");
                } else {
                    this.gateways = new String[0];
                }
            }
            for (String addr222 : this.gateways) {
                addr222 = addr222.trim();
                if (!addr222.isEmpty()) {
                    linkProperties.addRoute(new RouteInfo(NetworkUtils.numericToInetAddress(addr222)));
                }
            }
            linkProperties.setMtu(this.mtu);
            result = SetupResult.SUCCESS;
        } else if (this.version < 4) {
            result = SetupResult.ERR_GetLastErrorFromRil;
        } else {
            result = SetupResult.ERR_RilError;
        }
        if (result != SetupResult.SUCCESS) {
            Rlog.d("DataCallResponse", "setLinkProperties: error clearing LinkProperties status=" + this.status + " result=" + result);
            linkProperties.clear();
        }
        return result;
    }
}
