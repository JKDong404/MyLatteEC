package com.mymxhbyf.dongjk.lattecore.app;

import android.graphics.Bitmap;

import java.util.WeakHashMap;

/**
 * Created by DongJK on 2017/12/28.
 */

public class Configurator {

    private static final WeakHashMap<String,Object> LATTE_CONFIGS = new WeakHashMap<>();

    private Configurator(){
        LATTE_CONFIGS.put(ConfigType.CONFIG_REDAY.name(),false);
    }

    //线程安全的懒汉模式
    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    final WeakHashMap<String,Object> getLatteConfigs(){
        return LATTE_CONFIGS;
    }

    //静态内部类式的初始化
    private static class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void configure(){//配置完成
        LATTE_CONFIGS.put(ConfigType.CONFIG_REDAY.name(),true);
    }


    //以下为配置API_HOST
    public final Configurator withApiHost(String host){
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(),host);
        return this;
    }

    /**
     * 监测配置是否完成
     */
    private void checkConfiguration(){
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_REDAY);
        if (!isReady){
            throw new RuntimeException("Configuration is not ready, call configure");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T  getConfiguration(Enum<ConfigType> key){
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());

    }
}
