package com.github.cnlinjie.infrastructure.web.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author linjie
 * @version 0.0.1
 * @date 16-5-12
 */
public final class BrowserUtils {


//    //TODO  待实现
//    public enum BrowserType {
//        IE, IE7, IE8, IE9, IE10, Opera, Firefox, Webkit;
//    }


    /**
     * 判断是否微信浏览器
     *
     * @param request
     * @return
     */
    public static boolean isMicroMessenger(HttpServletRequest request) {
        String userAgent = request.getHeader("user-agent");
        if (userAgent != null && userAgent.toLowerCase().indexOf("micromessenger") > 0) {
            return true;
        }
        return false;
    }


//    /**
//     * 判断是否Android
//     *
//     * @param request
//     * @return
//     */
//    public static boolean isAndroid(HttpServletRequest request) {
//        //TODO 判断是否Android
//        return false;
//    }
//
//
//    /**
//     * IOS
//     *
//     * @param request
//     * @return
//     */
//    public static boolean isIOS(HttpServletRequest request) {
//        //TODO  判断是否IOS
//        return false;
//    }


}
