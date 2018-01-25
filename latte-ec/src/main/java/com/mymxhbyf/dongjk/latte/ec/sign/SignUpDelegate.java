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
import com.mymxhbyf.dongjk.lattecore.net.RestClient;
import com.mymxhbyf.dongjk.lattecore.net.callback.ISuccess;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by DongJK on 2018/1/25.
 */

public class SignUpDelegate extends LatteDelegate{

    @BindView(R2.id.et_sign_up_name)
    TextInputEditText etName = null;

    @BindView(R2.id.et_sign_up_email)
    TextInputEditText etEmail = null;

    @BindView(R2.id.et_sign_up_phone)
    TextInputEditText etPhone = null;

    @BindView(R2.id.et_sign_up_psw)
    TextInputEditText etPsw = null;

    @BindView(R2.id.et_sign_up_confirm_psw)
    TextInputEditText etConfirmPsw = null;

    @OnClick(R2.id.btn_sign_up_register)
    void onClickSignUp(){
        if (checkForm()){
//            RestClient.builder()
//                    .url("sign_up")
//                    .params("","")
//                    .success(new ISuccess() {
//                        @Override
//                        public void onSuccess(String response) {
//
//                        }
//                    })
//                    .build()
//                    .post();
            Toast.makeText(getContext(),"验证通过",Toast.LENGTH_SHORT).show();

        }
    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink(){
        //已注册，点击跳转登陆界面
        start(new SignInDelegate());
    }


    private boolean checkForm(){
        final String name = etName.getText().toString();
        final String email = etEmail.getText().toString();
        final String phone = etPhone.getText().toString();
        final String password = etPsw.getText().toString();
        final String comfirmPas = etConfirmPsw.getText().toString();

        boolean isPass = true;

        if (name.isEmpty()){
            etName.setError("请输入姓名");
            isPass = false;
        }else {
            etName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("错误的邮箱格式");
            isPass = false;
        }else {
            etEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11){
            etPhone.setError("手机号码错误");
            isPass = false;
        }else {
            etPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6){
            etPsw.setError("请填写至少六位密码");
            isPass = false;
        }else {
            etPsw.setError(null);
        }

        if (comfirmPas.isEmpty() || comfirmPas.length() < 6 || !(comfirmPas.equals(password))){
            etConfirmPsw.setError("两次输入密码不同");
            isPass = false;
        }else {
            etConfirmPsw.setError(null);
        }

        return isPass;

    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
