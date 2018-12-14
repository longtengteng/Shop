package com.lnkj.privateshop.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;

import com.lnkj.privateshop.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangyf on 2016/10/24.
 */
public class FileUtil {
    private static final String TAG = "FileUtil";
    private static final String FOLDER = "PrvateShop";
    private static final String FOLDER_SEPARATOR = "/";
    private static final char EXTENSION_SEPARATOR = '.';

    static public String SDPATH = getSDPath();

    static public String getSDPath() {
        File sdDir = null;
        File imageDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();// 获取根目录
            String path = sdDir.getPath() + File.separator + FOLDER + File.separator;
            imageDir = new File(path);
            if (!imageDir.exists()) {
                // 若不存在，创建目录，可以在应用启动的时候创建
                imageDir.mkdirs();
            }
        }
        if (imageDir == null){

            return null;
        }
        return sdDir.getPath() + File.separator + FOLDER + File.separator;
    }

    static public boolean isFileExist(String fileName) {
        File file = new File(SDPATH + fileName);
        file.isFile();
        if (file.exists()){
            return true;
        }
        deleteExistFile(fileName);
        return false;
    }

    public static void deleteExistFile(String fileName){
        File file = new File(SDPATH + fileName);
        if (file.isDirectory()){

            for (File element : file.listFiles()) {
                if (element.isFile())
                    element.delete(); // 删除所有文件
            }
        }else{
            file.delete();
        }
    }

    public static String getFileName(String id,String attem_path){
        if (null != attem_path && !"".equals(attem_path)){

            return id + "_"+attem_path.substring(attem_path.lastIndexOf("/") + 1);
        }
        return "";
    }

    public static List<String> getDownedDataList(List<String> pathList,String path) {

        if (null == pathList){
            pathList = new ArrayList<>();

        }
        try {

            File dirFile = new File(path);
            //如果dir对应的文件不存在，或者不是一个目录，则退出
            if (!dirFile.exists() || !dirFile.isDirectory()) {
                return pathList;
            }
            //删除文件夹下的所有文件(包括子目录)
            File[] files = dirFile.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    pathList.add(files[i].getAbsolutePath());
                }else {
                    getDownedDataList(pathList,files[i].getPath());
                }
            }
            return pathList;

        }catch (Exception e){
            Log.e("filepath","file--list---Exception--e-->>"+e);
        }

        return pathList;

    }
    static public String saveBitmapPath(Bitmap bm) {
        getSDPath();
        String ranDom=String.valueOf( (int) ((Math.random() * 9 + 1) * 1000));
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyyMMddHHmmss");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String picName = formatter.format(curDate)+ranDom;
        if (bm == null || TextUtils.isEmpty(picName))
            return "";
        try {
            if (!isFileExist("")) {
                File tempf = createSDDir("");
            }
            File f = new File(SDPATH, picName + ".jpg");
            if (f.exists()) {
                f.delete();
            }
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            return SDPATH + picName + ".jpg";
        } catch (FileNotFoundException e) {
            return "";
        } catch (IOException e) {
            return "";
        }
    }
    static public File createSDDir(String dirName) throws IOException {
        File dir = new File(SDPATH + dirName);
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {

        }
        return dir;
    }

    public static String readAssertResource(Context context, String strAssertFileName) {
        AssetManager assetManager = context.getAssets();
        String strResponse = "";
        try {
            InputStream ims = assetManager.open(strAssertFileName);
            strResponse = getStringFromInputStream(ims);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strResponse;
    }

    private static String getStringFromInputStream(InputStream a_is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(a_is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
        }
        return sb.toString();
    }

    public static Bitmap getVideoThumbnail(Context context, ContentResolver cr, String testVideopath) {
        // final String testVideopath = "/mnt/sdcard/sidamingbu.mp4";
        ContentResolver testcr = context.getContentResolver();
        String[] projection = { MediaStore.Video.Media.DATA, MediaStore.Video.Media._ID, };
        String whereClause = MediaStore.Video.Media.DATA + " = '" + testVideopath + "'";
        Cursor cursor = testcr.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, projection, whereClause,
                null, null);
        int _id = 0;
        String videoPath = "";
        if (cursor == null || cursor.getCount() == 0) {
            return null;
        }
        if (cursor.moveToFirst()) {

            int _idColumn = cursor.getColumnIndex(MediaStore.Video.Media._ID);
            int _dataColumn = cursor.getColumnIndex(MediaStore.Video.Media.DATA);

            do {
                _id = cursor.getInt(_idColumn);
                videoPath = cursor.getString(_dataColumn);
                System.out.println(_id + " " + videoPath);
            } while (cursor.moveToNext());
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inDither = false;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = MediaStore.Video.Thumbnails.getThumbnail(cr, _id, MediaStore.Images.Thumbnails.MICRO_KIND,
                options);
        return bitmap;
    }

    //根据文件路径获取缩略图
    public static Bitmap getBitmapFromFile(Context context,String videoPath) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        /**
         * android系统中为我们提供了ThumbnailUtils工具类来获取缩略图的处理。
         * ThumbnailUtils.createVideoThumbnail(filePath, kind)
         *          创建视频缩略图，filePath:文件路径，kind：MINI_KIND or MICRO_KIND
         * ThumbnailUtils.extractThumbnail(bitmap, width, height)
         *          将bitmap裁剪为指定的大小
         * ThumbnailUtils.extractThumbnail(bitmap, width, height, options)
         *          将bitmap裁剪为指定的大小，可以有参数BitmapFactory.Options参数
         *
         */
        Bitmap bitmap = null;
        bitmap = ThumbnailUtils.createVideoThumbnail(videoPath, MediaStore.Images.Thumbnails.MINI_KIND);

        //获取图片后，我们队图片进行压缩，获取指定大小
        if(bitmap != null){
            //裁剪大小
            bitmap = ThumbnailUtils.extractThumbnail(bitmap,
                    (int)(100*metrics.density), (int)(100*metrics.density));
        }else{//如果为空，采用我们的默认图片
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        }
        return  bitmap;
    }

    /** 保存方法 */
    public static String saveBitmap(Bitmap bm) {
        String imgUrl;
        File appDir = new File(Environment.getExternalStorageDirectory(), "Boohee");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = "head" + ".jpg";
        imgUrl=Environment.getExternalStorageDirectory()+"/Boohee/"+"head.jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imgUrl;
    }
}
