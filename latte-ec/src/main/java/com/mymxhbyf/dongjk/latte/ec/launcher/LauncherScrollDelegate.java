package com.mymxhbyf.dongjk.latte.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.mymxhbyf.dongjk.latte.ec.R;
import com.mymxhbyf.dongjk.lattecore.app.AccoutManager;
import com.mymxhbyf.dongjk.lattecore.app.IUserChecker;
import com.mymxhbyf.dongjk.lattecore.delegates.LatteDelegate;
import com.mymxhbyf.dongjk.lattecore.ui.launcher.ILauncherListener;
import com.mymxhbyf.dongjk.lattecore.ui.launcher.LauncherHolderCreator;
import com.mymxhbyf.dongjk.lattecore.ui.launcher.OnLauncherFinishTag;
import com.mymxhbyf.dongjk.lattecore.ui.launcher.ScrollLauncherTag;
import com.mymxhbyf.dongjk.lattecore.util.storage.LattePreference;

import java.util.ArrayList;

/**
 * 轮播图
 * Created by DongJK on 2018/1/24.
 */

public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> mConvenientBanner = null;
    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();

    private ILauncherListener mILauncherListener = null;


    private void initBanner() {
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);

        mConvenientBanner.setPages(new LauncherHolderCreator(),INTEGERS)
                .setPageIndicator(new int[]{R.drawable.dot_normal,R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .setCanLoop(false);//设置不可循环
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener){
            mILauncherListener = (ILauncherListener) activity;
        }
    }


    @Override
    public Object setLayout() {
        mConvenientBanner = new ConvenientBanner<Integer>(getContext());
        return mConvenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }


    @Override
    public void onItemClick(int position) {
        //如果点击的是最后一个
        if (position == INTEGERS.size() - 1){
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(),true);
            //检查用户是否已经登录
            AccoutManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {//已经登录，进行onSignIn回调
                    if (mILauncherListener != null){
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {//未登录，进行onNotSignIn回调
                    if (mILauncherListener != null){
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNDE);
                    }
                }
            });
        }

    }
}
