package com.mymxhbyf.dongjk.lattecore.app;

import com.mymxhbyf.dongjk.lattecore.util.storage.LattePreference;

/**
 * 管理用户信息
 * Created by DongJK on 2018/1/26.
 */

public class AccoutManager {

    private enum SignTag{
        SIGN_TAG
    }

    //保存用户登录状态，登陆后调用
    public static void setSignState(boolean state){
        LattePreference.setAppFlag(SignTag.SIGN_TAG.name(),state);
    }

    //判断是否已经登录
    private static boolean isSignIn(){
        return LattePreference.getAppFlag(SignTag.SIGN_TAG.name());
    }

    public static void checkAccount(IUserChecker checker){
        if (isSignIn()){
            checker.onSignIn();
        }else {
            checker.onNotSignIn();
        }
    }
}
