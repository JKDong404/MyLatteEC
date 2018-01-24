package com.mymxhbyf.dongjk.lattecore.util.timer;

import java.util.TimerTask;

/**
 * Created by DongJK on 2018/1/24.
 */

public class BaseTimerTask extends TimerTask{

    private ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener timerListener) {
        this.mITimerListener = timerListener;
    }

    @Override
    public void run() {
        if (mITimerListener != null){
            mITimerListener.onTimer();
        }
    }
}
