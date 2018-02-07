package com.mymxhbyf.dongjk.latte.ec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.mymxhbyf.dongjk.latte.ec.R;
import com.mymxhbyf.dongjk.latte.ec.R2;
import com.mymxhbyf.dongjk.lattecore.delegates.LatteDelegate;
import com.mymxhbyf.dongjk.lattecore.net.RestClient;
import com.mymxhbyf.dongjk.lattecore.net.callback.ISuccess;
import com.mymxhbyf.dongjk.lattecore.wechat.LatteWeChat;
import com.mymxhbyf.dongjk.lattecore.wechat.callbacks.IWeChatSignInCallback;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by DongJK on 2018/1/25.
 */

public class SignInDelegate extends LatteDelegate{

    @BindView(R2.id.et_sign_in_email)
    TextInputEditText etEmail = null;
    @BindView(R2.id.et_sign_in_psw)
    TextInputEditText etPsw = null;

    private ISignListener mISignListener = null;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener){
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_in)
    void onClickSingIn(){
        if (checkForm()){
//            RestClient.builder()
//                    .url("sign_in")
//                    .params("email",etEmail.getText().toString())
//                    .params("password",etPsw.getText().toString())
//                    .success(new ISuccess() {
//                        @Override
//                        public void onSuccess(String response) {
//                            SignHandler.onSignIn(response,mISignListener);
//                        }
//                    })
//                    .build()
//                    .post();
            SignHandler.onSignIn("登陆",mISignListener);
        }

    }


    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWeChat(){
        //微信登陆
        LatteWeChat.getInstance().onSignSuccess(new IWeChatSignInCallback() {
            @Override
            public void onSignInSuccess(String userInfo) {

            }
        }).signIn();
    }

    @OnClick(R2.id.tv_link_sign_in)
    void onClickLink(){
        getSupportDelegate().start(new SignUpDelegate());
    }


    private boolean checkForm(){
        final String email = etEmail.getText().toString();
        final String password = etPsw.getText().toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("错误的邮箱格式");
            isPass = false;
        }else {
            etEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6){
            etPsw.setError("请填写至少六位密码");
            isPass = false;
        }else {
            etPsw.setError(null);
        }

        return isPass;

    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
