package com.mymxhbyf.dongjk.lattecore.wechat.templates;


import com.mymxhbyf.dongjk.lattecore.wechat.BaseWXEntryActivity;
import com.mymxhbyf.dongjk.lattecore.wechat.LatteWeChat;

/**
 * Created by DongJK on 2018/1/26.
 */

public class WXEtryTempalets extends BaseWXEntryActivity{

    @Override
    protected void onResume() {
        super.onResume();
        finish();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onSignInsucess(String userInfo) {
        LatteWeChat.getInstance().getSignInCallback().onSignInSuccess(userInfo);
    }
}
