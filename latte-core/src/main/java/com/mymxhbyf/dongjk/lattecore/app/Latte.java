package com.mymxhbyf.dongjk.lattecore.app;

import android.content.Context;
import android.os.Handler;

import java.util.WeakHashMap;

/**
 * Created by DongJK on 2017/12/28.
 */

public final class Latte {

    public static Configurator init(Context context){
        Configurator
                .getInstance()
                .getLatteConfigs()
                .put(ConfigType.APPLICATION_CONTEXT,context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator(){
        return Configurator.getInstance();
    }

    public static  <T> T getConfiguration(Object key){
        return Configurator.getInstance().getConfiguration(key);
    }

//    public static WeakHashMap<String,Object> getConfigurations(){
//        return Configurator.getInstance().getLatteConfigs();
//    }

    public static Context getApplicationContext(){
        return getConfiguration(ConfigType.APPLICATION_CONTEXT);
    }

    public static Handler getHandler(){
        return getConfiguration(ConfigType.HANDLER);
    }
}
