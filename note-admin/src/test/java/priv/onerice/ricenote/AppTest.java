package priv.onerice.ricenote;

import cn.hutool.crypto.digest.DigestUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import priv.onerice.ricenote.base.RiceConst;

import java.io.UnsupportedEncodingException;

public class AppTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String pwd = DigestUtil.md5Hex(RiceConst.DEFAULT_PASSWORD + "m7tY12WsYD");
        System.out.println(pwd );
        String encode = bCryptPasswordEncoder.encode(pwd );
        System.out.println(encode);
    }
}
