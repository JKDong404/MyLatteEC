package com.mymxhbyf.dongjk.latteec.generators;

import com.mymxhbyf.dongjk.latte_annotations.annotations.AppRegisterGenerator;

import com.mymxhbyf.dongjk.lattecore.wechat.templates.AppRegisterTempalets;

/**
 * Created by DongJK on 2018/1/26.
 */

@AppRegisterGenerator(
        packageName = "com.mymxhbyf.dongjk.latteec",
        registerTemplete = AppRegisterTempalets.class
)
public interface AppRegister {
}
