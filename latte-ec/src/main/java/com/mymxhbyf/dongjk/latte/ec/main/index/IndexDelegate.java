package com.mymxhbyf.dongjk.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;
import com.mymxhbyf.dongjk.latte.ec.R;
import com.mymxhbyf.dongjk.latte.ec.R2;
import com.mymxhbyf.dongjk.lattecore.delegates.bottom.BottomItemDelegate;
import com.mymxhbyf.dongjk.lattecore.ui.recycler.BaseDecoration;
import com.mymxhbyf.dongjk.lattecore.ui.refresh.RefreshHandler;

import butterknife.BindView;

/**
 * Created by DongJK on 2018/1/29.
 */

public class IndexDelegate extends BottomItemDelegate{

    @BindView(R2.id.rv_index)
    RecyclerView mRecylerView = null;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLyout = null;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar = null;
    @BindView(R2.id.icon_index_scan)
    IconTextView mIconScan = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView = null;
    @BindView(R2.id.icon_index_message)
    IconTextView mIconMessage = null;

    private RefreshHandler mRefreshHandler = null;

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler = RefreshHandler.create(mRefreshLyout,mRecylerView,new IndexDataConverter());
    }


    //初始化 refresh
    private void initRefreshLayout(){
        mRefreshLyout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        mRefreshLyout.setProgressViewOffset(true,120,300);

    }

    //设置recyclerView布局
    private void initRecyclerView(){
        final GridLayoutManager manager = new GridLayoutManager(getContext(),4);
        mRecylerView.setLayoutManager(manager);
        //设置分割线
        mRecylerView.addItemDecoration(BaseDecoration.create(ContextCompat.getColor(getContext(),R.color.app_background),5));
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
       mRefreshHandler.firstPage("http://127.0.0.1/index");
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }


}
