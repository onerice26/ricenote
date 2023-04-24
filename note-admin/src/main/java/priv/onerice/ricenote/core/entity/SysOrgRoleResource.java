package priv.onerice.ricenote.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 系统组织角色资源表
 * </p>
 *
 * @author onerice
 * @since 2023-03-30
 */
@Getter
@Setter
@TableName("sys_org_role_resource")
public class SysOrgRoleResource implements Serializable {

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
     * 资质资源ID
     */
    private String resourceId;
}
