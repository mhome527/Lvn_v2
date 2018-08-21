package teach.vietnam.asia.view.translate;

import android.os.Build;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.google.firebase.crash.FirebaseCrash;

import teach.vietnam.asia.BuildConfig;
import teach.vietnam.asia.R;
import teach.vietnam.asia.utils.Log;
import teach.vietnam.asia.view.base.BaseActivity;

public class TranslateActivity extends BaseActivity<TranslateActivity> {

    private final String TAG = "TranslateActivity";

    private WebView webView;
    ProgressBar pbar;

    @Override
    protected int getLayout() {
        return R.layout.activity_translate;
    }

    @Override
    protected void initView() {
//        String url = "https://translate.google.com/?hl=%s#%s/vi/";
        String url = "https://translate.google.com/m/translate?hl=%s#view=home&op=translate&sl=%s&tl=vi";
        webView = findViewById(R.id.webView);
        pbar = findViewById(R.id.progressBar1);


        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setDisplayZoomControls(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }


        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

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
//            webView.loadUrl(url);
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            pbar.setVisibility(View.GONE);
        }
    }

}
