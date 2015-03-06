package bigbangtheory.display.yao.bigbangtheory.core.client;

import com.loopj.android.http.RequestParams;

import java.lang.reflect.Type;

/**
 * Created by JimmyandHurry on 2015/2/2.
 */
public class BigBangHttpRequest {
    /**
     * 用来解析json的POJO类
     */
    private Type type;
    private Type listType;
    /**
     * 请求uri
     */
    private String uri;
    /**
     * 请求参数
     */
    private RequestParams params;

    public BigBangHttpRequest() {
        params = new RequestParams();
    }
    public BigBangHttpRequest(String uri, Type type, Type listType) {
        this.uri = uri;
        this.type = type;
        this.listType = listType;
        params = new RequestParams();
    }

    /**
     * 添加请求参数
     * @param key
     * @param value
     */
    public void putParams(String key, String value) {
        params.add(key, value);
    }

    public RequestParams getParams() {
        return params;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Type getListType() {
        return listType;
    }

    public void setListType(Type listType) {
        this.listType = listType;
    }
}
