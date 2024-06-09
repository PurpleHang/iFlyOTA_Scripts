package com.iflytek.study.ota.modules;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.iflytek.study.ota.DirtyApiClient;
import com.iflytek.study.ota.Logger;
import com.iflytek.study.ota.core.AiStudyDevice;
import com.iflytek.study.ota.utils.AESUtils;
import com.iflytek.study.ota.utils.Base64Utils;
import com.iflytek.study.ota.utils.GzipUtils;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class QueryRecord {

    public static final String ACCESS_KEY = "bba93cde";
    public static final String ACCESS_KEY_SECRET = "15d8e25cb9d6b73bf6bbb9dc417d82c";

    @SerializedName("base")
    private Base base;

    // Getter 和 Setter
    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    // 嵌套的Base类
    public static class Base {

        @SerializedName("romver")
        private String romver;

        @SerializedName("bizid")
        private String bizid;

        @SerializedName("appid")
        private String appid;

        @SerializedName("versionname")
        private String versionname;

        @SerializedName("versioncode")
        private int versioncode;

        @SerializedName("imei")
        private String imei;

        @SerializedName("sn")
        private String sn;

        @SerializedName("userid")
        private String userid;

        @SerializedName("familyid")
        private String familyid;

        @SerializedName("childid")
        private String childid;

        @SerializedName("mac")
        private String mac;

        @SerializedName("androidid")
        private String androidid;

        @SerializedName("longitude")
        private Double longitude;

        @SerializedName("latitude")
        private Double latitude;

        @SerializedName("pos")
        private Object pos; // 由于pos的值在JSON中是null，这里可以使用Object或具体类型（如果知道的话）

        @SerializedName("city")
        private Object city; // 同上，city的值也是null

        @SerializedName("modelid")
        private String modelid;

        @SerializedName("chipid")
        private String chipid;

        @SerializedName("traceId")
        private String traceId;

        @SerializedName("timestamp")
        private String timestamp;

        @SerializedName("productid")
        private String productid;

        @SerializedName("bizver")
        private int bizver;

        @SerializedName("productOrigin")
        private int productOrigin;

        // Getter 和 Setter
        public String getRomver() {
            return romver;
        }

        public void setRomver(String romver) {
            this.romver = romver;
        }

        public String getBizid() {
            return bizid;
        }

        public void setBizid(String bizid) {
            this.bizid = bizid;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getVersionname() {
            return versionname;
        }

        public void setVersionname(String versionname) {
            this.versionname = versionname;
        }

        public int getVersioncode() {
            return versioncode;
        }

        public void setVersioncode(int versioncode) {
            this.versioncode = versioncode;
        }

        public String getImei() {
            return imei;
        }

        public void setImei(String imei) {
            this.imei = imei;
        }

        public String getSn() {
            return sn;
        }

        public void setSn(String sn) {
            this.sn = sn;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getFamilyid() {
            return familyid;
        }

        public void setFamilyid(String familyid) {
            this.familyid = familyid;
        }

        public String getChildid() {
            return childid;
        }

        public void setChildid(String childid) {
            this.childid = childid;
        }

        public String getMac() {
            return mac;
        }

        public void setMac(String mac) {
            this.mac = mac;
        }

        public String getAndroidid() {
            return androidid;
        }

        public void setAndroidid(String androidid) {
            this.androidid = androidid;
        }

        public Double getLongitude() {
            return longitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }

        public Double getLatitude() {
            return latitude;
        }

        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public Object getPos() {
            return pos;
        }

        public void setPos(Object pos) {
            this.pos = pos;
        }

        public Object getCity() {
            return city;
        }

        public void setCity(Object city) {
            this.city = city;
        }

        public String getModelid() {
            return modelid;
        }

        public void setModelid(String modelid) {
            this.modelid = modelid;
        }

        public String getChipid() {
            return chipid;
        }

        public void setChipid(String chipid) {
            this.chipid = chipid;
        }

        public String getTraceId() {
            return traceId;
        }

        public void setTraceId(String traceId) {
            this.traceId = traceId;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getProductid() {
            return productid;
        }

        public void setProductid(String productid) {
            this.productid = productid;
        }

        public int getBizver() {
            return bizver;
        }

        public void setBizver(int bizver) {
            this.bizver = bizver;
        }

        public int getProductOrigin() {
            return productOrigin;
        }

        public void setProductOrigin(int productOrigin) {
            this.productOrigin = productOrigin;
        }
    }

    public QueryRecord(AiStudyDevice aiStudyDevice, String romVer) {
        this.base = new Base();
        this.base.setAppid(aiStudyDevice.getAppId());
        this.base.setBizid(aiStudyDevice.getBizId());
        this.base.setModelid(aiStudyDevice.getModelId());
        this.base.setProductid(aiStudyDevice.getProductId());
        this.base.setRomver("iFLY_V"+romVer.substring(0,1)+"."+romVer.substring(1,3)+"."+romVer.substring(3,4));
    }

    public Response query(Gson gson, DirtyApiClient dirtyApiClient) {
        MediaType mediaType = MediaType.parse("application/json");

        StringBuilder url = new StringBuilder("https://api.xunfeixxj.com/config/systemUpgrade/queryRecord");
        url.append("?monitorTraceId=");
        url.append(this.getBase().getUserid());
        url.append("&c=2.0");

        if(this.base.getAppid() == null && this.base.getBizid() == null && this.base.getModelid() == null && this.base.getRomver() == null) {
            return null;
        }
        String bodyContent = gson.toJson(this);
        Logger.debug("请求体：", bodyContent);
        byte[] compressedBytes = null;
        try {
            compressedBytes = GzipUtils.compress(bodyContent.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        byte[] encryptedBytes = AESUtils.encrypt(compressedBytes);
        String finalString = Base64Utils.encode(encryptedBytes);
        Logger.debug("实际请求体：", finalString);

        RequestBody body = RequestBody.create(mediaType, finalString); // 或者根据需要构建请求体
        Request request = new Request.Builder()
                .url(url.toString())
                .post(body) // 使用post()方法发送POST请求
                .build();
        return dirtyApiClient.execute(request);
    }
}