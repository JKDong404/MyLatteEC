package com.mymxhbyf.dongjk.lattecore.ui.loader;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.mymxhbyf.dongjk.lattecore.R;
import com.mymxhbyf.dongjk.lattecore.util.DemenUtil.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * loader
 * Created by DongJK on 2018/1/19.
 */

public class LatteLoader {

    private static final int LOADER_SIZE_SCALE = 8;//缩放倍数
    private static final int LOADER_OFFSET_SCALE = 10;//偏移量

    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();

    //默认样式
    private static final String DEFFAULT_LOADER = LoaderStyle.BallClipRotatePulseIndicator.name();

    //可传入枚举类型，方便使用
    public static void showLoading(Context context,Enum<LoaderStyle> type){
        showLoading(context,type.name());
    }

    public static void showLoading(Context context,String type){

        final AppCompatDialog dialog = new AppCompatDialog(context,R.style.dialog);

        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type,context);
        dialog.setContentView(avLoadingIndicatorView);

        int devicewidth = DimenUtil.getScreenWidth();
        int deviceheight = DimenUtil.getScreenHeight();

        final Window dialogWindow = dialog.getWindow();

        if (dialogWindow != null){
            //设定dialog的宽高
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = devicewidth / LOADER_SIZE_SCALE;
            lp.height = deviceheight / LOADER_SIZE_SCALE;
            lp.height = lp.height + deviceheight /LOADER_OFFSET_SCALE;//偏移量设为10
            lp.gravity = Gravity.CENTER;//居中
        }
        LOADERS.add(dialog);
        dialog.show();
    }

    public static void showLoading(Context context){
        showLoading(context,DEFFAULT_LOADER);
    }

    public static void stopLoading(){
        for (AppCompatDialog dialog : LOADERS){
            if (dialog != null){
               if (dialog.isShowing()){
                   dialog.cancel();
               }
            }
        }
    }

}
