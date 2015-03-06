package bigbangtheory.display.yao.bigbangtheory.authorication;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.inject.Inject;

import bigbangtheory.display.yao.bigbangtheory.core.resource.ILifeResource;

import static bigbangtheory.display.yao.bigbangtheory.core.client.IBigBangTheoryConstants.BIG_BANG_SHAREDPREFERENCES;

/**
 * 授权token存取到sharedpreferences的处理类
 * Created by JimmyandHurry on 2015/3/6.
 */
public class AuthorTokenResource extends ILifeResource {
    /**
     * access_token存取key
     */
    private static final String ACCESS_TOEKN_KEY = "bigbangtheorytoken";

    @Inject
    public AuthorTokenResource(Context context) {
        super(context);
    }

    /**
     * 获取sharedpreferences
     * @return SharedPreferences
     */
    protected SharedPreferences getSharedPreferences() {
        return getApplicationContext().getSharedPreferences(BIG_BANG_SHAREDPREFERENCES, Context.MODE_PRIVATE);
    }

    /**
     * 获取SharedPrferences.Editor
     * @return SharedPrferences.Editor
     */
    protected SharedPreferences.Editor getEditor() {
        return getSharedPreferences().edit();
    }

    /**
     * 存储token到sharedpreferences
     * @param tokenJson token
     */
    public void storeOAuthor2Token(String tokenJson) {
        SharedPreferences.Editor editor = getEditor();
        editor.putString(ACCESS_TOEKN_KEY, tokenJson);
        editor.commit();
    }

    /**
     * 将access_token从sharedpreferences中取出
     * @return access_token
     */
    public String retrieveOAuthor2Token() {
        return getSharedPreferences().getString(ACCESS_TOEKN_KEY, "");
    }

    /**
     *  从Sharedprefrences当中删除access_token
     * @return 是否成功
     */
    public boolean deleteAccessToken() {
        SharedPreferences.Editor editor = getEditor();
        editor.remove(ACCESS_TOEKN_KEY);
        return editor.commit();
    }


}
