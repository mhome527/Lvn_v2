package teach.vietnam.asia.view.translate;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.firebase.crash.FirebaseCrash;

import teach.vietnam.asia.BuildConfig;
import teach.vietnam.asia.R;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.view.base.BaseActivity;

public class TranslateActivity extends BaseActivity<TranslateActivity> {

    private final String TAG = "TranslateActivity";

    private WebView webView;

    @Override
    protected int getLayout() {
        return R.layout.activity_translate;
    }

    @Override
    protected void initView() {
        String url = "https://translate.google.com/?hl=%s#%s/vi/";
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient() {
        });
        webView.setWebViewClient(new MCSWebViewClient());

        url = String.format(url, lang, lang);
//        url = "http://google.com";
        Log.i(TranslateActivity.class, "url:" + url);
        webView.loadUrl(url);
        if (!BuildConfig.DEBUG)
        FirebaseCrash.logcat(Log.INFO, TAG, "initView");
    }


    private class MCSWebViewClient extends WebViewClient

    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

        }
    }

}
