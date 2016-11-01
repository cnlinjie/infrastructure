package com.github.cnlinjie.infrastructure.util.net;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.CharsetUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 用于执行 API 操作的助手类
 */
public class HttpHelper {

    private final static Logger logger = LoggerFactory.getLogger(HttpHelper.class);
    private int connectionTimeout = 600000; // 时间单位：秒


    public HttpHelper() {
    }

    public HttpHelper(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public String get(String url, Map<String, Object> params)
            throws IOException {
        logger.info("sending request - GET " + url);
        return Request.Get(url + queryString(params))
                .socketTimeout(connectionTimeout)
                .connectTimeout(connectionTimeout)
                .execute()
                .returnContent()
                .asString();
    }

    public String get(String url, Map<String, Object> params, Map<String,String> headers)
            throws IOException {
        logger.info("sending request - GET " + url);
        System.out.println(url + queryString(params));
        return Request.Get(url + queryString(params))
                .socketTimeout(connectionTimeout)
                .connectTimeout(connectionTimeout)
                .setHeaders(headers(headers))
                .execute()
                .returnContent()
                .asString();
    }

    public String getFile(String url, Map<String, Object> params,
                          final OutputStream outputStream) throws IOException {
        logger.info("sending request - GET " + url);
        return Request.Get(url + queryString(params)).socketTimeout(connectionTimeout)
                .connectTimeout(connectionTimeout)
                .execute()
                .handleResponse(new FileResponseHandler(outputStream));
    }

    public String post(String url, Map<String, Object> data)
            throws IOException {
        logger.info("sending request - POST " + url);
        return Request.Post(url).socketTimeout(connectionTimeout)
                .connectTimeout(connectionTimeout)
                .bodyForm(form(data), CharsetUtils.get("utf-8"))
                .execute()
                .returnContent()
                .asString();
    }

    public String post(String url, Map<String, Object> data, Map<String,String> headers)
            throws IOException {
        logger.info("sending request - POST " + url);
        return Request.Post(url).socketTimeout(connectionTimeout)
                .connectTimeout(connectionTimeout)
                .bodyForm(form(data), CharsetUtils.get("utf-8"))
                .setHeaders(headers(headers))
                .execute()
                .returnContent()
                .asString();
    }

    public String post(String url, InputStream fileStream, Map<String, Object> params)
            throws IOException {
        logger.info("sending request - POST " + url);

        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create()
                .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                .setCharset(Charset.forName("UTF-8"))
                .addBinaryBody("media", fileStream, ContentType.MULTIPART_FORM_DATA, "fileName");
        formSet(multipartEntityBuilder, params);
        HttpEntity entity = multipartEntityBuilder.build();

        return Request.Post(url).socketTimeout(connectionTimeout)
                .connectTimeout(connectionTimeout)
                .body(entity)
                .execute()
                .returnContent()
                .asString();
    }

    private void formSet(MultipartEntityBuilder builder, Map<String, Object> data) {
        if (null != data && data.size() > 0) {
            for (String name : data.keySet()) {
                builder.addTextBody(name, objToString(data.get(name)));
            }
        }
    }

    // ////////////////////////////////////////////////////////

    public String post(String url, File file, Map<String, Object> params)
            throws IOException {
        logger.info("sending request - POST " + url);
        logger.info("file:" + file.getName());
        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create()
                .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                .setCharset(Charset.forName("UTF-8"))
                .addBinaryBody("media", file, ContentType.MULTIPART_FORM_DATA, file.getName());
        formSet(multipartEntityBuilder, params);
        HttpEntity entity = multipartEntityBuilder.build();
        return Request.Post(url).socketTimeout(connectionTimeout)
                .connectTimeout(connectionTimeout)
                .body(entity)
                .execute()
                .returnContent()
                .asString();
    }

    /**
     * @param url
     * @param data json string
     * @return
     * @throws java.io.IOException
     */
    public String post(String url, String data)
            throws IOException {
        logger.info("sending request - POST " + url);
        return Request.Post(url).socketTimeout(connectionTimeout)
                .connectTimeout(connectionTimeout)
                .bodyForm()
                .bodyString(data, ContentType.APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();
    }

    /**
     * post json
     * @param url
     * @param data json string
     * @return
     * @throws java.io.IOException
     */
    public String postJson(String url, String data)
            throws IOException {
        logger.info("sending request - POST " + url);
        return Request.Post(url).socketTimeout(connectionTimeout)
                .connectTimeout(connectionTimeout)
                .bodyForm()
                .bodyString(data, ContentType.APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();
    }

    /**
     * post json
     * @param url
     * @param data
     * @param headers
     * @return
     * @throws IOException
     */
    public String postJson(String url, String data, Map<String,String> headers)
            throws IOException {
        logger.info("sending request - POST " + url);
        return Request.Post(url).socketTimeout(connectionTimeout)
                .connectTimeout(connectionTimeout)
                .bodyForm()
                .bodyString(data, ContentType.APPLICATION_JSON)
                .setHeaders(headers(headers))
                .execute()
                .returnContent()
                .asString();
    }


    private String queryString(Map<String, Object> params) {
        if (params == null)
            params = new HashMap<String, Object>();

        List<String> plist = new ArrayList<String>();
        for (String name : params.keySet()) {
            plist.add(name + "=" + objToString(params.get(name)));
        }

        return !plist.isEmpty() ? "?" + StringUtils.join(plist, "&") : "";
    }

    private Header[] headers(Map<String, String> data) {
        Header[] headers = new Header[data.size()];
        int i = 0;
        for ( String key : data.keySet() ) {
            headers[i] = new BasicHeader( key,data.get(key) );
            i++;
        }
        return  headers;
    }

    private List<NameValuePair> form(Map<String, Object> data) {
        if (data == null)
            data = new HashMap<String, Object>();

        Form form = Form.form();
        for (String name : data.keySet()) {
            Object[] values = data.get(name) instanceof Object[] ? ((Object[]) data.get(name)) : null;
            if (null != values) {
                for (Object o : values) {
                    form.add(name, objToString(o));
                }
                continue;
            }
            form.add(name, objToString(data.get(name)));
        }

        return form.build();
    }

    private String objToString(Object obj) {
        if (obj instanceof Date) {
            if (obj instanceof java.sql.Date || obj instanceof java.sql.Time
                    || obj instanceof java.sql.Timestamp) {
                return obj.toString();
            } else {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                        .format((Date) obj);
            }
        } else if (obj instanceof Iterable) {
            return StringUtils.join((Iterable) obj, ",");
        }
        return obj == null ? "" : obj.toString();
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }
}
