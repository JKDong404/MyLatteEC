package com.mymxhbyf.dongjk.latteec;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mymxhbyf.dongjk.latte.ec.launcher.LauncherDelegate;
import com.mymxhbyf.dongjk.latte.ec.launcher.LauncherScrollDelegate;
import com.mymxhbyf.dongjk.latte.ec.sign.SignInDelegate;
import com.mymxhbyf.dongjk.latte.ec.sign.SignUpDelegate;
import com.mymxhbyf.dongjk.lattecore.activities.ProxyActivity;
import com.mymxhbyf.dongjk.lattecore.delegates.LatteDelegate;

public class MainActivity extends ProxyActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
    }

    @Override
    public LatteDelegate setRootDelegate() {
        return new SignInDelegate();
    }
}
