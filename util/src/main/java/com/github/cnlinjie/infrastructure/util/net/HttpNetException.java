package com.github.cnlinjie.infrastructure.util.net;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author linjie
 * @version 0.0.1
 * @date 16-6-21
 */
public class HttpNetException extends RuntimeException {

    private IOException ioException;

    private String url = "";
    private String params = "";

    private static final long serialVersionUID = 3230630383646562969L;
    private Integer code;

    public HttpNetException(Integer code) {
        super();
        this.code = code;
    }

    public HttpNetException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public HttpNetException(Integer code, IOException ioException) {
        this(code);
        this.ioException = ioException;
    }


    public HttpNetException(Integer code, String url, String params, IOException ioException) {
        this(code);
        this.ioException = ioException;
        this.params = params;
        this.url = url;
    }

    public HttpNetException(Integer code, String message, IOException ioException) {
        this(code, message);
        this.ioException = ioException;
    }

    @Override
    public String getMessage () {
        return ioException.getMessage();
    }

    public IOException getIoException () {
        return ioException;
    }

    @Override
    public String getLocalizedMessage () {
        return ioException.getLocalizedMessage();
    }

    @Override
    public synchronized Throwable getCause () {
        return ioException.getCause();
    }

    @Override
    public synchronized Throwable initCause (Throwable cause) {
        return ioException.initCause(cause);
    }

    @Override
    public void printStackTrace (PrintWriter s) {
        ioException.printStackTrace(s);
    }

    @Override
    public void printStackTrace () {
        ioException.printStackTrace();
    }


    @Override
    public StackTraceElement[] getStackTrace () {
        return ioException.getStackTrace();
    }

    @Override
    public void setStackTrace (StackTraceElement[] stackTrace) {
        ioException.setStackTrace(stackTrace);
    }

    public String getUrl () {
        return url;
    }

    public String getParams () {
        return params;
    }
}
