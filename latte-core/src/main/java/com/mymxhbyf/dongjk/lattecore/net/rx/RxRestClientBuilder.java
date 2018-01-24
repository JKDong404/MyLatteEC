package com.mymxhbyf.dongjk.lattecore.net.rx;

import android.content.Context;

import com.mymxhbyf.dongjk.lattecore.net.RestCreator;
import com.mymxhbyf.dongjk.lattecore.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by DongJK on 2018/1/19.
 */

public class RxRestClientBuilder {

    private String mUrl = null;
    private static final Map<String,Object> PARAMS = RestCreator.getParams();

    private RequestBody mBody = null;
    private Context mContext = null;
    //upload
    private File mFile = null;

    private LoaderStyle mLoaderStyle = null;

    //只允许同包的RestClient去new
    RxRestClientBuilder(){

    }

    public final RxRestClientBuilder url(String url){
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder params(WeakHashMap<String,Object> params){
        PARAMS.putAll(params);
        return this;
    }

    public final RxRestClientBuilder params(String key, Object value){
       PARAMS.put(key, value);
        return this;
    }

    public final RxRestClientBuilder file(File file){
        this.mFile = file;
        return this;
    }

    public final RxRestClientBuilder file(String file){
        this.mFile = new File(file);
        return this;
    }

    public final RxRestClientBuilder raw(String raw){
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }


/* //判空
    private Map<String,Object> checkParams() {
        if (mParams == null){
            return new WeakHashMap<>();
        }
        return mParams;
    }
*/

    public final RxRestClientBuilder loader(Context context, LoaderStyle style){
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public final RxRestClientBuilder loader(Context context){
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RxRestClient build(){
        return new RxRestClient(mUrl,PARAMS,mBody,mLoaderStyle,mFile,mContext);
    }
}
