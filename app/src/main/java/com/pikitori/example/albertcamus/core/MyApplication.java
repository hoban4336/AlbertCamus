package com.pikitori.example.albertcamus.core;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by admin on 2016-12-28.
 */

public class MyApplication extends Application {

    private static final String TAG = "MyApplication";
    @Override
    public void onCreate() {
        super.onCreate();

        Context context  = getApplicationContext();

        File cacheDir = StorageUtils.getCacheDirectory( context );

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder( context )
                .memoryCacheExtraOptions(480,800)
                .discCacheExtraOptions(480,800, Bitmap.CompressFormat.JPEG, 75, null)
//                .taskExecutor()
//                .taskExecutorForCachedImages()
                .threadPoolSize(3)
                .threadPriority(Thread.NORM_PRIORITY -1)
                .tasksProcessingOrder(QueueProcessingType.FIFO)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .memoryCacheSizePercentage(13)
                .discCache(new UnlimitedDiscCache(cacheDir))
                .discCacheSize(50* 1024 * 1024 )
                .discCacheFileCount(100)
                .discCacheFileNameGenerator(new HashCodeFileNameGenerator())
                .imageDownloader(new BaseImageDownloader(context))
//                .imageDecoder(new BaseImageDecoder())
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .writeDebugLogs()
                .build();

        ImageLoader.getInstance().init( config );


        Log.d(TAG, "onCreate()");
    }
}
