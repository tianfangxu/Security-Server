package com.mot.utils;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtils {

    public static final int DEFAULT_BUFFER_SIZE = 4096;

    /**
     * 创建文件
     * @param file
     */
    public static void createNewFile(File file){
        if (!file.exists()){
            createParentFolder(file.getParentFile());
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    /**
     * 创建文件夹
     * @param fileFolder
     */
    public static void createParentFolder(File fileFolder){
        if (!fileFolder.exists()){
            File parentFile = fileFolder.getParentFile();
            if (!parentFile.exists()){
                createParentFolder(parentFile);
            }
            try {
                fileFolder.mkdir();
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    /**
     * 流读写
     * @param in 源输入流
     * @param out 目标输出流
     * @param bufferSize 缓冲区大小
     */
    public static void copyStream(InputStream in, OutputStream out,int bufferSize,boolean isInClose,boolean isOutClose){
        if(in == null || out == null || bufferSize < 1){
            throw new RuntimeException("无法操作的数据流");
        }
        byte[] bts = new byte[bufferSize];
        int len = -1;

        try {
            while ((len = in.read(bts)) != -1){
                out.write(bts,0,len);
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }finally {
            try {
                if (isInClose){
                    in.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            try {
                out.flush();
            }catch (Exception e){
                e.printStackTrace();
            }
            try {
                if (isOutClose){
                    out.close();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void copyStream(InputStream in, OutputStream out){
        copyStream(in,out,DEFAULT_BUFFER_SIZE,true,true);
    }

    public static void copyStream(InputStream in, OutputStream out,boolean isInClose,boolean isOutClose){
        copyStream(in,out,DEFAULT_BUFFER_SIZE,isInClose,isOutClose);
    }

    /**
     *  文件流读写
     * @param file 源文件对象
     * @param out   目标输出流
     * @param bufferSize 缓冲区大小
     * @param startPoint 文件流开始写入位置
     * @param length    写入长度
     */
    public static void copyFileStream(File file,OutputStream out,int bufferSize,Long startPoint,Long length){
        if (!file.exists()){
            throw new RuntimeException("文件不存在");
        }
        if (out == null) {
            throw new RuntimeException("目标输出流不存在");
        }
        if(bufferSize < 1){
            throw new RuntimeException("缓冲区大小必须大于0");
        }
        if (startPoint == -1 && length == -1){
            try {
                copyStream(new FileInputStream(file),out,bufferSize,true,true);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else {
            RandomAccessFile accessFile = null;
            try {
                accessFile = new RandomAccessFile(file, "rw");
                accessFile.seek(startPoint);
                byte[] bts = new byte[bufferSize];
                int len = -1;
                while ((len = accessFile.read(bts)) != -1){
                    out.write(bts,0,len > length ? length.intValue() :len);
                    length = length -len;
                }
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }finally {
                try {
                    accessFile.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
                try {
                    out.flush();
                }catch (Exception e){
                    e.printStackTrace();
                }
                try {
                    out.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    public static void copyFileStream(File file,OutputStream out,int bufferSize){
        copyFileStream(file,out,bufferSize,-1L,-1L);
    }
    public static void copyFileStream(File file,OutputStream out){
        copyFileStream(file,out,DEFAULT_BUFFER_SIZE,-1L,-1L);
    }

    /**
     * 文件加打成压缩包
     */
    public static void dozip(String resourceFolder,String targetFilePath){
        File file = new File(resourceFolder);
        if (file.isDirectory()) {
            dozip(Arrays.asList(file.listFiles()),targetFilePath);
        }
    }
    /**
     * 多个文件打成压缩包
     */
    public static void dozip(List<File> fileList, String targetFilePath){
        File file = new File(targetFilePath);
        try {
            FileOutputStream os = new FileOutputStream(file);
            CheckedOutputStream cos = new CheckedOutputStream(os, new CRC32());
            ZipOutputStream zout = new ZipOutputStream(cos);
            fileList.forEach( f -> {
                try {
                    FileInputStream in = new FileInputStream(f);
                    ZipEntry zipEntry = new ZipEntry(f.getName());
                    zout.putNextEntry(zipEntry);
                    copyStream(in,zout,true,false);
                }catch (Exception e){
                    e.printStackTrace();
                }
            });
            zout.closeEntry();
            zout.close();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
