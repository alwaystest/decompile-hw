package com.huawei.device.connectivitychrlog;

public class CSegEVENT_WIFI_WIFIPRO_DUALBAND_EXCEPTION_EVENT extends ChrLogBaseEventModel {
    public LogByteArray aucSSID_2G = new LogByteArray(32);
    public LogByteArray aucSSID_5G = new LogByteArray(32);
    public ENCDualbandExSubEvent enDualbandExSubEvent = new ENCDualbandExSubEvent();
    public ENCEventId enEventId = new ENCEventId();
    public LogDate tmTimeStamp = new LogDate(6);
    public LogByte ucCardIndex = new LogByte();
    public LogByte ucIsBluetoothConnected = new LogByte();
    public LogByte ucSingleOrMixed = new LogByte();
    public LogShort usConnectTime_2G = new LogShort();
    public LogShort usConnectTime_5G = new LogShort();
    public LogShort usHandOverErrCode = new LogShort();
    public LogShort usLen = new LogShort();
    public LogShort usLossRate_2G = new LogShort();
    public LogShort usLossRate_5G = new LogShort();
    public LogShort usRSSI_2G = new LogShort();
    public LogShort usRSSI_5G = new LogShort();
    public LogShort usRTT_2G = new LogShort();
    public LogShort usRTT_5G = new LogShort();
    public LogShort usScan_Threshod_RSSI_2G = new LogShort();
    public LogShort usScore_2G = new LogShort();
    public LogShort usScore_5G = new LogShort();
    public LogShort usTarget_RSSI_5G = new LogShort();

    public CSegEVENT_WIFI_WIFIPRO_DUALBAND_EXCEPTION_EVENT() {
        this.lengthMap.put("enEventId", Integer.valueOf(1));
        this.fieldMap.put("enEventId", this.enEventId);
        this.lengthMap.put("usLen", Integer.valueOf(2));
        this.fieldMap.put("usLen", this.usLen);
        this.lengthMap.put("tmTimeStamp", Integer.valueOf(6));
        this.fieldMap.put("tmTimeStamp", this.tmTimeStamp);
        this.lengthMap.put("ucCardIndex", Integer.valueOf(1));
        this.fieldMap.put("ucCardIndex", this.ucCardIndex);
        this.lengthMap.put("enDualbandExSubEvent", Integer.valueOf(1));
        this.fieldMap.put("enDualbandExSubEvent", this.enDualbandExSubEvent);
        this.lengthMap.put("aucSSID_2G", Integer.valueOf(32));
        this.fieldMap.put("aucSSID_2G", this.aucSSID_2G);
        this.lengthMap.put("aucSSID_5G", Integer.valueOf(32));
        this.fieldMap.put("aucSSID_5G", this.aucSSID_5G);
        this.lengthMap.put("ucSingleOrMixed", Integer.valueOf(1));
        this.fieldMap.put("ucSingleOrMixed", this.ucSingleOrMixed);
        this.lengthMap.put("usScan_Threshod_RSSI_2G", Integer.valueOf(2));
        this.fieldMap.put("usScan_Threshod_RSSI_2G", this.usScan_Threshod_RSSI_2G);
        this.lengthMap.put("usTarget_RSSI_5G", Integer.valueOf(2));
        this.fieldMap.put("usTarget_RSSI_5G", this.usTarget_RSSI_5G);
        this.lengthMap.put("usRSSI_2G", Integer.valueOf(2));
        this.fieldMap.put("usRSSI_2G", this.usRSSI_2G);
        this.lengthMap.put("usRSSI_5G", Integer.valueOf(2));
        this.fieldMap.put("usRSSI_5G", this.usRSSI_5G);
        this.lengthMap.put("usScore_2G", Integer.valueOf(2));
        this.fieldMap.put("usScore_2G", this.usScore_2G);
        this.lengthMap.put("usScore_5G", Integer.valueOf(2));
        this.fieldMap.put("usScore_5G", this.usScore_5G);
        this.lengthMap.put("usHandOverErrCode", Integer.valueOf(2));
        this.fieldMap.put("usHandOverErrCode", this.usHandOverErrCode);
        this.lengthMap.put("ucIsBluetoothConnected", Integer.valueOf(1));
        this.fieldMap.put("ucIsBluetoothConnected", this.ucIsBluetoothConnected);
        this.lengthMap.put("usRTT_2G", Integer.valueOf(2));
        this.fieldMap.put("usRTT_2G", this.usRTT_2G);
        this.lengthMap.put("usLossRate_2G", Integer.valueOf(2));
        this.fieldMap.put("usLossRate_2G", this.usLossRate_2G);
        this.lengthMap.put("usConnectTime_2G", Integer.valueOf(2));
        this.fieldMap.put("usConnectTime_2G", this.usConnectTime_2G);
        this.lengthMap.put("usRTT_5G", Integer.valueOf(2));
        this.fieldMap.put("usRTT_5G", this.usRTT_5G);
        this.lengthMap.put("usLossRate_5G", Integer.valueOf(2));
        this.fieldMap.put("usLossRate_5G", this.usLossRate_5G);
        this.lengthMap.put("usConnectTime_5G", Integer.valueOf(2));
        this.fieldMap.put("usConnectTime_5G", this.usConnectTime_5G);
        this.enEventId.setValue("WIFI_WIFIPRO_DUALBAND_EXCEPTION_EVENT");
        this.usLen.setValue(getTotalLen());
    }
}
