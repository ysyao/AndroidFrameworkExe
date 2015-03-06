package bigbangtheory.display.yao.bigbangtheory.core.client;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import bigbangtheory.display.yao.bigbangtheory.core.client.BigBangHttpResponseHandler;
import roboguice.RoboGuice;

/**
 * Created by JimmyandHurry on 2015/2/2.
 * 在发送请求之前检查手机网络状况，如果没有连入互联网则不发送请求
 */
public abstract class CheckIfCouldAccessInternetResponseHandler extends BigBangHttpResponseHandler {
    private boolean isOnline = false;
    public CheckIfCouldAccessInternetResponseHandler(Context context) {
        super(context);
        ConnectivityManager cn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        isOnline = checkIfOnline(cn);
    }

    @Override
    public void onStart() {
        super.onStart();
        if(!isOnline) {
            cancelResponse();
        }
    }

    /**
     * 检测网络是否连接
     * @return 是否连接上
     */
    protected boolean checkIfOnline(ConnectivityManager cm) {
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    /**
     * 获取网络连接方式
     * @return 网络类型
     */
    protected int checkConnectionType(ConnectivityManager cm) {
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo.getType();
    }
}
