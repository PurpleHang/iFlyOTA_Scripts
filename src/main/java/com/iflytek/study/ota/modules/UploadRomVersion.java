package com.iflytek.study.ota.modules;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.iflytek.study.ota.ApiConstant;
import com.iflytek.study.ota.DirtyApiClient;
import com.iflytek.study.ota.Logger;
import com.iflytek.study.ota.core.AiStudyDevice;
import com.iflytek.study.ota.core.Param;
import com.iflytek.study.ota.utils.MD5Utils;
import com.iflytek.study.ota.utils.SAuthUtils;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.UUID;

public class UploadRomVersion {

    public static final String ACCESS_KEY = "bba93cde";
    public static final String ACCESS_KEY_SECRET = "15d8e25cb9d6b73bf6bbb9dc417d82c";

    public UploadRomVersion(AiStudyDevice aiStudyDevice, String romVer, String sn) {
        this.base.appid = aiStudyDevice.getAppId();
        this.base.productId = aiStudyDevice.getProductId();
        this.base.modelId = aiStudyDevice.getModelId();
        this.base.sn = sn;
        this.base.meid = sn;
        this.base.imei = sn;
        this.base.deviceId = sn;
        this.base.chipId = sn;
        this.base.romVer = romVer;
    }

    @SerializedName("base")
    private Base base = new Base();

    @SerializedName("param")
    private Param param = new Param();

    // Base类，包含了所有base字段  
    public static class Base {

        @SerializedName("appid")
        private String appid;

        @SerializedName("bt")
        private String bt;

        @SerializedName("chipId")
        private String chipId;

        @SerializedName("clientVer")
        private String clientVer = "1.0.0.1415";

        @SerializedName("deviceId")
        private String deviceId;

        @SerializedName("imei")
        private String imei;

        @SerializedName("lang")
        private String lang = "zh";

        @SerializedName("mac")
        private String mac;

        @SerializedName("meid")
        private String meid;

        @SerializedName("modelId")
        private String modelId;

        @SerializedName("osid")
        private String osid = "androidid";

        @SerializedName("pVer")
        private String pVer = "1.1";

        @SerializedName("productId")
        private String productId;

        @SerializedName("romVer")
        private String romVer;

        @SerializedName("sn")
        private String sn;

        @SerializedName("ua")
        private String ua;

    }

    public Response query(Gson gson, DirtyApiClient dirtyApiClient) {
        MediaType mediaType = MediaType.parse("application/json");

        StringBuilder url = new StringBuilder("https://api.hardware.xfinfr.com/gateway");

        String bodyContent = gson.toJson(this);
        Logger.debug("Body:", bodyContent);

        String finalString = bodyContent;

        RequestBody body = RequestBody.create(mediaType, finalString);

        String nonce = UUID.randomUUID().toString();
        Request request = new Request.Builder()
                .url("http://api.hardware.xfinfr.com/gateway")
                .post(body) // 设置请求方式为HEAD
                .addHeader(ApiConstant.API_X_CA_GROUP, "firmware")
                .addHeader(ApiConstant.API_X_CA_API, "uploadRomVersion")
                .addHeader(ApiConstant.API_X_CA_MOCK, "false")
                .addHeader(ApiConstant.API_X_CA_APPID, this.base.appid)
                .addHeader(ApiConstant.API_X_CA_STAGE, "RELEASE")
                .addHeader(ApiConstant.API_X_CA_NONCE, nonce)
                .addHeader(ApiConstant.HTTP_HEADER_CONTENT_MD5, "NULL")
                .addHeader(ApiConstant.API_X_CA_SIGNATURE_HEADERS, "X-Ca-AccessKey,X-Ca-Api,X-Ca-Appid,X-Ca-Group,X-Ca-Mock,X-Ca-Nonce,X-Ca-Stage,X-Ca-Timestamp,X-Ca-Version")
                .addHeader(ApiConstant.API_X_CA_TIMESTAMP, "NULL")
                .addHeader(ApiConstant.API_X_CA_VERSION, "1.0")
                .addHeader(ApiConstant.API_X_CA_ACCESSKEY, ACCESS_KEY)
                .addHeader(ApiConstant.API_X_CA_SIGNATURE, "NULL")
                .build();

        return null;
    }

}