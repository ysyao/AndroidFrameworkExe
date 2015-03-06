package bigbangtheory.display.yao.bigbangtheory.authorication;

import android.content.Context;

import com.google.inject.Inject;

import bigbangtheory.display.yao.bigbangtheory.core.client.BigBangHttpClient;
import bigbangtheory.display.yao.bigbangtheory.core.client.BigBangHttpRequest;
import bigbangtheory.display.yao.bigbangtheory.core.client.BigBangHttpResponseHandler;
import bigbangtheory.display.yao.bigbangtheory.login.AccessToken;
import roboguice.RoboGuice;

/**授权客户端，会在get,post等各种请求之前自动设置token
 * Created by JimmyandHurry on 2015/3/5.
 */
public class AuthorTokenClient extends BigBangHttpClient {
    @Inject
    private AuthorTokenResource tokenResource;

    public AuthorTokenClient(Context context) {
        super(context);
    }

    @Override
    protected void configuration(BigBangHttpRequest request, BigBangHttpResponseHandler response) {
        super.configuration(request, response);

        //获取access_token
        String tokenObj = tokenResource.retrieveOAuthor2Token();
        String token = AuthorTokenUtils.getAccessTokenStringByAccessTokenJson(tokenObj);

        //检查是否有access_token，如果没有跳转登录页面
        if(token != null && !"".equals(token)) {
            setOAuth2Token(token);
        }
    }
}
