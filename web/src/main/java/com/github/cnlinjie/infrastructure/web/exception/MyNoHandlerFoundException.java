package com.github.cnlinjie.infrastructure.web.exception;

/**
 * @author linjie
 * @version 0.0.1
 * @date 16-7-6
 */
public class MyNoHandlerFoundException extends RuntimeException {

    private final String httpMethod;

    private final String requestURL;

    /**
     * Constructor for NoHandlerFoundException.
     * @param httpMethod the HTTP method
     * @param requestURL the HTTP request URL
     */
    public MyNoHandlerFoundException(String httpMethod, String requestURL) {
        super("No handler found for " + httpMethod + " " + requestURL);
        this.httpMethod = httpMethod;
        this.requestURL = requestURL;
    }


    public String getHttpMethod() {
        return this.httpMethod;
    }

    public String getRequestURL() {
        return this.requestURL;
    }


}
