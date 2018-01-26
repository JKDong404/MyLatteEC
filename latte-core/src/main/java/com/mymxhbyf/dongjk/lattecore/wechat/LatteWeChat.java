package com.mymxhbyf.dongjk.lattecore.wechat;

import android.app.Activity;

import com.mymxhbyf.dongjk.lattecore.app.ConfigType;
import com.mymxhbyf.dongjk.lattecore.app.Latte;
import com.mymxhbyf.dongjk.lattecore.wechat.callbacks.IWeChatSignInCallback;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by DongJK on 2018/1/26.
 */

public class LatteWeChat {

    public static final String APP_ID = Latte.getConfiguration(ConfigType.WE_CHAT_APP_ID);
    public static final String APP_SECRET = Latte.getConfiguration(ConfigType.WE_CHAT_APP_SECRET);
    private final IWXAPI WXAPI;
    private IWeChatSignInCallback mSignInCallback = null;


    private static final class Holder{
        private static final LatteWeChat INSTANCE = new LatteWeChat();
    }

    public static LatteWeChat getInstance(){
        return Holder.INSTANCE;
    }

    private LatteWeChat(){
        final Activity activity = Latte.getConfiguration(ConfigType.ACTIVITY);
        WXAPI = WXAPIFactory.createWXAPI(activity,APP_ID,true);
        WXAPI.registerApp(APP_ID);
    }

    public final IWXAPI getWXAPI(){
        return WXAPI;
    }

    public LatteWeChat onSignSuccess(IWeChatSignInCallback callback){
        this.mSignInCallback = callback;
        return this;
    }

    public IWeChatSignInCallback getSignInCallback(){
        return mSignInCallback;
    }

    public final void signIn(){
        final SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "random_state";
        WXAPI.sendReq(req);
    }

}
