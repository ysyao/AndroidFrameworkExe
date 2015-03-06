package bigbangtheory.display.yao.bigbangtheory.core.client;

import org.apache.http.Header;

/**
 * Created by JimmyandHurry on 2015/2/2.
 */
public interface OnResponseListener {
    public void onSuccess(Object object) ;
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error);

}
