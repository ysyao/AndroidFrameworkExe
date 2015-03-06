package bigbangtheory.display.yao.bigbangtheory.core.client;

import android.content.Context;
import android.os.Message;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;

import bigbangtheory.display.yao.bigbangtheory.core.pojo.*;
import roboguice.RoboGuice;

import static com.google.gson.stream.JsonToken.BEGIN_ARRAY;

/**
 * Created by JimmyandHurry on 2015/2/2.
 */
public abstract class BigBangHttpResponseHandler extends AsyncHttpResponseHandler {
    private Type type;
    private Type listType;
    private int bufferSize = 8192;
    /**
     * 请求是否取消
     */
    private boolean isRequestCanceled = false;

    public BigBangHttpResponseHandler(){}

    public BigBangHttpResponseHandler(Context context) {
        RoboGuice.getInjector(context).injectMembers(this);
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public BigBangHttpResponseHandler setBufferSize(int bufferSize) {
        if (bufferSize < 1)
            throw new IllegalArgumentException(
                    "Buffer size must be greater than zero"); //$NON-NLS-1$
        this.bufferSize = bufferSize;
        return this;
    }

    public Type getType() {
        return type;
    }
    public Type getListType() {
        return listType;
    }
    public BigBangHttpResponseHandler setType(Type type) {
        this.type = type;
        return this;
    }
    public BigBangHttpResponseHandler setListType(Type listType) {
        this.listType = listType;
        return this;
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
        //试图用BigBangError类型来解析传回来的byte
        BigBangError error = parseByte2JsonPojo(BigBangError.class, null, responseBody);
        if(error.getCode() != null) {
            onRequestError(error);
        } else {
            onRequestSuccess(parseByte2JsonPojo(getType(), getListType(), responseBody));
        }
    }

    @Override
    protected void sendMessage(Message msg) {
        if(!isRequestCanceled) {
            super.sendMessage(msg);
        }
    }

    /**
     * 将响应设置成为不触发，有时候请求连接已经建立，但是又有取消请求的动作，
     * 这样就要求请求对应的响应不被触发。
     */
    protected void cancelResponse() {
        isRequestCanceled = true;
    }

    /**
     * 当成功返回json之后调用此方法，调用过程出现错误则不调用
     * @param object 回调
     */
    protected abstract void onRequestSuccess(Object object);

    /**
     * 当出现请求参数错误的时候调用此方法，调用过程成功则不调用
     * @param error 错误
     */
    protected void onRequestError(BigBangError error){}

    /**
     * 将响应获得的byte转换成为json，在onSuccess方法当中打印出来
     * @param type 转换POJO
     * @param listType
     * @param responseBody
     * @param <T>
     * @return
     */
    private <T> T parseByte2JsonPojo(Type type, Type listType, byte[] responseBody) {
        ByteArrayInputStream bis = new ByteArrayInputStream(responseBody);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(bis, IBigBangTheoryConstants.CHARSET_UTF8), bufferSize);
            Gson gson = new Gson();
            if(listType == null) {
                return gson.fromJson(reader, type);
            } else {
                JsonReader jsonReader = new JsonReader(reader);
                if(jsonReader.peek() == BEGIN_ARRAY) {
                    return gson.fromJson(reader,listType);
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
