package com.mymxhbyf.dongjk.latte.ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.mymxhbyf.dongjk.latte.ec.R;
import com.mymxhbyf.dongjk.latte.ec.R2;
import com.mymxhbyf.dongjk.lattecore.delegates.LatteDelegate;

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

    @OnClick(R2.id.btn_sign_in)
    void onClickSingIn(){
        checkForm();
    }

    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWeChat(){
        //微信登陆
    }

    @OnClick(R2.id.tv_link_sign_in)
    void onClickLink(){
        start(new SignUpDelegate());
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
