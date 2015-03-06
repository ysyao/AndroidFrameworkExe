package bigbangtheory.display.yao.bigbangtheory.core.client;

import android.content.Context;

import static bigbangtheory.display.yao.bigbangtheory.core.client.IBigBangTheoryConstants.AUTH_TOKEN;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestHandle;

import roboguice.RoboGuice;

/**发送GET，POST，PUT，DELETE请求客户端
 * Created by JimmyandHurry on 2015/2/2.
 */
public class BigBangHttpClient {
    private static AsyncHttpClient client = new AsyncHttpClient();
    private Context context;
    /**
     * Authorization header
     */
    protected static final String HEADER_AUTHORIZATION = "Authorization"; //$NON-NLS-1$

    public BigBangHttpClient() {

    }

    public BigBangHttpClient(Context context) {
        this.context = context;
        RoboGuice.getInjector(context).injectMembers(this);
    }

    /**
     * Set OAuth2 token
     *
     * @param token
     * @return this client
     */
    public BigBangHttpClient setOAuth2Token(String token) {
        client.addHeader(HEADER_AUTHORIZATION, AUTH_TOKEN + ' ' + token);
        return this;
    }

    /**
     * 清除授权header
     */
    public void removeAuthorizationHeader() {
        client.removeHeader(HEADER_AUTHORIZATION);
    }

    /**
     * 取消请求
     * @param context               环境变量
     * @param mayInterruptIfRunning specifies if active requests should be cancelled along with
     *                              pending requests
     */
    public void cancelRequests(final Context context, final boolean mayInterruptIfRunning) {
        client.cancelRequests(context, mayInterruptIfRunning);
    }

    /**
     * GET请求方法，从网络API获取结果，这个方法将不会在UI线程运行
     * @param request  请求
     * @param response 响应
     * @return         一个可以取消请求的handler
     */
    public BigBangHttpRequestHandler get(BigBangHttpRequest request, BigBangHttpResponseHandler response) {
        configuration(request, response);
        RequestHandle handler = client.get(request.getUri(), request.getParams(), response);
        System.out.println(request.getUri());
        return new BigBangHttpRequestHandler(handler);
    }

    /**
     * POST请求方法，从网络API获取结果，这个方法将不会在UI线程运行
     * @param request   请求
     * @param response  响应
     * @return          一个可以取消请求的handler
     */
    public BigBangHttpRequestHandler post(BigBangHttpRequest request, BigBangHttpResponseHandler response) {
        configuration(request, response);
        RequestHandle handler = client.post(request.getUri(), request.getParams(), response);
        return new BigBangHttpRequestHandler(handler);
    }

    /**
     * 发送请求之前的配置
     * @param request   请求
     * @param response  响应
     */
    protected void configuration(BigBangHttpRequest request, BigBangHttpResponseHandler response) {
        response.setType(request.getType()).setListType(request.getListType());
    }

    protected Context getApplicationContext() {
        return context;
    }
}
