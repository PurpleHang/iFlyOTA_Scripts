package com.iflytek.study.ota.core;

public enum AiStudyDevice {
    CK10AIR("F1VDORD8", "ud710_ck10air_native", "CK10AIR", AiStudyDeviceType.C, "7ce2d548", "7ce2d548", "AISTUDY_CK10AIR", "学习机CK1.0 AIR，基于T710硬件平台"),
    CK10("ZHIDBIXU", "ums9620_ck10_native", "CK10", AiStudyDeviceType.C, "a6f4ecc2", "a6f4ecc2", "AISTUDY_CK10", "学习机CK1.0"),
    CTG3AIR("PMR6UOP3", "ud710_ctg30air_native", "CTG3AIR", AiStudyDeviceType.C, "7e61fe97", "7e61fe97", "AISTUDY_CTG3AIR", "学习机CTG3.0 AIR，基于T710硬件平台"),
    CTG3("IFDHMXUD", "ctg3", "ctg3", AiStudyDeviceType.C, "7473ec19", "7473ec19", "AISTUDY_CTG3", "T20 Pro，基于RK3588硬件平台"),
    LENOVO_5G("PWZC9PEP", "Lenovo TB-J607Z", "Lenovo TB-J607Z", AiStudyDeviceType.C, "9c3fa256", "9c3fa256", "AISTUDY_H150201", "学习平板5G版本"),
    CTG2_0("U860R2TC", "CB-CTG2.0", "CB-CTG2.0", AiStudyDeviceType.C, "826b3cd3", "826b3cd3", AiStudyConstants.MODEL_ID_CTG_2_0, "T10/CTG2.0，基于T710硬件平台"),
    X1("PWZC9PEP", "TYE100", "CB-X1", AiStudyDeviceType.C, "5d979ff4", "5d979ff4", AiStudyConstants.MODEL_ID, "X1Pro，基于高通平台"),
    Z1("AYR70ZD9", "CB-Z1", "CB-Z1", AiStudyDeviceType.C, "71952b43", "71952b43", AiStudyConstants.MODEL_ID_Z1, "Z1"),
    X2("7UXZ2129", "CB-X2", "CB-X2", AiStudyDeviceType.C, "4605f293", "4605f293", AiStudyConstants.MODEL_ID_X2PRO, "X2，对外是X2Pro"),
    Q1("SS6DKVB6", "CB-Q1", "CB-Q1", AiStudyDeviceType.C, "0616c063", "0616c063", AiStudyConstants.MODEL_ID_Q1, "Q1，对外是X2"),
    Q10("L5NU63MF", "CB-Q10", "CB-Q10", AiStudyDeviceType.C, "3f745f53", "3f745f53", AiStudyConstants.MODEL_ID_X2_MINI, "Q10，也叫X2Mini"),
    Q20("2LWHVDOQ", "Q20", "Q20", AiStudyDeviceType.C, "013113cf", "013113cf", "AISTUDY_Q20", "对外型号为A10"),
    TX20_A10_AIHome("PWZC9PEP", "TX20-A10-AIHome", "TX20-A10-AIHome", AiStudyDeviceType.C, "noneed", "noneed", "AISTUDY_TX20_A10_AIHome", "TX20-A10-AIHome"),
    CB30("PWZC9PEP", "CB30", "CB30", AiStudyDeviceType.C, "4d4b9df0", "4d4b9df0", "AISTUDY_CB30", "CB30"),
    TX20_Q20("PWZC9PEP", "TX20-Q20", "TX20-Q20", AiStudyDeviceType.C, "30b920a4", "30b920a4", "AISTUDY_TX20_Q20", "TX20_Q20, 新的Q20，原有的Q20发布时更名为了A10"),
    X2ProPlus("UCVDG1G1", "CB-X2ProPlus", "CB-X2ProPlus", AiStudyDeviceType.C, "d6357bfc", "d6357bfc", AiStudyConstants.MODEL_ID_X2PRO_PLUS, "X2ProPlus，废弃"),
    AFTER_CLASS("7KVKB4N6", "CB-X2-AfterClass", "CB-X2-AfterClass", AiStudyDeviceType.AFTER_CLASS, "b706728d", "b706728d", "AISTUDY_X2_AFTER_CLASS", "课后服务，基于X2PRO修改"),
    AFTER_CLASS_Q1("7KVKB4N6", "CB-Q1-AfterClass", "CB-Q1-AfterClass", AiStudyDeviceType.AFTER_CLASS, "f1fa2645", "f1fa2645", "AISTUDY_Q1_AFTER_CLASS", "课后服务，Q1，对外是X2"),
    CITY_100("B1AZ4XX2", "CB-Q1-100CITY", "CB-Q1-100CITY", AiStudyDeviceType.CITY_100, "9ae58863", "9ae58863", AiStudyConstants.MODEL_ID_Q1_100CITY, "百城计划，基于X2修改"),
    B_X1("PWZC9PEP", "TYE100", "CB-X1", AiStudyDeviceType.BYOD, "e2fb4e38", "e2fb4e38", AiStudyConstants.MODEL_ID_X1PRO_BYOD_MIDDLE, "BYOD X1Pro"),
    C6STU("PWZC9PEP", "CB-C6-STU", "CB-C6-STU", AiStudyDeviceType.BYOD, "0616c063", "0616c063", AiStudyConstants.MODEL_ID_CBC6STU, "BYOD C6 学生机, X2"),
    C6ET("PWZC9PEP", "CB-Q1-ETOrg", "CB-Q1-ETOrg", AiStudyDeviceType.BYOD, "0616c063", "0616c063", "AISTUDY_ETQRG_Q1", "BYOD C6 教培版, X2"),
    C6PLUSSTU("PWZC9PEP", "CB-C6PLUS-STU", "CB-C6PLUS-STU", AiStudyDeviceType.BYOD, "71952b43", "71952b43", AiStudyConstants.MODEL_ID_CBC6PLUSSTU, "BYOD C6 学生高配机, Z1"),
    C6PLUSTCH("PWZC9PEP", "CB-C6PLUS-TCH", "CB-C6PLUS-TCH", AiStudyDeviceType.BYOD, "71952b43", "71952b43", AiStudyConstants.MODEL_ID_CBC6PLUSTCH, "BYOD C6 教师机, Z1"),
    C6MAXTCH("PWZC9PEP", "CB-C6MAX-TCH", "CB-C6MAX-TCH", AiStudyDeviceType.BYOD, "71952b43", "71952b43", AiStudyConstants.MODEL_ID_CBC6MAXTCH, "BYOD C6 高配教师机, Z1"),
    C8STU("PWZC9PEP", "CB-C8-STU", "CB-C8-STU", AiStudyDeviceType.BYOD, "ceacc557", "ceacc557", "AISTUDY_STU_C8", "BYOD C8 学生机, X2ProPlus"),
    C8PLUSSTU("PWZC9PEP", "CB-C8PLUS-STU", "CB-C8PLUS-STU", AiStudyDeviceType.BYOD, "446bb5e5", "446bb5e5", "AISTUDY_STU_C8PLUS", "BYOD C8 高配学生机"),
    C8PLUSTCH("PWZC9PEP", "CB-C8PLUS-TCH", "CB-C8PLUS-TCH", AiStudyDeviceType.BYOD, "446bb5e5", "446bb5e5", "AISTUDY_TCH_C8PLUS", "BYOD C8 教师机"),
    EINK_BYOD("PWZC9PEP", "iFlytekAiNoteT2", "iFlytekAiNoteT2", AiStudyDeviceType.EINK, "KTCPX", "XXB", "ZK-E-HAN", "Eink BYOD 课堂AI自习本"),
    EINK_AINOTEBOOK("PWZC9PEP", "AIStudyAINoteBook1.0", "AIStudyAINoteBook1.0", AiStudyDeviceType.EINK, "5996dcf7", "5996dcf7", "AISTUDY_AIStudyAINoteBook1.0", "Eink T2 理科自习本");

    private static AiStudyDevice sAiStudyDevice;
    private final String mAppId;
    private final String mBizId;
    private final String mDesc;
    private final String mHardwareModel;
    private final String mModel;
    private final String mModelId;
    private final String mProductId;
    private final AiStudyDeviceType mType;
    private static final AiStudyDevice DEFAULT_DEVICE = B_X1;

    AiStudyDevice(String str, String str1, String str2, AiStudyDeviceType aiStudyDeviceType, String str3, String str4, String str5, String str6) {
        this.mAppId = str;
        this.mHardwareModel = str;
        this.mModel = str2;
        this.mType = aiStudyDeviceType;
        this.mProductId = str3;
        this.mBizId = str4;
        this.mModelId = str5;
        this.mDesc = str6;
    }

    public String getAppId() {
        return this.mAppId;
    }

    public String getModel() {
        return this.mModel;
    }

    public AiStudyDeviceType getType() {
        return this.mType;
    }

    public String getProductId() {
        return this.mProductId;
    }

    public String getBizId() {
        return this.mBizId;
    }

    public String getModelId() {
        return this.mModelId;
    }

    public String getDesc() {
        return this.mDesc;
    }

    public static AiStudyDevice getDeviceById(int id) {
        if(values().length >= id) {
            return values()[id];
        } else {
            return DEFAULT_DEVICE;
        }
    }
}