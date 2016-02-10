package com.dhara.playbasis.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.dhara.playbasis.R;
import com.dhara.playbasis.constants.Global;

/**
 * Created by USER on 07-02-2016.
 */
public class WebviewFragment extends Fragment{
    private View mView;
    private String mURL;
    private WebView mWebView;
    private ProgressBar mProgressBar;
    private static WebviewFragment mFragment;

    public static WebviewFragment newInstance(String webUrl)  {
        mFragment = new WebviewFragment();
        Bundle args = new Bundle();
        args.putString(Global.INTENT_URL, webUrl);
        mFragment.setArguments(args);
        return mFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_webview,container,false);
        mWebView = (WebView)mView.findViewById(R.id.webView);
        mProgressBar = (ProgressBar)mView.findViewById(R.id.progressBar);
        setUpWebview();
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void setUpWebview() {
        if(getArguments() != null) {
            mURL = getArguments().getString(Global.INTENT_URL);
        }

        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setBuiltInZoomControls(false);
        mWebView.setInitialScale(100);
        mWebView.setWebViewClient(new MyWebClient());
        mProgressBar.setVisibility(View.VISIBLE);
        mWebView.loadUrl(mURL);
    }

    public class MyWebClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            mProgressBar.setVisibility(View.GONE);
        }
    }
}
