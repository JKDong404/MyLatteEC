package com.mymxhbyf.dongjk.lattecore.net.download;

import android.os.AsyncTask;

import com.mymxhbyf.dongjk.lattecore.net.callback.IError;
import com.mymxhbyf.dongjk.lattecore.net.callback.IFailure;
import com.mymxhbyf.dongjk.lattecore.net.callback.IRequest;
import com.mymxhbyf.dongjk.lattecore.net.callback.ISuccess;
import com.mymxhbyf.dongjk.lattecore.net.RestCreator;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by DongJK on 2018/1/23.
 */

public class DownloadHandler {

    private final String URL;
    private static final WeakHashMap<String,Object> PARAMS = RestCreator.getParams() ;
    private final IRequest REQUEST;
    //download
    private final String DOWNLOAD_DIR;//目录
    private final String EXTENSION;//后缀
    private final String NAME;//下载的文件名
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;


    public DownloadHandler(String url,
                           IRequest request,
                           String downloadDir,
                           String extension,
                           String name,
                           ISuccess success,
                           IFailure failure,
                           IError error) {
        this.URL = url;
        this.REQUEST = request;
        this.DOWNLOAD_DIR = downloadDir;
        this.EXTENSION = extension;
        this.NAME = name;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
    }

    public final void handlerDownload(){
        if (REQUEST != null){
            REQUEST.onRequestStart();
        }

        RestCreator.getRestService().download(URL,PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            final ResponseBody responseBody =  response.body();

                            final SaveFileTask task = new SaveFileTask(REQUEST,SUCCESS);
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,DOWNLOAD_DIR,EXTENSION,responseBody,NAME);

                            //一定要判断，否则文件加载不全
                            if (task.isCancelled()){
                                if (REQUEST != null){
                                    REQUEST.onRequestEnd();
                                }
                            }else {
                                if (ERROR != null){
                                    ERROR.onError(response.code(),response.message());
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (FAILURE != null){
                            FAILURE.onFailure();
                        }
                    }
                });


    }
}
