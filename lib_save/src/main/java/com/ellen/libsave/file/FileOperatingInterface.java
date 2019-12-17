package com.ellen.libsave.file;

import java.io.IOException;

/**
 * 文件操作的接口
 */
public interface FileOperatingInterface {

    /**
     * 创建文件(通过父目录+文件名)
     * @param fatherPath 文件的父目录名字
     * @param fileName 文件名
     */
    void createNewFile(String fatherPath, String fileName) throws IOException;

    /**
     * 创建文件(通过全路径)
     * @param path
     */
    void createNewFile(String path) throws IOException;

    /**
     * 创建文件夹
     * @param fatherPath 文件夹的父目录
     * @param folderName 文件夹的名字
     */
    void createNewFolder(String fatherPath, String folderName);

    /**
     * 删除文件
     */
    boolean deleteFile(String fatherPath, String fileName);

    /**
     * 删除文件夹
     */
    boolean deleteFileFolder(String fatherPath, String folderName);

    /**
     *  将字节写入到某个文件(覆盖式)
     * @param bytes 字节数据
     * @param fatherPath 文件的父目录名字
     * @param fileName 文件名
     */
    void writeBytesToFile(byte[] bytes, String fatherPath, String fileName, boolean isAppend) throws IOException;

    byte[] readBytesFromFile(String fatherPath, String fileName) throws IOException;

    /**
     * 拷贝文件
     * @param filePath 被拷贝的文件的目录
     * @param fileName 被拷贝的文件的名字
     * @param targetFilePath
     */
    void copyFile(String filePath, String fileName, String targetFilePath) throws IOException;

    /**
     * 拷贝文件夹
     * @param filePath 被拷贝的文件的目录
     * @param fileName 被拷贝的文件的名字
     * @param targetFilePath
     */
    void copyFileFoldeer(String filePath, String fileName, String targetFilePath) throws IOException;

}
