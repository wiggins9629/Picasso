package com.wiggins.picasso.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;
import com.wiggins.picasso.utils.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyApplication extends Application {

    public static Context mContext;
    private static List<Activity> activityList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();

        initPicasso();
    }

    /**
     * @Description 初始化Picasso配置
     */
    private void initPicasso() {
        Picasso.Builder pb = new Picasso.Builder(this);
        Picasso picasso = pb
                .downloader(new OkHttpDownloader(new File(FileUtil.getCachePath(this))))
                .build();
        Picasso.setSingletonInstance(picasso);
    }

    public static Context getContext() {
        return mContext;
    }

    /**
     * @Description 添加Activity到activityList，在onCreate()中调用
     */
    public static void addActivity(Activity activity) {
        if (activityList != null && activityList.size() > 0) {
            if (!activityList.contains(activity)) {
                activityList.add(activity);
            }
        } else {
            activityList.add(activity);
        }
    }

    /**
     * @Description 结束Activity到activityList，在onDestroy()中调用
     */
    public static void finishActivity(Activity activity) {
        if (activity != null && activityList != null && activityList.size() > 0) {
            activityList.remove(activity);
        }
    }

    /**
     * @Description 结束所有Activity
     */
    public static void finishAllActivity() {
        if (activityList != null && activityList.size() > 0) {
            for (Activity activity : activityList) {
                if (null != activity) {
                    activity.finish();
                }
            }
        }
        activityList.clear();
    }
}
