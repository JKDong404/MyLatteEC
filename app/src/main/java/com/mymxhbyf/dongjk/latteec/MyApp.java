package com.mymxhbyf.dongjk.latteec;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.mymxhbyf.dongjk.latte.ec.database.DatabaseManager;
import com.mymxhbyf.dongjk.latte.ec.icon.FontEcModule;
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
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withLoaderDelayed(1000)
                .withApiHost("http://127.0.0.1/")
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .configure();

        DatabaseManager.getInstance().init(this);
    }
}
