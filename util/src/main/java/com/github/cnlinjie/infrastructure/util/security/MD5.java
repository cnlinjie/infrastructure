package com.github.cnlinjie.infrastructure.util.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String encode(String plainText) {
        org.linjie.framework.util.security.MD5 md5 = new org.linjie.framework.util.security.MD5();
        return md5.encryption(plainText);
    }

    public String encryption(String plainText) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte[] b = md.digest();

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                int i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            re_md5 = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return re_md5;
    }

    public static void main(String[] args) {
        org.linjie.framework.util.security.MD5 md5 = new org.linjie.framework.util.security.MD5();
        System.out.println(md5.encryption("admin"));
    }
}