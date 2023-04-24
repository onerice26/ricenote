package priv.onerice.ricenote.core.controller;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.onerice.ricenote.base.Result;

import java.awt.image.BufferedImage;
import java.util.UUID;

@RestController
public class LoginController {

    @Autowired
    private Producer producer;

    @GetMapping("/captcha")
    public Result Captcha() {
        String text = producer.createText();
        String key = UUID.randomUUID().toString();
        BufferedImage image = producer.createImage(text);
        /*ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.*/
        return Result.success();
    }
    @GetMapping("/captcha1")
    public Result Captcha1() {
        String text = producer.createText();
        String key = UUID.randomUUID().toString();
        BufferedImage image = producer.createImage(text);
        /*ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.*/
        return Result.success();
    }

}

