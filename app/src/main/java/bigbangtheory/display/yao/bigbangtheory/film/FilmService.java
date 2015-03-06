package bigbangtheory.display.yao.bigbangtheory.film;

import android.content.Context;

import com.google.inject.Inject;

import static bigbangtheory.display.yao.bigbangtheory.core.client.IBigBangTheoryConstants.INFO_DOUBAN_API;

import bigbangtheory.display.yao.bigbangtheory.login.User;
import bigbangtheory.display.yao.bigbangtheory.authorication.AuthorTokenService;
import bigbangtheory.display.yao.bigbangtheory.core.client.BigBangHttpRequest;
import bigbangtheory.display.yao.bigbangtheory.core.client.BigBangHttpRequestHandler;
import bigbangtheory.display.yao.bigbangtheory.core.client.BigBangHttpResponseHandler;

/**登录service
 * Created by JimmyandHurry on 2015/2/27.
 */
public class FilmService extends AuthorTokenService {
    @Inject
    public FilmService(Context context) {
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
