package com.mymxhbyf.dongjk.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mymxhbyf.dongjk.latte.ec.database.DatabaseManager;
import com.mymxhbyf.dongjk.latte.ec.database.UserProfile;
import com.mymxhbyf.dongjk.lattecore.app.AccoutManager;


/**
 * Created by DongJK on 2018/1/26.
 */

public class SignHandler {

    public static void onSignUp(String response,ISignListener signListener){
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId,name,avatar,gender,address);
        DatabaseManager.getInstance().getDao().insert(profile);

        //已经注册并保存成功
        AccoutManager.setSignState(true);//将tag置为true
        signListener.onSignUpSuccess();
    }


    public static void onSignIn(String response,ISignListener signListener){
//        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
//        final long userId = profileJson.getLong("userId");
//        final String name = profileJson.getString("name");
//        final String avatar = profileJson.getString("avatar");
//        final String gender = profileJson.getString("gender");
//        final String address = profileJson.getString("address");
        final long userId = 123456;
        final String name = "张三";
        final String avatar = "";
        final String gender = "男";
        final String address = "郑州";

        final UserProfile profile = new UserProfile(userId,name,avatar,gender,address);
        DatabaseManager.getInstance().getDao().insert(profile);

        //已经登录并保存成功
        AccoutManager.setSignState(true);//将tag置为true
        signListener.onSignInSuccess();
    }

}
