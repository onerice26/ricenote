package priv.onerice.ricenote.base;

/**
 * @author onerice
 * @date 2023/4/10
 * @apiNote
 */
public interface RiceConst {
    /******* 系统默认值 ********/
    String DEFAULT_PASSWORD = "onerice_123456";
    String DEFAULT_AVATAR = "";

    /******* 用户状态 ********/
    String USER_TYPE_OK = "0";
    String USER_TYPE_LOCK = "5";

    /******** 业务类型 ********/
    String BUSINESS_LOGIN = "login";

    /******* XXX前缀 ********/
    String CAPTCHA_KEY = "captcha";

    /******* 图像验证码 ********/
    String BUSINESS_LOGIN_CAPTCHA = BUSINESS_LOGIN + ":" + CAPTCHA_KEY;
}
