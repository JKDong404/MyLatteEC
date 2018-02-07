package com.mymxhbyf.dongjk.lattecore.delegates.web.client;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mymxhbyf.dongjk.lattecore.delegates.web.WebDelegate;
import com.mymxhbyf.dongjk.lattecore.delegates.web.route.Router;
import com.mymxhbyf.dongjk.lattecore.util.log.LatteLogger;

/**
 * Created by DongJK on 2018/2/5.
 */

public class WebViewClientImpl extends WebViewClient{

    private final WebDelegate DELEGATE;

    public WebViewClientImpl(WebDelegate delegate) {
        this.DELEGATE = delegate;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        return super.shouldOverrideUrlLoading(view, request);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LatteLogger.d("shouldOverrideUrlLoading",url);
        return Router.getInstance().handlerWebUrl(DELEGATE,url);
    }
}
