package com.iflytek.study.ota.modules;


//import com.iflytek.ys.core.util.log.Logging;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: classes.dex */
public class MD5Utils {
    private static final int BUFF_SIZE = 4096;
    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static final String MD5 = "MD5";

    public static String md5Hex(byte[] bArr, boolean z, boolean z2) {
        String str = new String(encodeHex(Md5(bArr), DIGITS_LOWER));
        if (z) {
            str = str.substring(8, 24);
        }
        return z2 ? str.toUpperCase() : str;
    }

    public static String md5Hex(byte[] bArr) {
        return md5Hex(bArr, false, false);
    }

    private static byte[] Md5(byte[] bArr) {
        try {
            return MessageDigest.getInstance(MD5).digest(bArr);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected static char[] encodeHex(byte[] bArr, char[] cArr) {
        int length = bArr.length;
        char[] cArr2 = new char[length << 1];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            cArr2[i] = cArr[(bArr[i2] & 240) >>> 4];
            i = i3 + 1;
            cArr2[i3] = cArr[bArr[i2] & 15];
        }
        return cArr2;
    }

    public static String md5EncodeFile(File file) {
        int i;
        try {
            byte[] bArr = new byte[4096];
            MessageDigest messageDigest = MessageDigest.getInstance(MD5);
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            if (randomAccessFile.length() <= 0) {
                randomAccessFile.close();
                return null;
            }
            randomAccessFile.seek(0L);
            while (true) {
                int read = randomAccessFile.read(bArr);
                if (read <= 0) {
                    break;
                }
                messageDigest.update(bArr, 0, read);
            }
            randomAccessFile.close();
            byte[] digest = messageDigest.digest();
            char[] cArr = new char[digest.length * 2];
            int i2 = 0;
            for (byte b : digest) {
                int i3 = i2 + 1;
                cArr[i2] = DIGITS_LOWER[(b >>> 4) & 15];
                i2 = i3 + 1;
                cArr[i3] = DIGITS_LOWER[b & 15];
            }
            return new String(cArr);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String md5Encode(String str) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            MessageDigest messageDigest = MessageDigest.getInstance(MD5);
            messageDigest.update(bytes);
            byte[] digest = messageDigest.digest();
            char[] cArr = new char[digest.length * 2];
            int i = 0;
            for (byte b : digest) {
                int i2 = i + 1;
                cArr[i] = DIGITS_LOWER[(b >>> 4) & 15];
                i = i2 + 1;
                cArr[i2] = DIGITS_LOWER[b & 15];
            }
            return new String(cArr);
        } catch (Exception unused) {
            return null;
        }
    }
}