package com.github.cnlinjie.infrastructure.util;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.List;

/**
 * 系统工具
 *
 * @author linjie
 * @version 0.0.1
 * @date 16-5-4
 */
public final class SystemInfoUtils {

    private static final Logger logger = LoggerFactory.getLogger(SystemInfoUtils.class);

    public enum OSType {
        Windows, Linux, Unknown
    }

    public final static OSType getOSType() {
        String name = System.getProperty("os.name");
        System.out.println(name.toLowerCase().startsWith("linux"));
        if (name.toLowerCase().startsWith("windows")) {
            return OSType.Windows;
        } else if (name.toLowerCase().startsWith("linux")) {
            return OSType.Linux;
        }
        logger.info("System.getProperty(\"os.name\")" + System.getProperty("os.name"));
        return OSType.Unknown;
    }

    /**
     * 获取应用程序地址
     *
     * @return 返回应用程序地址
     */
    public static String getApplicationPath() {
        String appDir = System.getProperty("user.dir");// 应用程序目录
        return appDir;
    }

    /**
     * 获取本机的MAC地址
     *
     * @return 返回本机的MAC地址
     */
    public static List<String> getMacAddress() {
        List<String> macs = Lists.newArrayList();
        try {
            Enumeration<NetworkInterface> netWorks = NetworkInterface.getNetworkInterfaces();
            while (netWorks.hasMoreElements()) {
                NetworkInterface netWork = netWorks.nextElement();
                byte[] mac = netWork.getHardwareAddress();
                if (mac == null)
                    continue;
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < mac.length; i++) {
                    builder.append(String.format("%02X%s", mac[i],(i < mac.length - 1) ? "-" : ""));
                }
                macs.add(builder.toString());
            }
        } catch (Exception e) {
            logger.error("getMacAddress Error:",e);
        }
        return macs;
    }

    public static void main(String[] args) {
        System.out.println(SystemInfoUtils.getOSType());
        List<String> macAddress = SystemInfoUtils.getMacAddress();
        System.out.println(macAddress.size());
    }


}
