package com.mymxhbyf.dongjk.latteec;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.mymxhbyf.dongjk.lattecore.net.callback.IError;
import com.mymxhbyf.dongjk.lattecore.net.callback.IFailure;
import com.mymxhbyf.dongjk.lattecore.net.callback.ISuccess;
import com.mymxhbyf.dongjk.lattecore.delegates.LatteDelegate;
import com.mymxhbyf.dongjk.lattecore.net.RestClient;
import com.mymxhbyf.dongjk.lattecore.util.log.LatteLogger;

/**
 * Created by DongJK on 2018/1/18.
 */

public class AppDelegate extends LatteDelegate{
    @Override
    public Object setLayout() {
        return R.layout.delegate_app;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient(){
        RestClient.builder()
                .url("http://127.0.0.1/index")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(),response,Toast.LENGTH_SHORT).show();
                        LatteLogger.json("gogogog",response);
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();

    }
}
