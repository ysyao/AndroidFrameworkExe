package bigbangtheory.display.yao.bigbangtheory.authorication;

import android.content.Context;

import com.google.inject.Inject;

import bigbangtheory.display.yao.bigbangtheory.core.client.BigBangService;

/**一个在请求发起之前将author信息设置进header的Service，
 * 其实现方式是将客户端换成授权客户端AuthorTokenClient，
 * 将token放到header的动作由授权客户端来完成。
 * Created by JimmyandHurry on 2015/3/4.
 */
public class AuthorTokenService extends BigBangService {
    private Context context;

    @Inject
    public AuthorTokenService(Context context) {
        super(new AuthorTokenClient(context));
        this.context = context;
    }

    protected Context getApplicationContext() {
        return context;
    }
}
