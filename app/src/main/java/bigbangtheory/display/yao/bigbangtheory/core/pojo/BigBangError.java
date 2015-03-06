package bigbangtheory.display.yao.bigbangtheory.core.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by JimmyandHurry on 2015/3/3.
 */
public class BigBangError {
    public static final String MISSING_TOKEN_CODE = "102";
    public static final String INVALID_TOKEN_CODE = "103";
    public static final String EXPIRED_TOKEN_CODE = "106";

    @SerializedName("code")
    private String code;
    @SerializedName("msg")
    private String message;
    @SerializedName("request")
    private String request;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
