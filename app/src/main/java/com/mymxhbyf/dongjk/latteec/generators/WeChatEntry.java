package com.mymxhbyf.dongjk.latteec.generators;

import com.mymxhbyf.dongjk.latte_annotations.annotations.EntryGenerator;
import com.mymxhbyf.dongjk.lattecore.wechat.templates.WXEtryTempalets;

/**
 * Created by DongJK on 2018/1/26.
 */
@EntryGenerator(
        packageName = "com.mymxhbyf.dongjk.latteec",
        entryTempltete = WXEtryTempalets.class
)
public interface WeChatEntry {
}
