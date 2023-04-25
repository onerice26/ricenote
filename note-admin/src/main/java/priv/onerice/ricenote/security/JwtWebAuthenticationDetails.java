package priv.onerice.ricenote.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import priv.onerice.ricenote.base.RiceConst;
import priv.onerice.ricenote.utils.RedisUtil;

import javax.servlet.http.HttpServletRequest;

public class JwtWebAuthenticationDetails extends WebAuthenticationDetails {

    private boolean imageCodeIsRight;

    public boolean getImageCodeIsRight() {
        return this.imageCodeIsRight;
    }

    public JwtWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        String rand = request.getParameter("rand");
        String captcha = request.getParameter("captcha");
        String saveCaptcha = RedisUtil.get(RiceConst.BUSINESS_LOGIN_CAPTCHA + ":" + rand);
        if (StringUtils.isNotEmpty(captcha) && captcha.equals(saveCaptcha)) {
            this.imageCodeIsRight = true;
        }
    }
}
