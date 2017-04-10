package jiyun.com.openresausechina.view;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import jiyun.com.openresausechina.R;

/**
 * Created by think on 2017/4/8.
 */

public class SearchWebView extends Activity {
    WebView webView;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        webView = (WebView) findViewById(R.id.search_webview);
        url = getIntent().getStringExtra("url");
        webView.loadUrl(url);
    }
}
