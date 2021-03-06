package com.huawei.device.connectivitychrlog;

public class CSegEVENT_WIFI_POOR_LEVEL extends ChrLogBaseEventModel {
    public ENCEventId enEventId = new ENCEventId();
    public LogInt iRssi2g0LevelCnt = new LogInt();
    public LogInt iRssi2g1LevelCnt = new LogInt();
    public LogInt iRssi2g2LevelCnt = new LogInt();
    public LogInt iRssi2g3LevelCnt = new LogInt();
    public LogInt iRssi2g4LevelCnt = new LogInt();
    public LogInt iRssi5g0LevelCnt = new LogInt();
    public LogInt iRssi5g1LevelCnt = new LogInt();
    public LogInt iRssi5g2LevelCnt = new LogInt();
    public LogInt iRssi5g3LevelCnt = new LogInt();
    public LogInt iRssi5g4LevelCnt = new LogInt();
    public LogDate tmTimeStamp = new LogDate(6);
    public LogByte ucCardIndex = new LogByte();
    public LogByte ucRssi2g0LevelAvg = new LogByte();
    public LogByte ucRssi2g1LevelAvg = new LogByte();
    public LogByte ucRssi2g2LevelAvg = new LogByte();
    public LogByte ucRssi2g3LevelAvg = new LogByte();
    public LogByte ucRssi2g4LevelAvg = new LogByte();
    public LogByte ucRssi2gMaxRssi = new LogByte();
    public LogByte ucRssi2gMinRssi = new LogByte();
    public LogByte ucRssi5g0LevelAvg = new LogByte();
    public LogByte ucRssi5g1LevelAvg = new LogByte();
    public LogByte ucRssi5g2LevelAvg = new LogByte();
    public LogByte ucRssi5g3LevelAvg = new LogByte();
    public LogByte ucRssi5g4LevelAvg = new LogByte();
    public LogByte ucRssi5gMaxRssi = new LogByte();
    public LogByte ucRssi5gMinRssi = new LogByte();
    public LogShort usLen = new LogShort();

    public CSegEVENT_WIFI_POOR_LEVEL() {
        this.lengthMap.put("enEventId", Integer.valueOf(1));
        this.fieldMap.put("enEventId", this.enEventId);
        this.lengthMap.put("usLen", Integer.valueOf(2));
        this.fieldMap.put("usLen", this.usLen);
        this.lengthMap.put("tmTimeStamp", Integer.valueOf(6));
        this.fieldMap.put("tmTimeStamp", this.tmTimeStamp);
        this.lengthMap.put("ucCardIndex", Integer.valueOf(1));
        this.fieldMap.put("ucCardIndex", this.ucCardIndex);
        this.lengthMap.put("ucRssi2gMaxRssi", Integer.valueOf(1));
        this.fieldMap.put("ucRssi2gMaxRssi", this.ucRssi2gMaxRssi);
        this.lengthMap.put("ucRssi2gMinRssi", Integer.valueOf(1));
        this.fieldMap.put("ucRssi2gMinRssi", this.ucRssi2gMinRssi);
        this.lengthMap.put("ucRssi2g0LevelAvg", Integer.valueOf(1));
        this.fieldMap.put("ucRssi2g0LevelAvg", this.ucRssi2g0LevelAvg);
        this.lengthMap.put("ucRssi2g1LevelAvg", Integer.valueOf(1));
        this.fieldMap.put("ucRssi2g1LevelAvg", this.ucRssi2g1LevelAvg);
        this.lengthMap.put("ucRssi2g2LevelAvg", Integer.valueOf(1));
        this.fieldMap.put("ucRssi2g2LevelAvg", this.ucRssi2g2LevelAvg);
        this.lengthMap.put("ucRssi2g3LevelAvg", Integer.valueOf(1));
        this.fieldMap.put("ucRssi2g3LevelAvg", this.ucRssi2g3LevelAvg);
        this.lengthMap.put("ucRssi2g4LevelAvg", Integer.valueOf(1));
        this.fieldMap.put("ucRssi2g4LevelAvg", this.ucRssi2g4LevelAvg);
        this.lengthMap.put("iRssi2g0LevelCnt", Integer.valueOf(4));
        this.fieldMap.put("iRssi2g0LevelCnt", this.iRssi2g0LevelCnt);
        this.lengthMap.put("iRssi2g1LevelCnt", Integer.valueOf(4));
        this.fieldMap.put("iRssi2g1LevelCnt", this.iRssi2g1LevelCnt);
        this.lengthMap.put("iRssi2g2LevelCnt", Integer.valueOf(4));
        this.fieldMap.put("iRssi2g2LevelCnt", this.iRssi2g2LevelCnt);
        this.lengthMap.put("iRssi2g3LevelCnt", Integer.valueOf(4));
        this.fieldMap.put("iRssi2g3LevelCnt", this.iRssi2g3LevelCnt);
        this.lengthMap.put("iRssi2g4LevelCnt", Integer.valueOf(4));
        this.fieldMap.put("iRssi2g4LevelCnt", this.iRssi2g4LevelCnt);
        this.lengthMap.put("ucRssi5gMaxRssi", Integer.valueOf(1));
        this.fieldMap.put("ucRssi5gMaxRssi", this.ucRssi5gMaxRssi);
        this.lengthMap.put("ucRssi5gMinRssi", Integer.valueOf(1));
        this.fieldMap.put("ucRssi5gMinRssi", this.ucRssi5gMinRssi);
        this.lengthMap.put("ucRssi5g0LevelAvg", Integer.valueOf(1));
        this.fieldMap.put("ucRssi5g0LevelAvg", this.ucRssi5g0LevelAvg);
        this.lengthMap.put("ucRssi5g1LevelAvg", Integer.valueOf(1));
        this.fieldMap.put("ucRssi5g1LevelAvg", this.ucRssi5g1LevelAvg);
        this.lengthMap.put("ucRssi5g2LevelAvg", Integer.valueOf(1));
        this.fieldMap.put("ucRssi5g2LevelAvg", this.ucRssi5g2LevelAvg);
        this.lengthMap.put("ucRssi5g3LevelAvg", Integer.valueOf(1));
        this.fieldMap.put("ucRssi5g3LevelAvg", this.ucRssi5g3LevelAvg);
        this.lengthMap.put("ucRssi5g4LevelAvg", Integer.valueOf(1));
        this.fieldMap.put("ucRssi5g4LevelAvg", this.ucRssi5g4LevelAvg);
        this.lengthMap.put("iRssi5g0LevelCnt", Integer.valueOf(4));
        this.fieldMap.put("iRssi5g0LevelCnt", this.iRssi5g0LevelCnt);
        this.lengthMap.put("iRssi5g1LevelCnt", Integer.valueOf(4));
        this.fieldMap.put("iRssi5g1LevelCnt", this.iRssi5g1LevelCnt);
        this.lengthMap.put("iRssi5g2LevelCnt", Integer.valueOf(4));
        this.fieldMap.put("iRssi5g2LevelCnt", this.iRssi5g2LevelCnt);
        this.lengthMap.put("iRssi5g3LevelCnt", Integer.valueOf(4));
        this.fieldMap.put("iRssi5g3LevelCnt", this.iRssi5g3LevelCnt);
        this.lengthMap.put("iRssi5g4LevelCnt", Integer.valueOf(4));
        this.fieldMap.put("iRssi5g4LevelCnt", this.iRssi5g4LevelCnt);
        this.enEventId.setValue("WIFI_POOR_LEVEL");
        this.usLen.setValue(getTotalLen());
    }
}
