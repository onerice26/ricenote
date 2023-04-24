package priv.onerice.ricenote.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 系统用户组织角色
 * </p>
 *
 * @author onerice
 * @since 2023-03-30
 */
@Getter
@Setter
@TableName("sys_user_org_role")
public class SysUserOrgRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String id;

    /**
     * 资质角色ID
     */
    private String roleId;

    /**
     * 用户ID
     */
    private String userId;
}
