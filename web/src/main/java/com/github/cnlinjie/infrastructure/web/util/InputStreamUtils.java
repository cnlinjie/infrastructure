package com.github.cnlinjie.infrastructure.web.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author linjie
 * @version 0.0.1
 * @date 16-6-28
 */
public class InputStreamUtils {

    public static String inputStream2String (InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i;
        while ((i = is.read()) != -1) {
            baos.write(i);
        }
        return baos.toString();
    }

}
