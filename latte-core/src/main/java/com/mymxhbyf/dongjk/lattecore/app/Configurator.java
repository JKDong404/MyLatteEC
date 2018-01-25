package com.mymxhbyf.dongjk.lattecore.app;


import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.WeakHashMap;

import okhttp3.Interceptor;

/**
 * Created by DongJK on 2017/12/28.
 */

public class Configurator {

    private static final WeakHashMap<Object,Object> LATTE_CONFIGS = new WeakHashMap<>();
    //字体图标相关
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    //拦截器相关
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();


    private Configurator(){
        LATTE_CONFIGS.put(ConfigType.CONFIG_REDAY,false);
    }

    //线程安全的懒汉模式
    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    final WeakHashMap<Object,Object> getLatteConfigs(){
        return LATTE_CONFIGS;
    }

    //静态内部类式的初始化
    private static class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void configure(){//配置完成
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_REDAY,true);
    }


    //配置API_HOST
    public final Configurator withApiHost(String host){
        LATTE_CONFIGS.put(ConfigType.API_HOST,host);
        return this;
    }

    public final Configurator withLoaderDelayed(long delayed){
        LATTE_CONFIGS.put(ConfigType.LOADER_DELAYED,delayed);
        return this;
    }

    private void initIcons(){
        if (ICONS.size() > 0){
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i ++){
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor){
        ICONS.add(descriptor);
        return this;
    }

    //拦截器相关
    public final Configurator withInterceptor(Interceptor interceptor){
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigType.INTERCEPTOR,INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors){
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigType.INTERCEPTOR,INTERCEPTORS);
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
    final <T> T  getConfiguration(Object key){
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key);

    }
}
