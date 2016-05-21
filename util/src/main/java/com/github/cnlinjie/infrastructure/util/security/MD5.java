package com.github.cnlinjie.infrastructure.util.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public final class MD5 {

    private final static Logger logger = LoggerFactory.getLogger(MD5.class);

    public final static String getSalt() {
        return UUID.randomUUID().toString();
    }

    public  static String encode(String str) {
        return encode(str,null);
    }

    public  static String encode(String str,String salt) {
        String re_md5 = new String();
        try {
            str = mergePasswordAndSalt(str, salt);
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes("UTF-8"));
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
            logger.error("MD5 加密错误:", e);
        } catch (UnsupportedEncodingException e) {
            logger.error("MD5 加密错误:", e);
        }
        return re_md5;
    }


    /**
     *
     * 拼接密码与盐值
     *
     * @param password
     * @param salt
     *
     * @return 密码{盐值}
     */
    private final static String mergePasswordAndSalt(String password, Object salt) {
        if (salt == null || "".equals(salt.toString().trim())) {
            return password;
        }
        return password + "{" + salt.toString() + "}";
    }

    public static void main(String[] args) {
        MD5 md5 = new MD5();
        System.out.println(md5.encode("admin",MD5.getSalt()));
    }
}