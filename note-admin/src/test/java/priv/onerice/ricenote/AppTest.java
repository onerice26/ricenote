package priv.onerice.ricenote;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import priv.onerice.ricenote.base.RiceConst;

public class AppTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode(RiceConst.DEFAULT_PASSWORD);
        System.out.println(encode);
    }
}
