package bigbangtheory.display.yao.bigbangtheory.core.client;

/**
 * Created by JimmyandHurry on 2015/2/1.
 */
public interface IBigBangTheoryConstants {
    /** */
    String PROTOCOL_HTTPS = "https"; //$NON-NLS-1$
    String PROTOCOL_HTTP = "http"; //$NON-NLS-1$
    /** */
    String HOST_API = "www.douban.com"; //$NON-NLS-1$
    /** */
    String URL_API = PROTOCOL_HTTPS + "://" + HOST_API; //$NON-NLS-1$
    /** */
    String REDIRECT_HOST = "www.bigbangtheoryapp.com";
    /** */
    String DOUBAN_API_KEY = "02dd853b1bd170e008988fb1537452a0";
    /** */
    String DUOBAN_API_SECRET = "c15b6330e7180b6a";
    /** */
    String SEGMENT_SERVICE = "/service";
    /** */
    String SEGMENT_AUTH2 = "/auth2";
    /** */
    String SEGMENT_AUTH = "/auth";
    /** */
    String SEGMENT_AUTHOR_HOST = URL_API + SEGMENT_SERVICE + SEGMENT_AUTH2 + SEGMENT_AUTH;
    /** */
    String SEGMENT_PARAM_CLIENT = "client_id";
    /** */
    String SEGMENT_PARAM_REDIRECT_URL = "redirect_uri";
    /** */
    String SEGMENT_PARAM_RESPONSE_TYPE = "response_type";

    /** */
    String SETGMENT_AUTHOR_PARAMS = "";
    /** */
    String CHARSET_UTF8 = "UTF-8"; //$NON-NLS-1$
    /** */
    String CHARSET_ISO_8859_1 = "ISO-8859-1"; //$NON-NLS-1$

    String DOUBAN_HOST = "www.douban.com";
    String DOUBAN_API = PROTOCOL_HTTPS + "://" + DOUBAN_HOST; //$NON-NLS-1$

    String BIG_BANG_BACK_HOST = "www.bigbangtheorybooks.com";
    String BIG_BANG_BACK = "/back";
//    String DOUBAN_BACK_URL = "www.bigbangtheorybooks.com/back";
    String BIG_BANG_BACK_URL = PROTOCOL_HTTP + "://" + BIG_BANG_BACK_HOST + BIG_BANG_BACK;
    String DOUBAN_SERVICE = "/service";
    String DOUBAN_AUTH2 = "/auth2";
    String DOUBAN_AUTH = "/auth";
    String DOUBAN_TOKEN = "/token";
    String API_KEY = "02dd853b1bd170e008988fb1537452a0";
    String RESPONSE_TYPE = "code";
    String DOUBAN_SECRET = "c15b6330e7180b6a";
    String GRANT_TYPE = "authorization_code";

    String DOUBAN_AUTHORICATION_API = DOUBAN_API + DOUBAN_SERVICE + DOUBAN_AUTH2 + DOUBAN_AUTH;
    String DOUBAN_AUTHORICATION_API_TOKEN = DOUBAN_API + DOUBAN_SERVICE + DOUBAN_AUTH2 + DOUBAN_TOKEN;
    String AUTH_TOKEN = "Bearer";

    String BIG_BANG_SHAREDPREFERENCES = "bigbangtheorysharedpreferences";

    /**
     * Encoding
     */
    String ENCODING_UTF_8 = "UTF-8";

    /**
     * 个人信息
     */
    String INFO_DOUBAN_API = "https://api.douban.com/v2/user/~me";


}
