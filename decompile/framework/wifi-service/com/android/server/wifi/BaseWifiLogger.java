package com.android.server.wifi;

import java.io.FileDescriptor;
import java.io.PrintWriter;

public class BaseWifiLogger {
    protected String mDriverVersion;
    protected String mFirmwareVersion;
    protected int mSupportedFeatureSet;

    public synchronized void startLogging(boolean verboseEnabled) {
        WifiNative wifiNative = WifiNative.getWlanNativeInterface();
        this.mFirmwareVersion = wifiNative.getFirmwareVersion();
        this.mDriverVersion = wifiNative.getDriverVersion();
        this.mSupportedFeatureSet = wifiNative.getSupportedLoggerFeatureSet();
    }

    public synchronized void startPacketLog() {
    }

    public synchronized void stopPacketLog() {
    }

    public synchronized void stopLogging() {
    }

    synchronized void reportConnectionFailure() {
    }

    public synchronized void captureBugReportData(int reason) {
    }

    public synchronized void captureAlertData(int errorCode, byte[] alertData) {
    }

    public synchronized void dump(FileDescriptor fd, PrintWriter pw, String[] args) {
        dump(pw);
        pw.println("*** firmware logging disabled, no debug data ****");
        pw.println("set config_wifi_enable_wifi_firmware_debugging to enable");
    }

    protected synchronized void dump(PrintWriter pw) {
        pw.println("Chipset information :-----------------------------------------------");
        pw.println("FW Version is: " + this.mFirmwareVersion);
        pw.println("Driver Version is: " + this.mDriverVersion);
        pw.println("Supported Feature set: " + this.mSupportedFeatureSet);
    }
}
