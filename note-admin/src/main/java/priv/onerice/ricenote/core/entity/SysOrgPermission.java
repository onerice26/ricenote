package priv.onerice.ricenote.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 组织角色权限表
 * </p>
 *
 * @author onerice
 * @since 2023-03-30
 */
@Getter
@Setter
@TableName("sys_org_permission")
public class SysOrgPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String id;

    /**
     * 组织ID
     */
    private String orgId;

    /**
     * 权限ID
     */
    private String permissionsId;
}
