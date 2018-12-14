package com.lnkj.privateshop.utils;

import java.io.File;

/**
 * Created by lnkj on 2017/6/2 0002.
 */

public class AppFileMgr {

    /**
     * 递归取得文件夹大小
     *
     * @param file
     * @return long
     */
    public static long getFileSize(File file) {
        long size = 0;
        if (file != null && file.exists() && file.isDirectory()) {
            File files[] = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    size = size + getFileSize(files[i]);
                } else {
                    size = size + files[i].length();
                }
            }
        }
        LLog.i("AppFileMgr-->>getFileSize", "This file size: " + size);
        return size;
    }

    /**
     * 删除方法, 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理
     *
     * @param directory
     * @return void
     */
    public static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File file : directory.listFiles()) {
                if (file.isDirectory())
                    deleteFilesByDirectory(file);
                file.delete();
            }
            LLog.i("AppFileMgr-->>deleteFilesByDirectory", "This directory is not file, execute delete");
        } else {
            LLog.i("AppFileMgr-->>deleteFilesByDirectory", "This directory is file, not execute delete");
        }
    }

}
