package bigbangtheory.display.yao.bigbangtheory.authorication;

import static bigbangtheory.display.yao.bigbangtheory.core.client.IBigBangTheoryConstants.BIG_BANG_SHAREDPREFERENCES;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import bigbangtheory.display.yao.bigbangtheory.login.AccessToken;

/**
 * SharedPreferences Utils类
 * Created by JimmyandHurry on 2015/3/1.
 */
public class AuthorTokenUtils {
//    /**
//     * access_token存取key
//     */
//    private static final String ACCESS_TOEKN_KEY = "bigbangtheorytoken";
//
//    /**
//     * 获取sharedpreferences
//     * @param context Activity环境变量
//     * @return SharedPreferences
//     */
//    public static SharedPreferences getSharedPreferences(Context context) {
//        return context.getSharedPreferences(BIG_BANG_SHAREDPREFERENCES, Context.MODE_PRIVATE);
//    }
//
//    /**
//     * 获取SharedPrferences.Editor
//     * @param context Activity环境变量
//     * @return SharedPrferences.Editor
//     */
//    public static SharedPreferences.Editor getEditor(Context context) {
//        return getSharedPreferences(context).edit();
//    }
//
//    /**
//     * 存储token到sharedpreferences
//     * @param tokenJson token
//     * @param context 环境变量
//     */
//    public static void storeOAuthor2Token(String tokenJson, Context context) {
//        SharedPreferences.Editor editor = getEditor(context);
//        editor.putString(ACCESS_TOEKN_KEY, tokenJson);
//        editor.commit();
//    }
//
//    /**
//     * 将access_token从sharedpreferences中取出
//     * @param context 环境变量
//     * @return access_token
//     */
//    public static String retrieveOAuthor2Token(Context context) {
//        return getSharedPreferences(context).getString(ACCESS_TOEKN_KEY, "");
//    }

//    private static final String ACCESS_TOEKN_KEY = "bigbangtheorytoken";
//
//    /**
//     * 获取sharedpreferences
//     * @param context Activity环境变量
//     * @return SharedPreferences
//     */
//    public static SharedPreferences getSharedPreferences(Context context) {
//        return context.getSharedPreferences(BIG_BANG_SHAREDPREFERENCES, Context.MODE_PRIVATE);
//    }
//
//    /**
//     * 获取SharedPrferences.Editor
//     * @param context Activity环境变量
//     * @return SharedPrferences.Editor
//     */
//    public static SharedPreferences.Editor getEditor(Context context) {
//        return getSharedPreferences(context).edit();
//    }
//
//    /**
//     * 存储token到sharedpreferences
//     * @param tokenJson token
//     * @param context 环境变量
//     */
//    public static void storeOAuthor2Token(String tokenJson, Context context) {
//        SharedPreferences.Editor editor = getEditor(context);
//        editor.putString(ACCESS_TOEKN_KEY, tokenJson);
//        editor.commit();
//    }
//
//    /**
//     * 将access_token从sharedpreferences中取出
//     * @param context 环境变量
//     * @return access_token
//     */
//    public static String retrieveOAuthor2Token(Context context) {
//        return getSharedPreferences(context).getString(ACCESS_TOEKN_KEY, "");
//    }

    /** 从SharedPreferences当中取出json，将json转换成为AccessToken
     * @param json access_token的string
     * @return AccessToken类
     */
    public static AccessToken getAccessToken(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, AccessToken.class);
    }

    /**
     * 直接查询token
     * @param token access_token实例
     * @return      access_token
     */
    public static String getTokenString(AccessToken token){
        if(token != null)  {
            return token.getAccess_token();
        }
        return null;
    }

    /**
     * 通过AccessToken类的json，提取access_token这一属性
     * @param json AccessToken类的json
     * @return access_token
     */
    public static String getAccessTokenStringByAccessTokenJson(String json) {
        Gson gson = new Gson();
        AccessToken token = gson.fromJson(json, AccessToken.class);
        if(token != null)  {
            return token.getAccess_token();
        }
        return null;
    }

//    /**
//     * 从Sharedprefrences当中删除access_token
//     * @param context 环境变量
//     */
//    public static void deleteAccessToken(Context context) {
//        SharedPreferences.Editor editor = getEditor(context);
//        editor.remove(ACCESS_TOEKN_KEY);
//        editor.commit();
//    }
}
