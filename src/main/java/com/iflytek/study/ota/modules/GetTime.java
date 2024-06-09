package com.iflytek.study.ota.modules;

import com.iflytek.study.ota.DirtyApiClient;
import okhttp3.*;

public class GetTime {

    public static Response query(DirtyApiClient dirtyApiClient) {
        Request request = new Request.Builder()
                .url("http://api.hardware.xfinfr.com/time")
                .get()
                .build();

        return dirtyApiClient.execute(request);
    }
}
