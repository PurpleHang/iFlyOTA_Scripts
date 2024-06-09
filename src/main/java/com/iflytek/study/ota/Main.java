package com.iflytek.study.ota;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iflytek.study.ota.core.AiStudyDevice;
import com.iflytek.study.ota.modules.QueryRecord;
import com.iflytek.study.ota.utils.AESUtils;
import com.iflytek.study.ota.utils.Base64Utils;
import com.iflytek.study.ota.utils.DESUtils;
import com.iflytek.study.ota.utils.GzipUtils;
import okhttp3.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    public static String gettime() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("http://api.hardware.xfinfr.com/time")
                .get()
                .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                .addHeader("Accept", "*/*")
                .addHeader("Host", "api.hardware.xfinfr.com")
                .addHeader("Connection", "keep-alive")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            return "IOException occurred: " + e.getMessage();
        }
    }

    public static String getota(){
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://api.hardware.xfinfr.com/gateway")
                .get() // 设置请求方式为HEAD
                .addHeader("X-Ca-Api", "upgrade")
                .addHeader("Accept", "application/json")
                .addHeader("X-Ca-Mock", "false")
                .addHeader("X-Ca-Appid", "U860R2TC")
                .addHeader("X-Ca-Stage", "RELEASE")
                .addHeader("User-Agent", "ANDROID-SDK")
                .addHeader("Host", "api.hardware.xfinfr.com")
                .addHeader("Date", "Sat, 08 Jun 2024 12:31:33 GMT+08:00")
                .addHeader("X-Ca-Nonce", "0b76d3a7-d0dd-4233-8810-2b189c025bbe")
                .addHeader("Content-MD5", "56dc5949427399e344a483b0babc305a")
                .addHeader("X-Ca-Signature-Headers", "X-Ca-AccessKey,X-Ca-Api,X-Ca-Appid,X-Ca-Group,X-Ca-Mock,X-Ca-Nonce,X-Ca-Stage,X-Ca-Timestamp,X-Ca-Version")
                .addHeader("X-Ca-Timestamp", gettime())
                .addHeader("X-Ca-Version", "1.0")
                .addHeader("X-Ca-AccessKey", "bba93cde")
                .addHeader("X-Ca-Signature", "47bc034da6151c4dbd2faf53b51aaeda")
                .addHeader("X-Ca-Group", "firmware")
                .addHeader("Content-Type", "application/json")
                .addHeader("Connection", "Keep-Alive")
                .addHeader("Accept-Encoding", "gzip")
                .addHeader("Content-Length", "296")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            return "IOException occurred: " + e.getMessage();
        }
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static String bytesToHex(byte[] bytes) {
        final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static void main(String[] args) throws Exception {

        System.out.println("可用命令：\nset 设置版本和机型\nqueryrecord 获取更新日志\ntime(dubug):获取更新包");


        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.serializeNulls();
        Gson gson = gsonBuilder.create();
        DirtyApiClient dirtyApiClient = new DirtyApiClient();
        AiStudyDevice aiStudyDevice = AiStudyDevice.CTG3;
        QueryRecord queryRecord = new QueryRecord(aiStudyDevice, "iFLY_V1.00.0");

        Scanner scanner = new Scanner(System.in);
        String command, outputString;
        byte[] bytesToDecrypt, decryptedBytes, decompressedBytes, compressedBytes, encryptedBytes;
        String[] temp;

        while (true) {
            System.out.print("> ");
            command = scanner.nextLine();
            temp = command.split(" ");
            if (temp != null) {
                switch (temp[0].toLowerCase()) {
                    case "deskey": {
                        System.out.println("DES key is: " + DESUtils.getKey());
                        break;
                    }
                    case "desdecrypt": {
                        if (!(temp.length <= 1)) {
                            bytesToDecrypt = hexStringToByteArray(temp[1]);
                            decryptedBytes = DESUtils.decrypt(bytesToDecrypt);
                            decompressedBytes = GzipUtils.decompress(decryptedBytes);
                            outputString = new String(decompressedBytes);
                            System.out.println(outputString);
                        } else {
                            System.out.println("Missing arguments.");
                        }
                        break;
                    }
                    case "aeskey": {
                        System.out.println("AES key is: " + AESUtils.getKey());
                        break;
                    }
                    case "aesdecrypt": {
                        if (!(temp.length <= 1)) {
                            bytesToDecrypt = Base64Utils.decode(temp[1]);
                            decryptedBytes = AESUtils.decrypt(bytesToDecrypt);
                            decompressedBytes = GzipUtils.decompress(decryptedBytes);
                            outputString = new String(decompressedBytes);
                            System.out.println(outputString);
                        } else {
                            System.out.println("Missing arguments.");
                        }
                        break;
                    }
                    case "aesencrypt": {
                        if (!(temp.length <= 1)) {
                            compressedBytes = GzipUtils.compress(temp[1].getBytes(StandardCharsets.UTF_8));
                            encryptedBytes = AESUtils.encrypt(compressedBytes);
                            outputString = Base64Utils.encode(encryptedBytes);
                            System.out.println(outputString);
                        } else {
                            System.out.println("Missing arguments.");
                        }
                        break;
                    }
                    case "queryrecord": {
                        outputString = queryRecord.query(gson, dirtyApiClient).body().string();
                        bytesToDecrypt = Base64Utils.decode(outputString);
                        decryptedBytes = AESUtils.decrypt(bytesToDecrypt);
                        decompressedBytes = GzipUtils.decompress(decryptedBytes);
                        outputString = new String(decompressedBytes);
                        System.out.println(outputString);
                        break;
                    }
                    case "set": {//选择机型，版本
                        System.out.println("1.设置机型\n2.设置版本号");
                        int qqq = scanner.nextInt();
                        if (qqq == 1) {
                            System.out.println("输入机型编码：\n3.T20 Pro\n5.T10\n6.X1pro\n8.X2pro");
                            int inputIndex = scanner.nextInt();
                            scanner.nextLine(); // Consume newline left-over

                            AiStudyDevice[] aiStudyDevices = AiStudyDevice.values();

                            aiStudyDevice = aiStudyDevices[inputIndex];
                        } else if (qqq == 2) {
                            System.out.println("请输入版本号(格式：2.00.5):");
                            Scanner input = new Scanner(System.in);
                            queryRecord = new QueryRecord(aiStudyDevice, "iFLY_V" + input.nextLine());
                            System.out.println(queryRecord);

                        }

                        break;
                    }

                    case "time":{
                        String responseValue = gettime();
                        System.out.println(responseValue);

                        responseValue = getota();
                        System.out.println(responseValue);
                        break;

                    }


                    case "exit": {
                        return;
                    }
                }
            }
        }
    }

}

























