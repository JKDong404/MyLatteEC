package com.mymxhbyf.dongjk.latteec;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mymxhbyf.dongjk.latte.ec.launcher.LauncherDelegate;
import com.mymxhbyf.dongjk.latte.ec.launcher.LauncherScrollDelegate;
import com.mymxhbyf.dongjk.lattecore.activities.ProxyActivity;
import com.mymxhbyf.dongjk.lattecore.delegates.LatteDelegate;

public class MainActivity extends ProxyActivity{


    @Override
    public LatteDelegate setRootDelegate() {
        return new LauncherScrollDelegate();
    }
}
