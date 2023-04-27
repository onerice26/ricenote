package priv.onerice.ricenote.core.service;

import priv.onerice.ricenote.core.entity.SysOrgRolePermission;
import com.baomidou.mybatisplus.extension.service.IService;
import priv.onerice.ricenote.core.entity.SysPermission;

import java.util.List;

/**
 * <p>
 * 系统组织角色权限表 服务类
 * </p>
 *
 * @author onerice
 * @since 2023-03-30
 */
public interface ISysOrgRolePermissionService extends IService<SysOrgRolePermission> {

    /**
     *
     * @param roles
     * @return
     */
    List<SysPermission> getPermissionsByRoles(List<String> roles);
}
