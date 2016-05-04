package com.github.cnlinjie.infrastructure.util;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public final class PathUtil {

    private PathUtil() {
    }

    /**
     * 获取应用程序地址
     *
     * @return 返回应用程序地址
     */
    public static String getApplicationPath() {
		return SystemInfoUtils.getApplicationPath();
    }

    /**
     * 获取WEB应用程序地址
     *
     * @return WEB返回应用程序地址
     */
    public static String getWebApplicationPath() {
        String appDir;
        try {
            URL url = PathUtil.class.getClassLoader().getResource("");
            if (url != null) {
                appDir = url.toURI().getPath();
            } else {
                appDir = getApplicationPath();
            }
        } catch (Exception e) {
            appDir = getApplicationPath();// 应用程序目录
        }
        return appDir;
    }

    /**
     * 从全路径中取得根路径名
     *
     * @param sPath 完整文件名
     * @return String 根路径名
     */
    public static String getRootPath(String sPath) {
        switch (SystemInfoUtils.getOSType()) {
            case Windows:
                return sPath.substring(0, sPath.indexOf(File.separator));
            case Linux:
                return "/";
            default:
                return "/";
        }
    }

    /**
     * 将指定的路径转换成符合操作系统风格的路径形式
     *
     * @param path 完整路径
     * @return 返回符合当前应用程序的操作系统的路径
     */
    public static String translateStyle(String path) {

        String result = "";
        switch (SystemInfoUtils.getOSType()) {
            case Windows:
                result = path.replaceAll("/", "\\\\");
                break;
            case Linux:
                result = path.replaceAll("\\\\", "/");
                break;
        }
        return result;
    }

    /**
     * 将相对路径转换成绝对路径
     *
     * @param relatePath 相对路径
     * @return 返回绝对路径
     */
    public static String getAbsolutePath(String relatePath) {

        String result = "";
        result = translateStyle(relatePath);

        if (result.startsWith("/") || result.startsWith("\\")) {
            result = getApplicationPath() + result;
        } else {
            result = getApplicationPath() + File.separator + result;
        }

        return result;
    }

    /**
     * 对指定的路径的末尾移除斜杠
     *
     * @param path 文件的路径
     * @return 返回移除斜杠的路径
     */
    public static String trim(String path) {
        return trim(path, false);
    }

    /**
     * 对指定的路径的开始位置和末尾移除斜杠
     *
     * @param path 文件的路径
     * @param flag 如果为true则头尾都将移除斜杠，否则只对末尾移除斜杠
     * @return 返回移除斜杠的路径
     */
    public static String trim(String path, boolean flag) {

        String result = path;

        if (flag) {

            if (result.startsWith(File.separator))
                result = result.substring(1, result.length());
        }
        if (result.endsWith(File.separator))
            result = result.substring(0, result.length() - 1);

        return result;
    }

    /**
     * 将指定的子级路径连接至父级路径，形成全路径
     *
     * @param parentPath 父级路径
     * @param childPath  子级路径
     * @return 连接成功返回全路径
     */
    public static String join(String parentPath, String childPath) {
        String result = "";
        result = trim(parentPath) + File.separator + trim(childPath, true);
        return result;
    }

    /**
     * 获取当前路径的父路径
     *
     * @param path 当前路径
     * @return 返回父路径
     */
    public static String getParentPath(String path) {

        return new File(path).getParent();

    }

    /**
     * 对当前的路径进行解析，获取包含的所有目录名称
     *
     * @param path 当前路径
     * @return 返回目录名称集合
     */
    public static List<String> getFolderNames(String path) {

        String filePath = trim(path);
        String pattern = "/";

        if (filePath.indexOf("\\") >= 0)
            pattern = "\\\\";

        String[] folderNames = filePath.split(pattern);
        List<String> result = new ArrayList<String>();

        for (int index = 0; index < folderNames.length; index++)
            result.add(folderNames[index]);

        return result;
    }

    /**
     * 从路径中取得不含扩展名的文件名
     *
     * @param path 文件完整路径
     * @return 返回路径中的文件名（不含扩展名）
     */
    public static String getFileName(String path) {

        String result = trim(path);
        String pattern = "/";
        int index = 0;
        int a = result.lastIndexOf(File.separator);
        int b = result.lastIndexOf(pattern);
        if (a > b) {
            index = a;
        } else {
            index = b;
        }
        return result.substring(index + 1, result.lastIndexOf("."));
    }

    /**
     * 从路径中取得文件名，包含扩展名
     *
     * @param path 文件完整路径
     * @return 返回路径中文件名
     */
    public static String getFileFullName(String path) {

        String result = trim(path);
        return result.substring(result.lastIndexOf(File.separator) + 1);
    }

    /**
     * 获取文件的后缀名
     *
     * @param path 文件的路径
     * @return 获取成功返回后缀名(包含.)，否则返回null
     */
    public static String getExtension(String path) {

        String result = null;
        int index = path.lastIndexOf(".");

        if (index > -1)
            result = path.substring(index);

        return result;
    }

    /**
     * 从当前的路径中获取文件名，并在文件名中插入指定的字符串
     *
     * @param filePath  将被更改的路径
     * @param newString 要插入的字符串
     * @return 插入成功返回新的字符串
     */
    public static String insertFileName(String filePath, String newString) {

        String result = filePath;
        String parentPath = getParentPath(filePath);
        String fileName = getFileName(filePath);
        String extension = getExtension(filePath);
        fileName = fileName + newString;
        result = parentPath + File.separator + fileName + extension;
        return result;

    }

}
