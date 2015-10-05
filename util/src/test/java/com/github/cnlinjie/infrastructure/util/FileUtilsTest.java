package com.github.cnlinjie.infrastructure.util;

import org.junit.Test;

import java.io.*;

/**
 * @author Linjie
 * @date 2015/10/5.
 */
public class FileUtilsTest {

    @Test
    public void uploadFile() throws IOException {
        String parentDir = "D:\\Test";
        InputStream in = new FileInputStream("D:\\Test\\Save_Test1.doc");
        String s = FileUtils.uploadFile(parentDir, "Save_Test1.doc", in);
        String s1 = FileUtils.uploadFile(parentDir, "Save_Test1.doc", new File("D:\\Test\\Save_Test1.doc"));
        System.out.println(s);
        System.out.println(s1);
        System.out.println("---");
    }

}
