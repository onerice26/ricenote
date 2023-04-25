package priv.onerice.ricenote;

import cn.hutool.crypto.digest.DigestUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import priv.onerice.ricenote.base.RiceConst;

import java.io.UnsupportedEncodingException;

public class AppTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String p = DigestUtil.md5Hex(RiceConst.DEFAULT_PASSWORD);
        String pwd = DigestUtil.md5Hex(p + "m7tY12WsYD");
        System.out.println(p);
        String encode = bCryptPasswordEncoder.encode(pwd);
        System.out.println(encode);
        /*198a06541cdf6bdff0b8b901f1158bea
        $2a$10$oHOCWJcAReihmW84zYSWru3MgmCrlNsMqz.C2WpQBweNgCLngfm9G*/
    }
}
