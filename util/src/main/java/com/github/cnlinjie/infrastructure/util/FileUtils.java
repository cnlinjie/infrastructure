package com.github.cnlinjie.infrastructure.util;

import com.github.cnlinjie.infrastructure.util.net.FileResponseHandler;
import com.github.cnlinjie.infrastructure.util.spring.FileCopyUtils;
import org.apache.http.client.fluent.Request;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.util.UUID;

/**
 * @author Linjie
 * @date 2015/10/5.
 */
public class FileUtils {


    /**
     * 上传文件，或者也叫复制文件，将传入的文件流存储到指定的位置，存储位置为存储底目录+UUID生成的规则，如下规则:<br/>
     * 如参数 fileName 为 : test.png  <br/>
     * 随机 UUID 值 : 4865b709-06bd-4fd1-b707-f14bd0dd785f <br/>
     * 此时文件会存储在 : {parentDir}/4/8/6/4865b709-06bd-4fd1-b707-f14bd0dd785f/test.png  <br/>
     * 返回值为：4/8/6/4865b709-06bd-4fd1-b707-f14bd0dd785f/test.png  <br/>
     *
     * @param parentDir 存储底目录
     * @param fileName  文件名
     * @param in        输入流
     * @return 返回新的文件路径（只相对于 parentDir)
     * @throws IOException
     */
    public static String uploadFileByUUIDPath (String parentDir, String fileName, InputStream in) throws IOException {
        return _uploadFileByUUIDPath(parentDir, fileName, in);
    }


    /**
     * 上传文件，或者也叫复制文件，将传入的文件流存储到指定的位置，存储位置为存储底目录+UUID生成的规则，如下规则:<br/>
     * 如参数 fileName 为 : test.png  <br/>
     * 随机 UUID 值 : 4865b709-06bd-4fd1-b707-f14bd0dd785f <br/>
     * 此时文件会存储在 : {parentDir}/4/8/6/4865b709-06bd-4fd1-b707-f14bd0dd785f/test.png  <br/>
     * 返回值为：4/8/6/4865b709-06bd-4fd1-b707-f14bd0dd785f/test.png  <br/>
     *
     * @param parentDir 存储底目录
     * @param fileName  文件名
     * @param in        File 文件，需要上传的文件
     * @return 返回新的文件路径（只相对于 parentDir)
     * @throws IOException
     */
    public static String uploadFileByUUIDPath (String parentDir, String fileName, File in) throws IOException {
        return _uploadFileByUUIDPath(parentDir, fileName, new FileInputStream(in));
    }


    /**
     * 上传文件，或者也叫复制文件，将传入的文件流存储到指定的位置，存储位置为存储底目录+UUID生成的规则，如下规则:<br/>
     * 如参数 fileName 为 : test.png  <br/>
     * 随机 UUID 值 : 4865b709-06bd-4fd1-b707-f14bd0dd785f <br/>
     * 此时文件会存储在 : {parentDir}/4/8/6/4865b709-06bd-4fd1-b707-f14bd0dd785f/test.png  <br/>
     * 返回值为：4/8/6/4865b709-06bd-4fd1-b707-f14bd0dd785f/test.png  <br/>
     *
     * @param parentDir 存储底目录
     * @param fileName  文件名
     * @param in        字节流
     * @return 返回新的文件路径（只相对于 parentDir)
     * @throws IOException
     */
    public static String uploadFileByUUIDPath (String parentDir, String fileName, byte[] in) throws IOException {
        return _uploadFileByUUIDPath(parentDir, fileName, in);
    }


    public static String uploadURIByUUIDPath (String parentDir, String fileName, URI uri) throws IOException {
        String byUUIDRelativePath = getByUUIDRelativePath(parentDir, fileName);
        String filePath = parentDir + byUUIDRelativePath;
        FileOutputStream out = new FileOutputStream(filePath);
        Request.Get(uri)
                .execute()
                .handleResponse(new FileResponseHandler(out));
        return  byUUIDRelativePath;
    }


    private static String _uploadFileByUUIDPath (String parentDir, String fileName, InputStream in) throws IOException {
        String byUUIDRelativePath = getByUUIDRelativePath(parentDir, fileName);
        String filePath = parentDir + byUUIDRelativePath;
        FileOutputStream out = new FileOutputStream(filePath);
        FileCopyUtils.copy(in, out);
        return  byUUIDRelativePath;
    }


    private static String _uploadFileByUUIDPath (String parentDir, String fileName, byte[] in) throws IOException {
        String byUUIDRelativePath = getByUUIDRelativePath(parentDir, fileName);
        String filePath = parentDir + byUUIDRelativePath;
        FileOutputStream out = new FileOutputStream(filePath);
        FileCopyUtils.copy(in, out);
        return  byUUIDRelativePath;
    }



    public static String getByUUIDRelativePath (String parentDir, String fileName) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String fileEnd = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        String newFileName = String.format("%s.%s", uuid, fileEnd);
        String newFilePath = String.format("/%s/%s/%s/%s/",
                uuid.substring(0, 1),
                uuid.substring(1, 2),
                uuid.substring(2, 3),
                uuid);
        File fileDir = new File(parentDir + newFilePath);
        if (!fileDir.exists()) {
            if (!fileDir.mkdirs()) {
                throw new IllegalArgumentException("目录创建失败");
            }
        }
        return newFilePath + newFileName;
    }




    /**
     * 上传文件，或者也叫复制文件，将传入的文件流存储到指定的位置，并返回新文件的路径
     *
     * @param parentDir 存储底目录
     * @param fileName  文件名
     * @param in        输入流
     * @return 返回新的文件路径（只相对于 parentDir)
     * @throws IOException
     */

    public static String uploadFile (String parentDir, String fileName, InputStream in) throws IOException {
        String fileEnd = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        File fileDir = new java.io.File(parentDir);
        String newFileName = String.format("%s.%s", UUID.randomUUID().toString(), fileEnd);
        if (!fileDir.exists()) {
            if (!fileDir.mkdirs()) {
                throw new IllegalArgumentException("目录创建失败");
            }
        }
        String filePath = parentDir + "/" + newFileName;
        FileOutputStream out = new FileOutputStream(filePath);
        FileCopyUtils.copy(in, out);
        return newFileName;
    }

    public static void main (String[] args) {
        try {
            String byUUIDRelativePath = getByUUIDRelativePath("/data/test/upload", "111.jpg");
            System.out.println(byUUIDRelativePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
