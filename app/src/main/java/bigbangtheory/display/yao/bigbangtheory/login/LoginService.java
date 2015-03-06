package bigbangtheory.display.yao.bigbangtheory.login;

import android.content.Context;

import bigbangtheory.display.yao.bigbangtheory.authorication.AuthorTokenService;
import bigbangtheory.display.yao.bigbangtheory.core.client.BigBangHttpRequest;
import bigbangtheory.display.yao.bigbangtheory.core.client.BigBangHttpRequestHandler;
import bigbangtheory.display.yao.bigbangtheory.core.client.BigBangHttpResponseHandler;

import static bigbangtheory.display.yao.bigbangtheory.core.client.IBigBangTheoryConstants.INFO_DOUBAN_API;

/**
 * 登录Service，可以获取用户信息
 * Created by JimmyandHurry on 2015/3/5.
 */
public class LoginService extends AuthorTokenService {
    public LoginService(Context context) {
        super(context);
    }

    /**
     * 获取个人信息
     * @param response 回调
     * @return 请求handler
     */
    public BigBangHttpRequestHandler getPersonalInformation(BigBangHttpResponseHandler response) {
        BigBangHttpRequest request = new BigBangHttpRequest();
        request.setUri(INFO_DOUBAN_API);
        request.setType(User.class);
        return client.get(request, response);
    }
}
