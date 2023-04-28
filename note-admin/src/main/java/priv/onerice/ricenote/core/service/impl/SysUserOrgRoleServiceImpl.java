package priv.onerice.ricenote.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import priv.onerice.ricenote.core.entity.SysOrgRole;
import priv.onerice.ricenote.core.entity.SysUserOrg;
import priv.onerice.ricenote.core.entity.SysUserOrgRole;
import priv.onerice.ricenote.core.mapper.SysUserOrgRoleMapper;
import priv.onerice.ricenote.core.service.ISysOrgRoleService;
import priv.onerice.ricenote.core.service.ISysUserOrgRoleService;
import priv.onerice.ricenote.core.service.ISysUserOrgService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统用户组织角色 服务实现类
 * </p>
 *
 * @author onerice
 * @since 2023-03-30
 */
@Service
public class SysUserOrgRoleServiceImpl extends ServiceImpl<SysUserOrgRoleMapper, SysUserOrgRole> implements ISysUserOrgRoleService {

    @Autowired
    private ISysOrgRoleService orgRoleService;

    @Autowired
    private ISysUserOrgService userOrgService;

    @Override
    public List<SysUserOrgRole> getOrgRoleByUserId(String userId) {
        if (StringUtils.isBlank(userId)) {
            return new ArrayList<>();
        }
        LambdaQueryWrapper<SysUserOrgRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(true, SysUserOrgRole::getUserId, userId);
        return getBaseMapper().selectList(queryWrapper);
    }

    @Override
    public List<SysOrgRole> getRoleByUserIdAndOrgId(String userId, String orgId) {
        if (StringUtils.isBlank(userId)) {
            return new ArrayList<>();
        }
        List<SysUserOrgRole> roles = getOrgRoleByUserId(userId);// 用户所有角色
        List<String> roleIds = roles.stream().map(SysUserOrgRole::getRoleId).collect(Collectors.toList());
        if (StringUtils.isBlank(orgId)) {
            List<SysUserOrg> orgByUser = userOrgService.getOrgByUserId(userId);
            if (orgByUser.isEmpty()) return new ArrayList<>();
            orgId = orgByUser.get(0).getOrgId();
        }
        List<SysOrgRole> orgRoles = orgRoleService.getRoleByOrgId(orgId); // 用户所有组织
        //List<SysOrgRole> orgRole = orgRoles.stream().filter(ie -> ie.getOrgId().equals(orgId)).collect(Collectors.toList());
        return orgRoles.stream().filter(ie -> roleIds.contains(ie.getId())).collect(Collectors.toList());
    }
}
