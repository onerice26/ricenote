package priv.onerice.ricenote.base.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author onerice
 * @date 2023/4/26
 * @apiNote
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserDTO implements Serializable {
    String username;
    String password;
    String captcha;
}
