package bigbangtheory.display.yao.bigbangtheory.authorication;

import android.content.Context;
import android.widget.Toast;

import com.google.inject.Inject;

import bigbangtheory.display.yao.bigbangtheory.core.pojo.BigBangError;
import bigbangtheory.display.yao.bigbangtheory.login.LoginUtils;
import bigbangtheory.display.yao.bigbangtheory.core.client.ProgressDialogResponseHandler;

/**一个判断是否需要重新登录的response handler：
 * 1.在请求发起之前都要检查token是否存在，不存在则自动跳转到登录页面，且取消之后的一切ui操作。
 * 2.解析从服务端返回的json，检查服务端是否报出由请求参数等原因引起的错误，如果出错则改用BigBangError类来解析。
 * Created by JimmyandHurry on 2015/3/3.
 */
public abstract class AuthorTokenResponseHanlder extends ProgressDialogResponseHandler {
    @Inject
    private AuthorTokenResource tokenResource;
    public AuthorTokenResponseHanlder(Context context) {
        super(context);
    }

    @Override
    public void onStart() {
        super.onStart();
        //获取access_token
        String tokenObj = tokenResource.retrieveOAuthor2Token();
        String token = AuthorTokenUtils.getAccessTokenStringByAccessTokenJson(tokenObj);

        //检查是否有access_token，如果没有跳转登录页面
        if(token == null || "".equals(token)) {
            //启动登录activity
            LoginUtils.startLoginActivity(getApplicationContext());

            //不调用响应内容
            cancelResponse();
        }
    }

    @Override
    protected void onRequestError(BigBangError error) {
        //这三种情况需要重新登录
        if(error.getCode().equals(BigBangError.MISSING_TOKEN_CODE)  || error.getCode().equals(BigBangError.EXPIRED_TOKEN_CODE) || error.getCode().equals(BigBangError.INVALID_TOKEN_CODE)) {
            LoginUtils.startLoginActivity(getApplicationContext());
        }
        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
    }
}
