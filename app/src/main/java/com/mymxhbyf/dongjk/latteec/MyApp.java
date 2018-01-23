package com.mymxhbyf.dongjk.latteec;

import android.app.Application;

import com.mymxhbyf.dongjk.lattecore.app.Latte;

/**
 * Created by DongJK on 2017/12/28.
 */

public class MyApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withApiHost("http://127.0.0.1/")
                .configure();
    }
}
