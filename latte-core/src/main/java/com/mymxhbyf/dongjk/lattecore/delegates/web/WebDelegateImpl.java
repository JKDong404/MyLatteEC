package com.mymxhbyf.dongjk.lattecore.delegates.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mymxhbyf.dongjk.lattecore.delegates.web.chromeclient.WebChromeClientImpl;
import com.mymxhbyf.dongjk.lattecore.delegates.web.client.WebViewClientImpl;
import com.mymxhbyf.dongjk.lattecore.delegates.web.route.RoteKeys;
import com.mymxhbyf.dongjk.lattecore.delegates.web.route.Router;

/**
 * Created by DongJK on 2018/2/5.
 */

public class WebDelegateImpl extends WebDelegate{

    public static WebDelegateImpl create(String url){
        final Bundle args = new Bundle();
        args.putString(RoteKeys.URL.name(),url);
        final WebDelegateImpl delegate = new WebDelegateImpl();
        delegate.setArguments(args);
        return delegate;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        if (getUrl() != null){
            //用原生的方式模拟web跳转并进行页面加载
            Router.getInstance().loadPage(this,getUrl());
        }
    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public WebView initWebView(WebView webView) {
        return new WebInitializer().creatWebView(webView);
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClient client = new WebViewClientImpl(this);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClent() {
        return new WebChromeClientImpl();
    }
}
