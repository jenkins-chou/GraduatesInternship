package com.jenking.graduatesinternship.application;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import org.xutils.x;

public class App extends Application{

    public static App appOS;

    @Override
    public void onCreate() {
        super.onCreate();
        appOS = this;
        //文件上传
        x.Ext.init(this);
        //x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}