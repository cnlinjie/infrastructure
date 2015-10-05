package com.github.cnlinjie.infrastructure.util;

import com.github.cnlinjie.infrastructure.util.spring.FileCopyUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author Linjie
 * @date 2015/10/5.
 */
public class FileUtils {


    /**
     * 上传文件，或者也叫复制文件，将传入的文件流存储到指定的位置，并返回新文件的路径
     * @param parentDir  存储底目录
     * @param fileName 文件名
     * @param in  输入流
     * @return  返回新的文件路径（只相对于 parentDir)
     * @throws IOException
     */
    public static String uploadFile(String parentDir, String fileName, InputStream in) throws IOException {
        String fileEnd = fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
        String uuid = UUID.randomUUID().toString();
        String newFilePath =  String.format("%s/%s/%s/%s",
                uuid.substring(0,1),
                uuid.substring(1,2),
                uuid.substring(2,3),
                uuid);

        java.io.File fileDir = new java.io.File(parentDir + "/" +  newFilePath);
        String newFileName = String.format("%s.%s", newFilePath, uuid, fileEnd);
        if(!fileDir.exists()){
            Boolean mkResult = fileDir.mkdirs();
            if(mkResult){
                newFileName = String.format("%s/%s.%s", newFilePath, uuid, fileEnd);
            }
        }
        String filePath = parentDir + "/" +  newFileName;
        FileOutputStream out = new FileOutputStream(filePath);
        FileCopyUtils.copy(in,out);
        return newFileName;
    }

    /**
     * 上传文件，或者也叫复制文件，将传入的文件流存储到指定的位置，并返回新文件的路径
     * @param parentDir  存储底目录
     * @param fileName 文件名
     * @param in  输入流
     * @return  返回新的文件路径（只相对于 parentDir)
     * @throws IOException
     */
    public static String uploadFile(String parentDir, String fileName, File in) throws IOException {
        String fileEnd = fileName.substring(fileName.lastIndexOf(".")+1).toLowerCase();
        String uuid = UUID.randomUUID().toString();
        String newFilePath =  String.format("%s/%s/%s/%s",
                uuid.substring(0,1),
                uuid.substring(1,2),
                uuid.substring(2,3),
                uuid);

        java.io.File fileDir = new java.io.File(parentDir + "/" +  newFilePath);
        String newFileName = String.format("%s.%s", newFilePath, uuid, fileEnd);
        if(!fileDir.exists()){
            Boolean mkResult = fileDir.mkdirs();
            if(mkResult){
                newFileName = String.format("%s/%s.%s", newFilePath, uuid, fileEnd);
            }
        }
        String filePath = parentDir + "/" +  newFileName;
        File out = new File(filePath);
        FileCopyUtils.copy(in, out);
        return newFileName;
    }

}
