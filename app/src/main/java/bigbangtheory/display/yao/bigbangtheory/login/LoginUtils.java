package bigbangtheory.display.yao.bigbangtheory.login;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.google.gson.Gson;
import static bigbangtheory.display.yao.bigbangtheory.core.client.IBigBangTheoryConstants.ENCODING_UTF_8;
import static bigbangtheory.display.yao.bigbangtheory.core.client.IBigBangTheoryConstants.DOUBAN_AUTHORICATION_API;
import static bigbangtheory.display.yao.bigbangtheory.core.client.IBigBangTheoryConstants.API_KEY;
import static bigbangtheory.display.yao.bigbangtheory.core.client.IBigBangTheoryConstants.DOUBAN_SECRET;
import static bigbangtheory.display.yao.bigbangtheory.core.client.IBigBangTheoryConstants.BIG_BANG_BACK_URL;
import static bigbangtheory.display.yao.bigbangtheory.core.client.IBigBangTheoryConstants.GRANT_TYPE;
import static bigbangtheory.display.yao.bigbangtheory.core.client.IBigBangTheoryConstants.RESPONSE_TYPE;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.regex.Pattern;

import bigbangtheory.display.yao.bigbangtheory.login.AccessToken;
import bigbangtheory.display.yao.bigbangtheory.ui.login.LoginActivity;

/**
 * Created by JimmyandHurry on 2015/2/27.
 */
public class LoginUtils {
    /**
     * 组成登录授权Author2.0页面
     * @return 获取authorication_code的url
     */
    public static String getLoginUrl() {
        return DOUBAN_AUTHORICATION_API + "?client_id=" + API_KEY + "&redirect_uri=" + BIG_BANG_BACK_URL + "&response_type=" + RESPONSE_TYPE;
    }

    /**
     * 获得授权post参数
     * @param url redirect back url
     * @return 授权post参数
     */
    public static String authorPostParameters(String url) {
        return "client_id=" + API_KEY + "&client_secret=" + DOUBAN_SECRET +
        "&redirect_uri=" + BIG_BANG_BACK_URL + "&grant_type=" + GRANT_TYPE + "&code=" + getParameter(url, "code");
    }

    /**
     * 获取url的host
     * @param url
     * @return
     */
    public static String getHost(String url) {
        if(url == null || url.length() == 0)
            return "";

        int doubleslash = url.indexOf("//");
        if(doubleslash == -1)
            doubleslash = 0;
        else
            doubleslash += 2;

        int end = url.indexOf('/', doubleslash);
        end = end >= 0 ? end : url.length();

        int port = url.indexOf(':', doubleslash);
        end = (port > 0 && port < end) ? port : end;

        return url.substring(doubleslash, end);
    }

    /**
     * 通过url和参数名称来获得参数值
     * @param url
     * @param name
     * @return
     * @throws URISyntaxException
     */
    public static String getParameter(String url, String name) {
        List<NameValuePair> args= null;
        try {
            args = URLEncodedUtils.parse(new URI(url), ENCODING_UTF_8);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        for (NameValuePair arg:args)
            if (arg.getName().equals(name))
               return arg.getValue();
        return null;
    }

    /**
     * 将json转换成为POJO
     * @param json 从网络下载的json
     * @return AccessToken类
     */
    public static AccessToken parseJson2AccessToken(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, AccessToken.class);
    }

    /**
     * 打开登录页面
     * @param context 环境变量
     */
    public static void startLoginActivity(Context context) {
        Intent i = new Intent(context, LoginActivity.class);
        context.startActivity(i);
    }
}
