package com.mymxhbyf.dongjk.lattecore.net;

import android.content.Context;

import com.mymxhbyf.dongjk.lattecore.net.callback.IError;
import com.mymxhbyf.dongjk.lattecore.net.callback.IFailure;
import com.mymxhbyf.dongjk.lattecore.net.callback.IRequest;
import com.mymxhbyf.dongjk.lattecore.net.callback.ISuccess;
import com.mymxhbyf.dongjk.lattecore.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by DongJK on 2018/1/19.
 */

public class RestClientBuilder {

    private String mUrl = null;
    private static final Map<String,Object> PARAMS = RestCreator.getParams();
    private IRequest mIRequest = null;
    private ISuccess mISuccess = null;
    private IFailure mIFailure = null;
    private IError mIError = null;
    private RequestBody mBody = null;
    private Context mContext = null;
    //upload
    private File mFile = null;
    //download
    private  String mDownloadDir = null;
    private  String mExtenson = null;
    private  String mName = null;

    private LoaderStyle mLoaderStyle = null;

    //只允许同包的RestClient去new
    RestClientBuilder(){

    }

    public final RestClientBuilder url(String url){
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String,Object> params){
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key,Object value){
       PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder file(File file){
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String file){
        this.mFile = new File(file);
        return this;
    }

    //文件下载后存放在哪个目录
    public final RestClientBuilder dir(String dir){
        this.mDownloadDir = dir;
        return this;
    }

    //文件后缀名
    public final RestClientBuilder extension(String extension){
        this.mExtenson = extension;
        return this;
    }

    public final RestClientBuilder raw(String raw){
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),raw);
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest){
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess){
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure){
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder request(IRequest iRequest){
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder error(IError iError){
        this.mIError = iError;
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

    public final RestClientBuilder loader(Context context,LoaderStyle style){
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public final RestClientBuilder loader(Context context){
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RestClient build(){
        return new RestClient(mUrl,PARAMS,mIRequest,mDownloadDir,mExtenson,mName,mISuccess,mIFailure,mIError,mBody,mLoaderStyle,mFile,mContext);
    }
}
