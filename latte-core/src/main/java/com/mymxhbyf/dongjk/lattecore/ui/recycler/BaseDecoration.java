package com.mymxhbyf.dongjk.lattecore.ui.recycler;

import android.support.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/**
 * recyclerView 分割线
 * Created by DongJK on 2018/1/31.
 */

public class BaseDecoration extends DividerItemDecoration{

    public BaseDecoration(@ColorInt int color, int size){
        setDividerLookup(new DividerLookupImpl(color,size));
    }

    public static BaseDecoration create(@ColorInt int color, int size){
        return new BaseDecoration(color, size);
    }

}
