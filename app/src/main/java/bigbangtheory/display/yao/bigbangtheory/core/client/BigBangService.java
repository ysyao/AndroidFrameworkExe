package bigbangtheory.display.yao.bigbangtheory.core.client;

import android.content.Context;

/**BigBang服务，操作AsyncHttpClient
 * Created by JimmyandHurry on 2015/2/2.
 */
public class BigBangService {
    protected BigBangHttpClient client;

    public BigBangService() {
        this(new BigBangHttpClient());
    }

    public BigBangService(BigBangHttpClient client) {
        this.client = client;
    }

    public BigBangHttpClient getClient() {
        return this.client;
    }

    public BigBangService setOAuth2Token(String token) {
        client.setOAuth2Token(token);
        return this;
    }

    public BigBangHttpClient getHttpClient() {
        return client;
    }

    /**
     * 清除授权header
     */
    public void removeAuthorizationHeader() {
        client.removeAuthorizationHeader();
    }

    /**
     * 取消请求
     * @param context
     * @param mayInterruptIfRunning
     */
    public void cancelRequest(Context context, boolean mayInterruptIfRunning) {
        client.cancelRequests(context, mayInterruptIfRunning);
    }
}
