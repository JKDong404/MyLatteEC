package com.mymxhbyf.dongjk.latte.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.mymxhbyf.dongjk.latte.ec.R;


import com.mymxhbyf.dongjk.latte.ec.R2;
import com.mymxhbyf.dongjk.lattecore.app.AccoutManager;
import com.mymxhbyf.dongjk.lattecore.app.IUserChecker;
import com.mymxhbyf.dongjk.lattecore.delegates.LatteDelegate;
import com.mymxhbyf.dongjk.lattecore.ui.launcher.ILauncherListener;
import com.mymxhbyf.dongjk.lattecore.ui.launcher.OnLauncherFinishTag;
import com.mymxhbyf.dongjk.lattecore.ui.launcher.ScrollLauncherTag;
import com.mymxhbyf.dongjk.lattecore.util.storage.LattePreference;
import com.mymxhbyf.dongjk.lattecore.util.timer.BaseTimerTask;
import com.mymxhbyf.dongjk.lattecore.util.timer.ITimerListener;

import java.text.MessageFormat;
import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * splash 倒计时
 * 因为是library,所以需要先编译一下
 * Created by DongJK on 2018/1/24.
 */

public class LauncherDelegate extends LatteDelegate implements ITimerListener{

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvTimer = null;

    private Timer mTimer = null;
    private int mCount = 5;

    private ILauncherListener mILauncherListener = null;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView(){//跳过按钮
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    private void initTimer(){
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task,0,1000);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener){
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    //判断是否显示滑动启页
    private void checkIsShowScroll(){
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())){
            //第一次登录，进入轮播图
            start(new LauncherScrollDelegate(),SINGLETASK);
        }else {
            //检查用户是否登陆了app
            AccoutManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null){
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null){
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNDE);
                    }
                }
            });
        }


    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();//倒计时
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null){
                    mTvTimer.setText(MessageFormat.format("跳过\n{0}s",mCount));
                    mCount--;
                    if (mCount < 0){
                        if (mTimer != null){
                            mTimer.cancel();
                            mTimer = null;
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }
}
