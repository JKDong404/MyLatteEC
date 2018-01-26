package com.mymxhbyf.dongjk.latteec.generators;

import com.mymxhbyf.dongjk.latte_annotations.annotations.PayEntryGenerator;
import com.mymxhbyf.dongjk.lattecore.wechat.templates.WXPayEtryTempalets;

/**
 * Created by DongJK on 2018/1/26.
 */
@PayEntryGenerator(
        packageName = "com.mymxhbyf.dongjk.latteec",
        payEntryTemplete = WXPayEtryTempalets.class
)
public interface WeChatPayEntry {
}
