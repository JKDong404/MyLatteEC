package com.mymxhbyf.dongjk.lattecore.delegates.web.event;

import com.mymxhbyf.dongjk.lattecore.util.log.LatteLogger;

/**
 * Created by DongJK on 2018/2/5.
 */

public class UndefineEvent extends Event{
    @Override
    public String execute(String params) {
        LatteLogger.e("UndefineEvent",params);
        return null;
    }
}
