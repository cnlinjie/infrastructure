package com.github.cnlinjie.infrastructure.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 一些流的基础操作
 * @author linjie
 * @version 0.0.1
 * @date 16-6-29
 */
public class Stream2Utils {

    public static String inputStream2String (InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i;
        while ((i = is.read()) != -1) {
            baos.write(i);
        }
        return baos.toString();
    }

}
