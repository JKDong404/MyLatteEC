package com.mymxhbyf.dongjk.latteec;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.mymxhbyf.dongjk.latte.ec.launcher.LauncherDelegate;
import com.mymxhbyf.dongjk.latte.ec.launcher.LauncherScrollDelegate;
import com.mymxhbyf.dongjk.latte.ec.main.EcBottomDelegate;
import com.mymxhbyf.dongjk.latte.ec.main.sort.SortDelegate;
import com.mymxhbyf.dongjk.latte.ec.sign.ISignListener;
import com.mymxhbyf.dongjk.latte.ec.sign.SignInDelegate;
import com.mymxhbyf.dongjk.latte.ec.sign.SignUpDelegate;
import com.mymxhbyf.dongjk.lattecore.activities.ProxyActivity;
import com.mymxhbyf.dongjk.lattecore.app.Latte;
import com.mymxhbyf.dongjk.lattecore.delegates.LatteDelegate;
import com.mymxhbyf.dongjk.lattecore.ui.launcher.ILauncherListener;
import com.mymxhbyf.dongjk.lattecore.ui.launcher.OnLauncherFinishTag;

import qiu.niorgai.StatusBarCompat;

public class MainActivity extends ProxyActivity implements ISignListener,ILauncherListener{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        Latte.getConfigurator().withActivity(this);
        //去掉titlebar
        StatusBarCompat.translucentStatusBar(this,true);
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag){
            case SIGNED://启动结束，用户登录完成
                startWithPop(new EcBottomDelegate());
                break;

            case NOT_SIGNDE://启动结束，用户未登录
                startWithPop(new SignInDelegate());
                break;
        }
    }
}
