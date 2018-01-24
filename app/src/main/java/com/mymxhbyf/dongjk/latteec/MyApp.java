package com.mymxhbyf.dongjk.latteec;

import android.app.Application;

import com.mymxhbyf.dongjk.lattecore.app.Latte;
import com.mymxhbyf.dongjk.lattecore.net.interceptors.DebugInterceptor;

/**
 * Created by DongJK on 2017/12/28.
 */

public class MyApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .configure();
    }
}
