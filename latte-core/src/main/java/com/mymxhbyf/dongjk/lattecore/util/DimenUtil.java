package com.mymxhbyf.dongjk.lattecore.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.mymxhbyf.dongjk.lattecore.app.Latte;

/**
 * 得到屏幕宽高
 * Created by DongJK on 2018/1/19.
 */

public class DimenUtil {

    public static int getScreenWidth(){
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight(){
        final Resources resources = Latte.getApplication().getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }
}
