package com.github.cnlinjie.infrastructure.web.util;

import org.springframework.util.MimeTypeUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author linjie
 * @version 0.0.1
 * @date 16-5-12
 */
public final class ResponseUtils {

    /**
     * 输出json
     *
     * @param response
     * @param text
     * @throws IOException
     */
    public static final void writeJson(HttpServletResponse response, String text) throws IOException {
        response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(text.getBytes("UTF-8"));
        outputStream.flush();
    }
}
