package com.github.cnlinjie.infrastructure.web.api;

import com.alibaba.fastjson.JSON;
import com.github.cnlinjie.infrastructure.util.net.HttpHelper;
import com.github.cnlinjie.infrastructure.web.exception.HttpNetException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * @author linjie
 * @version 0.0.1
 * @date 16-5-16
 */
public class BaseApi {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected String apiBaseUrl = "";

    protected HttpHelper httpHelper = new HttpHelper();

    private String sessionId() {
        return RequestContextHolder.getRequestAttributes().getSessionId();
    }


    public BaseApi () {
    }

    public BaseApi (String apiBaseUrl) {
        this.apiBaseUrl = apiBaseUrl;
    }

    protected String apiUrl(String relativeUrl) {
        if (apiBaseUrl.endsWith("/")) apiBaseUrl = apiBaseUrl.substring(0, apiBaseUrl.length() - 1);
        if (!relativeUrl.startsWith("/")) relativeUrl = "/" + relativeUrl;
        return apiBaseUrl + relativeUrl;
    }

    protected String httpGet(String relativeUrl, Map<String, Object> params)  {
        try {
            return httpHelper.get(apiUrl(relativeUrl), params);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new HttpNetException(500, relativeUrl, JSON.toJSONString(params), e);
        }
    }

    protected String httpPost(String relativeUrl, Map<String, Object> params) {
        try {
            return httpHelper.post(apiUrl(relativeUrl), params);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new HttpNetException(500, relativeUrl, JSON.toJSONString(params), e);
        }
    }

    protected String httpPostJson(String relativeUrl, String jsonStr)  {
        try {
            return httpHelper.post(apiUrl(relativeUrl), jsonStr);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new HttpNetException(500, relativeUrl, jsonStr, e);
        }
    }

    protected String httpPostStream(String relativeUrl, InputStream stream, Map<String, Object> params)  {
        try {
            return httpHelper.post(apiUrl(relativeUrl), stream, params);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new HttpNetException(500, relativeUrl, JSON.toJSONString(params), e);
        }
    }

    protected String httpPostFile(String relativeUrl, File file, Map<String, Object> params)  {
        try {
            return httpHelper.post(apiUrl(relativeUrl), file, params);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new HttpNetException(500, relativeUrl, JSON.toJSONString(params), e);
        }
    }

    protected String getFile(String relativeUrl, Map<String, Object> params, OutputStream outputStream)  {
        try {
            return httpHelper.getFile(apiUrl(relativeUrl), params, outputStream);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new HttpNetException(500, relativeUrl, JSON.toJSONString(params), e);
        }
    }

    protected HttpServletRequest request() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()) .getRequest();
    }

    protected HttpServletResponse response() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()) .getResponse();
    }

    public String getApiBaseUrl () {
        return apiBaseUrl;
    }

    public void setApiBaseUrl (String apiBaseUrl) {
        this.apiBaseUrl = apiBaseUrl;
    }
}
