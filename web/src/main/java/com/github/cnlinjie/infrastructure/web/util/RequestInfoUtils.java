package com.github.cnlinjie.infrastructure.web.util;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author linjie
 * @version 0.0.1
 * @date 16-5-12
 */
public final class RequestInfoUtils {

    public static final String X_REAL_IP = "X-Real-IP";

    public static final String X_FORWARDED_FOR = "X-Forwarded-For";

    /**
     * 获取请求的具体地址和参数
     *
     * @param request
     * @return 请求地址
     */
    public final static String getRequestURL(HttpServletRequest request) {
        String basePath = request.getScheme() + "://" + request.getServerName();
        String queryString = request.getQueryString();
        queryString = queryString == null ? "" : "?" + queryString;
        String requestURL = basePath + request.getRequestURI() + queryString;
        return requestURL;
    }

    public final static String addParams(String url, String[] keys, String[] values) {
        for (int i=0; i<keys.length;i++) {
            url = addParam(url, keys[i], values[i]);
        }
        return url;
    }

    public final static String addParam(String url, String key, String param) {
        StringBuffer sb = new StringBuffer(url);
        if (sb.indexOf("?")  == -1) {
            sb.append("?");
        }else {
            sb.append("&");
        }
        sb.append(key).append("=").append(param);
        return sb.toString();
    }

    /**
     * 获取远程IP地址
     *
     * @param request
     * @return
     */
    public static String getRemoteAddr(HttpServletRequest request) {
        String remoteIp = request.getHeader(X_REAL_IP); // nginx反向代理
        if (StringUtils.hasText(remoteIp)) {
            return remoteIp;
        } else {
            remoteIp = request.getHeader(X_FORWARDED_FOR);// apache反射代理
            if (StringUtils.hasText(remoteIp)) {
                String[] ips = remoteIp.split(",");
                for (String ip : ips) {
                    if (!"null".equalsIgnoreCase(ip)) {
                        return ip;
                    }
                }
            }
            return request.getRemoteAddr();
        }
    }

    /**
     * 去除对应的参数，然后返回去除后的URL
     * @param url
     * @param params
     * @return
     */
    public final static String removeParams(String url, String[] params) {
        String reg = null;
        for (int i = 0; i < params.length; i++) {
            reg = "(?<=[\\?&])" + params[i] + "=[^&]*&?";
            url = url.replaceAll(reg, "");
        }
        url = url.replaceAll("&+$", "");
        return url;
    }

    public static void main (String[] args) {
        String s = removeParams("http://192.168.254.188:8105/user/material/detail?type=688&userId=1752", new String[]{"userId"});
        System.out.println(s);
        String newURL = addParam(s, "userId", "213123");
        System.out.println(newURL);
    }


}
