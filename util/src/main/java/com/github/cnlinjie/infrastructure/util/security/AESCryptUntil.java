package com.github.cnlinjie.infrastructure.util.security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class AESCryptUntil {

    /**
     * @param buf 字节数组
     * @return 16进制字符串
     * @description: 字节数组转16进制字符串
     * @date: 2015-6-12 下午1:41:21
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * @param hexStr 16进制字符串
     * @return 字节数组
     * @description: 16进制字符串转字节数组
     * @date: 2015-6-12 下午1:42:02
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
                    16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * @param data     加密数据
     * @param password 密钥
     * @return 加密后的字节数据, null 失败
     * @description: Aes加密
     * @date: 2015-6-11 上午11:08:55
     */
    public byte[] Encrypt(String data, String password) {
        try {

            byte[] encoded = this.getKey(password);
            SecretKeySpec secretKeySpec = new SecretKeySpec(encoded, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            byte[] byteData = data.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] result = cipher.doFinal(byteData);

            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * @param data     解密数据
     * @param password 密钥
     * @return 解密后数据，null 失败
     * @description: AES解密
     * @date: 2015-6-11 上午11:17:13
     */
    public byte[] Dectypt(byte[] data, String password) {
        try {
            byte[] encoded = this.getKey(password);
            SecretKeySpec secretKeySpec = new SecretKeySpec(encoded, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] result = cipher.doFinal(data);

            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public byte[] getKey(String key) {
        byte[] keyBytes = new byte[16];

        byte[] byteTemp = key.getBytes();

        for (int i = 0; i < keyBytes.length; i++) {
            if (i >= byteTemp.length) {
                keyBytes[i] = 0;
            } else {
                keyBytes[i] = byteTemp[i];
            }
        }

        return keyBytes;
    }

    /**
     * @return 密钥
     * @description: 由当前日期生成密钥
     * @date: 2015-6-11 上午11:20:35
     */
    public String generateAESKeyByDate() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String time = simpleDateFormat.format(date);

        String out = Integer.toString(time.hashCode());
//		return Integer.toString(time.hashCode());
        return out;
    }

    /**
     * AES加密类型
     */
    public static enum AES_MODE {
        DECRYPT_MODE,        // 解密
        ENCRYPT_MODE;        // 加密
    }

    /**
     * @param data    加解密数据
     * @param dataKey 加解密秘钥
     * @param aesMode 加解密模式
     * @return 加解密后的字节数组， null 失败
     * @description: ASE加解密
     * @date: 2015-6-11 上午11:31:25
     */
    public static String CryptByTimePassword(String data, String dataKey, AES_MODE aesMode) {
        AESCryptUntil aesCryptUntil = new AESCryptUntil();
        byte[] result = null;
        String strResultString = null;

        try {

            switch (aesMode) {
                case DECRYPT_MODE:
                    /**解密*/
                    byte[] byteData = parseHexStr2Byte(data);
                    result = aesCryptUntil.Dectypt(byteData, dataKey);

                    if (null != result) {
                        strResultString = new String(result, "UTF-8");
                    }
                    break;
                case ENCRYPT_MODE:
                    /**加密*/
                    result = aesCryptUntil.Encrypt(data, dataKey);

                    if (null != result) {
                        strResultString = parseByte2HexStr(result);
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return strResultString;
    }

    /**
     * @param data    加解密数据
     * @param aesMode 加解密模式
     * @return 加解密后的字节数组， null 失败
     * @description: ASE加解密
     * @date: 2015-6-11 上午11:31:25
     */
    public static String CryptByTimePassword(String data, AES_MODE aesMode) {
        AESCryptUntil aesCryptUntil = new AESCryptUntil();
        /**获取日期密钥*/
        String dataKey = aesCryptUntil.generateAESKeyByDate();
        return  AESCryptUntil.CryptByTimePassword(data, dataKey, aesMode);
    }

}

