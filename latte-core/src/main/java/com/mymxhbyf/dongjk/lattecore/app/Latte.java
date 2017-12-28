package com.mymxhbyf.dongjk.lattecore.app;

import android.content.Context;

import java.util.WeakHashMap;

/**
 * Created by DongJK on 2017/12/28.
 */

public final class Latte {

    public static Configurator init(Context context){
        getconfigurations().put(ConfigType.APPLICATION_CONTEXT.name(),context.getApplicationContext());
        return Configurator.getInstance();
    }

    private static WeakHashMap<String,Object> getconfigurations(){
        return Configurator.getInstance().getLatteConfigs();
    }
}
