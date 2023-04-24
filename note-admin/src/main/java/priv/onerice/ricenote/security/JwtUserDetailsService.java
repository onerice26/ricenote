package priv.onerice.ricenote.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import priv.onerice.ricenote.core.entity.SysOrgRolePermission;
import priv.onerice.ricenote.core.entity.SysPermission;
import priv.onerice.ricenote.core.entity.SysUser;
import priv.onerice.ricenote.core.entity.SysUserOrgRole;
import priv.onerice.ricenote.core.service.ISysOrgRolePermissionService;
import priv.onerice.ricenote.core.service.ISysPermissionService;
import priv.onerice.ricenote.core.service.ISysUserOrgRoleService;
import priv.onerice.ricenote.core.service.ISysUserService;
import priv.onerice.ricenote.utils.StrUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author onerice
 * @date 2023/3/29
 * @apiNote
 */
@Service
@Slf4j
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysUserOrgRoleService userOrgRoleService;

    @Autowired
    private ISysOrgRolePermissionService rolePermissionService;

    @Autowired
    private ISysPermissionService permissionsService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StrUtil.isBlank(username)) {
            log.info("登录用户：{} 不存在", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        SysUser sysUser = userService.getUserByName(username);
        if (ObjectUtils.isEmpty(sysUser)) {
            log.info("登录用户：{} 不存在", username);
            throw new UsernameNotFoundException("登录用户：" + username + " 不存在");
        }
        JwtUserDetails jwtUserDetails = new JwtUserDetails();
        jwtUserDetails.setUser(sysUser);
        // 查找组织-->角色
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        // 用户>>角色
        List<SysUserOrgRole> userOrgRoles = userOrgRoleService.getRoleByUserId(sysUser.getId());
        List<String> roles = userOrgRoles.stream().distinct().map(SysUserOrgRole::getId).collect(Collectors.toList());
        // 用户>>角色>>权限
        List<SysOrgRolePermission> permissionsByRoles = rolePermissionService.getPermissionsByRoles(roles);
        List<String> permissionIds = permissionsByRoles.stream().distinct().map(SysOrgRolePermission::getId).collect(Collectors.toList());
        // 用户>>角色>>权限>>角色详情
        List<SysPermission> permissions = permissionsService.getPermissionsByUserId(permissionIds);
        for (SysPermission permission : permissions) {
            authorities.add(new SimpleGrantedAuthority(permission.getCode()));
        }
        jwtUserDetails.setAuthorities(authorities);
        return jwtUserDetails;
    }
}
