package priv.onerice.ricenote.core.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserDTO implements Serializable {

    private String username;

    private String orgId;
}
