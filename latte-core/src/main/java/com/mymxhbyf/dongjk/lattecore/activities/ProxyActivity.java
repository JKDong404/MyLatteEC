package com.mymxhbyf.dongjk.lattecore.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.mymxhbyf.dongjk.lattecore.R;
import com.mymxhbyf.dongjk.lattecore.delegates.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * 作为一个容器，盛放fragment
 * Created by DongJK on 2018/1/18.
 */

public abstract class ProxyActivity extends SupportActivity{
    //返回根Delegate
    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState){
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState == null){
            loadRootFragment(R.id.delegate_container,setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //垃圾回收，java规定默认一定执行
        System.gc();
        System.runFinalization();
    }
}
