package com.iflytek.study.ota;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.iflytek.study.ota.core.AiStudyDevice;
import com.iflytek.study.ota.modules.GetOTAPackage;
import com.iflytek.study.ota.modules.GetTime;
import com.iflytek.study.ota.modules.QueryRecord;
import com.iflytek.study.ota.modules.UploadRomVersion;
import com.iflytek.study.ota.utils.*;

import java.io.IOException;

public class CommandHandler {

    private AiStudyDevice aiStudyDevice = AiStudyDevice.CTG2_0;
    private String romVer = "1000";
    private String sn = "T9999999999999999";
    private QueryRecord queryRecord = new QueryRecord(aiStudyDevice, romVer);
    private UploadRomVersion uploadRomVersion = new UploadRomVersion(aiStudyDevice, romVer, sn);
    private GetOTAPackage getOTAPackage = new GetOTAPackage();
    Gson gson = new GsonBuilder().serializeNulls().create();
    DirtyApiClient dirtyApiClient = new DirtyApiClient();

    public void handleCommand(String command) {
        String[] args = command.toLowerCase().split(" ");
        if(args.length >= 1) {
            int index = 0;
            for(String arg : args) {
                Logger.debug("arg["+String.valueOf(index)+"]=", arg);
                index++;
            }
            switch(args[0]) {
                case "help" -> {
                    Logger.info("可用命令：");
                    Logger.info("list - 机型列表。");
                    Logger.info("set model <id> - 设置机型。");
                    Logger.info("set romver <romver> - 设置系统版本（格式1000）。");
                    Logger.info("queryrecord - 获取更新日志。");
                    Logger.info("uploadromversion - 上传系统版本。（仍在开发中）");
                    Logger.info("upgrade - 请求更新。（仍在开发中）");
                    Logger.info("getotapackage - 获取更新包。（仍在开发中）");
                    Logger.info("time - 获取网络时间戳。");
                    Logger.info("md5 <数据> - 获取md5。");
                }
                case "list" -> {
                    int mIndex = 0;
                    for(AiStudyDevice aiStudyDevice1 : AiStudyDevice.values()) {
                        Logger.info(String.valueOf(mIndex)+"、", aiStudyDevice1.getModel(), "(", aiStudyDevice1.getDesc(), ")");
                        mIndex++;
                    }
                }
                case "set" -> {
                    switch (args[1]) {
                        case "model" -> {
                            if (args.length > 2) {
                                if (!args[2].isEmpty()) {
                                    aiStudyDevice = AiStudyDevice.getDeviceById(Integer.parseInt(args[2]));
                                    if (aiStudyDevice == null) {
                                        Logger.error("不存在的设备！正在设置为 CTG3。");
                                        aiStudyDevice = AiStudyDevice.CTG3;
                                    }
                                    Logger.info("当前设备：", aiStudyDevice.getModel());
                                } else {
                                    Logger.error("缺少参数。");
                                }
                            } else {
                                Logger.error("缺少参数。");
                            }
                        }
                        case "romver" -> {
                            if (args.length > 2) {
                                if (!args[2].isEmpty()) {
                                    romVer = args[2];
                                    Logger.info("当前系统版本：", romVer);
                                } else {
                                    Logger.error("缺少参数。");
                                }
                            } else {
                                Logger.error("缺少参数。");
                            }
                        }
                        default -> {
                            Logger.error("缺少参数。");
                        }
                    }
                    queryRecord = new QueryRecord(aiStudyDevice, romVer);
                }
                case "time" -> {
                    try {
                        String outputString = GetTime.query(dirtyApiClient).body().string();
                        Logger.info("响应：", outputString);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "queryrecord" -> {
                    String outputString = null;
                    try {
                        outputString = queryRecord.query(gson, dirtyApiClient).body().string();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    byte[] bytesToDecrypt = Base64Utils.decode(outputString);
                    byte[] decryptedBytes = null;
                    if (bytesToDecrypt != null) {
                        decryptedBytes = AESUtils.decrypt(bytesToDecrypt);
                    }
                    byte[] decompressedBytes = null;
                    try {
                        decompressedBytes = GzipUtils.decompress(decryptedBytes);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    outputString = new String(decompressedBytes);
                    Logger.info("响应：", outputString);
                }
                case "uploadromversion" -> {
                    uploadRomVersion.query(gson, dirtyApiClient);
                }
                case "getotapackage" -> {
                    Logger.warn("仍在开发中。");
                }
                case "exit" -> {
                    System.exit(0);
                }
                case "md5" -> {
                    System.out.println(MD5Utils.md5Encode(args[1]));
                }
                default -> {
                    Logger.error("未知命令！");
                }
            }
        }
    }

}
