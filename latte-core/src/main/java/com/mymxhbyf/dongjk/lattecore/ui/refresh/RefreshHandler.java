package com.mymxhbyf.dongjk.lattecore.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mymxhbyf.dongjk.lattecore.app.Latte;
import com.mymxhbyf.dongjk.lattecore.net.RestClient;
import com.mymxhbyf.dongjk.lattecore.net.callback.IError;
import com.mymxhbyf.dongjk.lattecore.net.callback.IFailure;
import com.mymxhbyf.dongjk.lattecore.net.callback.ISuccess;
import com.mymxhbyf.dongjk.lattecore.ui.recycler.DataConverter;
import com.mymxhbyf.dongjk.lattecore.ui.recycler.MultipleRecyclerAdapter;
import com.mymxhbyf.dongjk.lattecore.util.log.LatteLogger;

/**
 * Created by DongJK on 2018/1/29.
 */

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener,BaseQuickAdapter.RequestLoadMoreListener{

    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingBean BEAN;
    private final RecyclerView RECYCLERVIEW;
    private MultipleRecyclerAdapter mAdapter = null;
    private final DataConverter CONVERTER;



    private RefreshHandler(SwipeRefreshLayout swipeRefreshLayout, RecyclerView recyclerView, DataConverter converter, PagingBean bean) {
        this.REFRESH_LAYOUT = swipeRefreshLayout;
        this.RECYCLERVIEW = recyclerView;
        this.CONVERTER = converter;
        this.BEAN = bean;
        REFRESH_LAYOUT.setOnRefreshListener(this);
    }
    public static RefreshHandler create(SwipeRefreshLayout swipeRefreshLayout,RecyclerView recyclerView,DataConverter converter){
        return new RefreshHandler(swipeRefreshLayout,recyclerView,converter,new PagingBean());
    }


    private void refresh(){
        REFRESH_LAYOUT.setRefreshing(true);
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //在这里可以进行网络请求

                //refresh 消失，可放在网络请求sucess中
                REFRESH_LAYOUT.setRefreshing(false);
            }
        },2000);
    }

    public void firstPage(String url){
        RestClient.builder()
                .url(url)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                    //    LatteLogger.json("refresh11",response.toString());
                        final JSONObject object = JSON.parseObject(response);
                        BEAN.setmTotal(object.getInteger("total"))
                                .setmPageSize(object.getInteger("page_size"));
//                        BEAN.setmTotal(20)
//                                .setmPageSize(6);
                        //设置adapter
                        mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(response));
                        mAdapter.setOnLoadMoreListener(RefreshHandler.this,RECYCLERVIEW);
                        RECYCLERVIEW.setAdapter(mAdapter);
                        BEAN.addIndex();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        LatteLogger.json("onFailure","failure");
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        LatteLogger.json("onError",code + msg);

                    }
                })
                .build()
                .get();
    }

    @Override
    public void onRefresh() {
        refresh();
    }

    /**
     * 上拉加载更多
     */
    @Override
    public void onLoadMoreRequested() {

    }
}
