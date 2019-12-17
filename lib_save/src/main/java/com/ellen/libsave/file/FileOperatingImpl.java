package com.ellen.libsave.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOperatingImpl implements FileOperatingInterface {

    @Override
    public void createNewFile(String fatherPath, String fileName) throws IOException {
        File file = new File(fatherPath, fileName);
        if (!file.exists()) {
            //文件不存在
            file.createNewFile();
        }
    }

    @Override
    public void createNewFile(String path) throws IOException {
        File file = new File(path);
        createNewFile(file.getParentFile().getAbsolutePath(), file.getName());
    }

    @Override
    public void createNewFolder(String fatherPath, String folderName) {
        File file = new File(fatherPath, folderName);
        file.mkdirs();
    }

    @Override
    public boolean deleteFile(String fatherPath, String fileName) {
        File file = new File(fatherPath, fileName);
        if (file.exists()) {
            file.delete();
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteFileFolder(String fatherPath, String folderName) {
        File file = new File(fatherPath, folderName);
        //需要遍历文件夹的子文件
        if (!file.exists()) {
            return true;
        }

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                deleteFileFolder(f.getParentFile().getAbsolutePath(), f.getName());
            }
        }
        return file.delete();
    }

    @Override
    public void writeBytesToFile(byte[] bytes, String fatherPath, String fileName, boolean isAppend) throws IOException {
        File file = new File(fatherPath,fileName);
        //isAppend = true:追加写入
        FileOutputStream fos = new FileOutputStream(file,isAppend);
        fos.write(bytes);
        fos.close();
    }

    @Override
    public byte[] readBytesFromFile(String fatherPath, String fileName) throws IOException {
        File file = new File(fatherPath,fileName);
        long length = file.length();
        byte[] bytes = new byte[(int) length];
        FileInputStream fis = new FileInputStream(file);
        fis.read(bytes);
        return bytes;
    }

    @Override
    public void copyFile(String filePath, String fileName, String targetFilePath) throws IOException {
        //文件的复制很简单，就是先进行读取,然后再进行写入

        //读取文件内容
        byte[] bytes = readBytesFromFile(filePath,fileName);
        //接着先创建文件

        //看看父目录有没有，没有就创建
        File fatherFile = new File(targetFilePath);
        fatherFile.mkdirs();

        File file = new File(fatherFile,fileName);
        file.createNewFile();

        //最后再进行写入
        writeBytesToFile(bytes,targetFilePath,fileName,false);

    }

    @Override
    public void copyFileFoldeer(String filePath, String fileName, String targetFilePath) throws IOException {
        File file = new File(filePath,fileName);
        if(file.isFile()){
            //是文件
            return;
        }else {
            //是文件夹
            File fileCopy = new File(targetFilePath,fileName);
            fileCopy.mkdirs();

            //获取所有子目录
            File[] files = file.listFiles();
            for(File f:files){
                if(f.isFile()){
                    //是文件
                    copyFile(f.getParentFile().getAbsolutePath(),f.getName(),fileCopy.getAbsolutePath());
                }else {
                    //是文件夹
                    copyFileFoldeer(f.getParentFile().getAbsolutePath(),f.getName(),fileCopy.getAbsolutePath());
                }
            }
        }

    }
}
