package priv.onerice.ricenote.utils;

import cn.dev33.satoken.secure.SaSecureUtil;

public class PasswordUtil {

    /**
     * 指定公钥存放文件
     */
    private static String PRIVATE_KEY_FILE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAPIlQ21gGzL5ThGqkVV2T4Cn45dujaoBMbsHUOsoplKp2PcHJ9VLMLSSLLeS4yb6Gdg8/3rOtnTmhRsKO8INZKJzyz6ibfJyW0gAlHU873GYZZHfrvs14IxL+DfL0hpaSovmwmC1RMMDW54TuVgQOFyHNln3xroKXvnl/bBt5YvNAgMBAAECgYEAwWwepjnEwD0ArX/wQ7IT45M4a3g11PzLOrinXJDF+vYNh5XBrdE4AKVa7vB4XmQdTOu8r957RHn3DRhpj6KfJM8zAXP1rRhty1GA0ihlGSg7kdBu+363qgwBsaIqFryvPQfO8xjKytFmrsmb+PpBWvb0hI8Ci5V3bu0VS1X99YkCQQD/Q4QSNPKjwfZaSEuVyOZR1znUnR7tzxEmFz3Ltm5aVqSZbZ0+1G8yuPnRO8EOo8+SKPjsyAtKRtJqDBbc/UvLAkEA8tgPqEHV41av0+mKIUB0Tg3SCIDglhmOTKi4zjNQ+HYNPB4qUtbEt9W5LayhX0iwPj2vvN09pA8YggkrHEzDxwJAZ40iFKi6Dchvgax2TSsUk/q1kRS0KVCo6kTfYrZ9CdTALSWUdpqDCZaUcVbGOIQnfml0ocl9GwKj9TU2SSvQBQJBAJTmVfNdKsCSTo0pDUEGPEn/v2DnZM2kLdRjP61vWZ5/A+5wsJLKjSQLL9gJmYjLEQWY3UB4h2sotmZFKOHZT9ECQG0H16fKIX3fAPVNvcTXmmgY4lsCXEwngg82LfYGvD5N+/S3lt+J7coW6VC6fRW0AIiy0wUyVtMRrLBLNDqsnXk=";
    /**
     * 指定私钥存放文件
     */
    private static String PUBLIC_KEY_FILE = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDyJUNtYBsy+U4RqpFVdk+Ap+OXbo2qATG7B1DrKKZSqdj3ByfVSzC0kiy3kuMm+hnYPP96zrZ05oUbCjvCDWSic8s+om3ycltIAJR1PO9xmGWR3677NeCMS/g3y9IaWkqL5sJgtUTDA1ueE7lYEDhchzZZ98a6Cl755f2wbeWLzQIDAQAB";


    public static String encrypt(String password) {
        return SaSecureUtil.rsaEncryptByPublic(PUBLIC_KEY_FILE, password);
    }

    public static String decrypt(String password) {
        return SaSecureUtil.rsaDecryptByPrivate(PRIVATE_KEY_FILE, password);
    }

    public static void main(String[] args) throws Exception {
        /*Map<String, String> map = SaSecureUtil.rsaGenerateKeyPair();
        System.out.println(map);*/
        String s1 = SaSecureUtil.md5("18483223986&123456");
        String s = SaSecureUtil.md5BySalt(s1, "m7tY12WsYD");
        String encrypt = encrypt(s);
        System.out.println(encrypt);
        System.out.println(decrypt(encrypt));
        /*String encrypt = SaSecureUtil.rsaEncryptByPublic(map.get("public"), "18483223986" + "m7tY12WsYD" + "8a2149b5f1339962b17a0a29b7e7d269");
        System.out.println(encrypt);
        System.out.println(SaSecureUtil.rsaDecryptByPrivate(map.get("private"), encrypt));*/
    }
}
