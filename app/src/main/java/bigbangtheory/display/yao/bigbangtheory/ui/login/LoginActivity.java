package bigbangtheory.display.yao.bigbangtheory.ui.login;

import static bigbangtheory.display.yao.bigbangtheory.core.client.IBigBangTheoryConstants.DOUBAN_AUTHORICATION_API_TOKEN;
import static bigbangtheory.display.yao.bigbangtheory.core.client.IBigBangTheoryConstants.BIG_BANG_BACK_HOST;
import static bigbangtheory.display.yao.bigbangtheory.core.client.IBigBangTheoryConstants.ENCODING_UTF_8;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.inject.Inject;

import org.apache.http.util.EncodingUtils;

import bigbangtheory.display.yao.bigbangtheory.R;
import bigbangtheory.display.yao.bigbangtheory.authorication.AuthorTokenResource;
import bigbangtheory.display.yao.bigbangtheory.authorication.AuthorTokenUtils;
import bigbangtheory.display.yao.bigbangtheory.login.LoginUtils;
import bigbangtheory.display.yao.bigbangtheory.ui.WebViewActivity;


public class LoginActivity extends WebViewActivity {
    /**
     * WebView Interface Name
     */
    private static final String BIG_BANG_INTERFACE_NAME = "bigbanginterface";
    @Inject
    private AuthorTokenResource tokenResource;

    class OnAuthFinished {
        @JavascriptInterface
        public void getJson(String json) {
            System.out.print(json);
            Log.d("ssssssssssss", json);

            tokenResource.storeOAuthor2Token(json);
            setResult(RESULT_OK);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadingLoginPage();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            loadingLoginPage();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * 载入豆瓣授权界面
     */
    private void loadingLoginPage() {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                String host = LoginUtils.getHost(url);
                if(host.equals(BIG_BANG_BACK_HOST)) {
                    getOAuthor2Token(view, url);
                    return true;
                }
                return false;
            }
        });
        String url = LoginUtils.getLoginUrl();
        webView.loadUrl(url);
    }

    /**
     * 利用webView获取授权
     * @param authorWebView 载入的webview
     * @param backUrl 回退url
     */
    private void getOAuthor2Token(final WebView authorWebView, String backUrl) {
        authorWebView.getSettings().setJavaScriptEnabled(true);
        authorWebView.addJavascriptInterface(new OnAuthFinished(), BIG_BANG_INTERFACE_NAME);
        authorWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                authorWebView.loadUrl("javascript: var json = document.getElementsByTagName('pre')[0].innerHTML;\n" +
//                        "javascript:window." + BIG_BANG_INTERFACE_NAME + ".getJson(document.getElementsByTagName('pre')[0].innerHTML);" +
                        "document.getElementsByTagName('pre')[0].innerHTML=\"\";\n" +
                        "window." + BIG_BANG_INTERFACE_NAME + ".getJson(json);");
            }
        });
        authorWebView.postUrl(DOUBAN_AUTHORICATION_API_TOKEN, EncodingUtils.getBytes(LoginUtils.authorPostParameters(backUrl), ENCODING_UTF_8));
    }
}
