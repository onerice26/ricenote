package priv.onerice.ricenote.config.soToken;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import priv.onerice.ricenote.core.entity.SysOrgRole;
import priv.onerice.ricenote.core.entity.SysPermission;
import priv.onerice.ricenote.core.entity.SysUser;
import priv.onerice.ricenote.core.service.ISysOrgRolePermissionService;
import priv.onerice.ricenote.core.service.ISysUserOrgRoleService;
import priv.onerice.ricenote.core.service.ISysUserService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义权限验证接口扩展
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysUserOrgRoleService userOrgRoleService;

    @Autowired
    private ISysOrgRolePermissionService orgRolePermissionService;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询权限
        String userId = loginId.toString();
        SysUser userInfo = userService.getById(userId);
        String orgId = userInfo.getOrgId();// 用户默认组织
        List<SysOrgRole> orgRole = userOrgRoleService.getRoleByUserIdAndOrgId(userId, orgId);
        List<String> currentRoleIds = orgRole.stream().map(SysOrgRole::getId).collect(Collectors.toList());
        List<SysPermission> currentPermissions = orgRolePermissionService.getPermissionsByRoles(currentRoleIds);
        return currentPermissions.stream().distinct().map(SysPermission::getCode).collect(Collectors.toList());
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询角色
        String userId = loginId.toString();
        SysUser userInfo = userService.getById(userId);
        String orgId = userInfo.getOrgId();// 用户默认组织
        List<SysOrgRole> orgRole = userOrgRoleService.getRoleByUserIdAndOrgId(userId, orgId);
        return orgRole.stream().map(SysOrgRole::getCode).collect(Collectors.toList());
    }


}
