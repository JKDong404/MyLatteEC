package com.mymxhbyf.dongjk.lattecore.delegates.web.event;

import android.webkit.WebView;
import android.widget.Toast;

/**
 * Created by DongJK on 2018/2/5.
 */

public class TestEvent extends Event {
    @Override
    public String execute(String params) {
        Toast.makeText(getContext(),getAction(),Toast.LENGTH_LONG).show();
        if (getAction().equals("test")){
            final WebView webView = getWebView();
            getWebView().post(new Runnable() {
                @Override
                public void run() {
                    webView.evaluateJavascript("nativeCall();",null);
                }
            });
        }
        return null;
    }
}
