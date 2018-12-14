package com.lnkj.privateshop;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.lnkj.privateshop.api.ApiUtils;
import com.lnkj.privateshop.ease.DemoHelper;
import com.lnkj.privateshop.injector.component.ApplicationComponent;
import com.lnkj.privateshop.injector.component.DaggerApplicationComponent;
import com.lnkj.privateshop.injector.mudules.ApplicationModule;
import com.lnkj.privateshop.utils.HttpUtil;
import com.lnkj.privateshop.utils.PicassoImageLoader;
import com.lnkj.privateshop.utils.ToastUtil;
import com.lnkj.privateshop.view.ProgreesDialog;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;
import com.pgyersdk.crash.PgyCrashManager;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import cn.jpush.android.api.JPushInterface;
import okhttp3.OkHttpClient;


/**
 * Created by sll on 2016/3/8.
 */
public class MyApplication extends Application {

    private ApplicationComponent mApplicationComponent;
    public static OkHttpClient mOkHttpClientt;
    @Inject
    OkHttpClient mOkHttpClient;
    ApiUtils meApi = new ApiUtils();
    public static Context mContext;


    public static MyApplication instance;

    public static MyApplication getInstance() {

        return instance;

    }
    public static OkHttpClient getOkHttpClient() {

        return mOkHttpClientt;

    }

    public static Context getApplication() {
        return instance;
    }

    @Override

    public void onCreate() {

        super.onCreate();
        mContext =this;
        instance = this;

        PgyCrashManager.register(this);

        DemoHelper.getInstance().init(this);
//        EaseHelper.getInstance().init(this);

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        initComponent();

//        initFrescoConfig();
        mOkHttpClientt = new OkHttpClient();
        HttpUtil.getApi();
        // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext

//        SDKInitializer.initialize(this);
        ToastUtil.register(this);

        PgyCrashManager.register(this);

        ProgreesDialog.setDialog(this);

        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new PicassoImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(false); //是否按矩形区域保存
        imagePicker.setSelectLimit(9);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.CIRCLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void initComponent() {
        mApplicationComponent =
                DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }


    private static final int MAX_HEAP_SIZE = (int) Runtime.getRuntime().maxMemory();

//    public static final int MAX_DISK_CACHE_SIZE = 50 * ByteConstants.MB;
    public static final int MAX_MEMORY_CACHE_SIZE = MAX_HEAP_SIZE / 8;

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

//    private void initFrescoConfig() {
//        final MemoryCacheParams bitmapCacheParams =
//                new MemoryCacheParams(MAX_MEMORY_CACHE_SIZE, // Max total size of elements in the cache
//                        Integer.MAX_VALUE,                     // Max entries in the cache
//                        MAX_MEMORY_CACHE_SIZE, // Max total size of elements in eviction queue
//                        Integer.MAX_VALUE,                     // Max length of eviction queue
//                        Integer.MAX_VALUE);
//        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory.newBuilder(this, mOkHttpClient)
//                .setProgressiveJpegConfig(new SimpleProgressiveJpegConfig())
//                .setBitmapMemoryCacheParamsSupplier(new Supplier<MemoryCacheParams>() {
//                    public MemoryCacheParams get() {
//                        return bitmapCacheParams;
//                    }
//                })
//                .setMainDiskCacheConfig(
//                        DiskCacheConfig.newBuilder(this).setMaxCacheSize(MAX_DISK_CACHE_SIZE).build())
//                .setDownsampleEnabled(true)
//                .build();
//        Fresco.initialize(this, config);
//    }


}
