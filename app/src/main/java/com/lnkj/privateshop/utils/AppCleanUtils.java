package com.lnkj.privateshop.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.text.DecimalFormat;

/**
 * Created by lnkj on 2017/6/2 0002.
 */

public class AppCleanUtils {

    /**
     * 获取App应用缓存的大小
     *
     * @param context 上下文
     * @return String
     */
    public static String getAppClearSize(Context context) {
        long clearSize = 0;
        String fileSizeStr = "";
        DecimalFormat df = new DecimalFormat("0.00");
        //获得应用内部缓存大小
        clearSize = AppFileMgr.getFileSize(context.getCacheDir());
        //获得应用SharedPreference缓存数据大小
        clearSize += AppFileMgr.getFileSize(new File("/data/data/" + context.getPackageName() + "/shared_prefs"));
        //获得应用data/data/com.xxx.xxx/files下的内容文件大小
        clearSize += AppFileMgr.getFileSize(context.getFilesDir());
        //获取应用外部缓存大小
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            clearSize += AppFileMgr.getFileSize(context.getExternalCacheDir());
        }
        if (clearSize > 5000) {
            //转换缓存大小Byte为MB
            fileSizeStr = df.format((double) clearSize / 1048576) + "MB";
        }
        return fileSizeStr;
    }

    /**
     * 清除本应用内部缓存数据(/data/data/com.xxx.xxx/cache)
     * @param context 上下文
     * @return void
     */
    public static void cleanInternalCache(Context context) {
        AppFileMgr.deleteFilesByDirectory(context.getCacheDir());
        LLog.i("AppCleanMgr->>cleanInternalCache", "清除本应用内部缓存(/data/data/" + context.getPackageName() + "/cache)");
    }

    /**
     * 清除本应用外部缓存数据(/mnt/sdcard/android/data/com.xxx.xxx/cache)
     * @param context 上下文
     * @return void
     */
    public static void cleanExternalCache(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            AppFileMgr.deleteFilesByDirectory(context.getExternalCacheDir());
            LLog.i("AppCleanMgr->>cleanExternalCache", "清除本应用外部缓存数据(/mnt/sdcard/android/data/" + context.getPackageName() + "/cache)");
        }
    }



}
