package com.iflytek.study.ota;

import okhttp3.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static boolean isDebug = false;

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
                .addHeader("X-Ca-Timestamp", "516910601879")
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

    public static void main(String[] args) throws Exception {

        Logger.info("正在初始化...");

        Scanner scanner = new Scanner(System.in);
        String command = new String();

        CommandHandler commandHandler = new CommandHandler();

        Logger.info("初始化完毕！使用 help 来查看命令列表。");


        while (true) {
            System.out.print("> ");
            command = scanner.nextLine();
            commandHandler.handleCommand(command);
        }
    }

}

























