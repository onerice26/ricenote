package priv.onerice.ricenote.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import priv.onerice.ricenote.core.entity.SysOrgRolePermission;
import priv.onerice.ricenote.core.mapper.SysOrgRolePermissionMapper;
import priv.onerice.ricenote.core.service.ISysOrgRolePermissionService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 系统组织角色权限表 服务实现类
 * </p>
 *
 * @author onerice
 * @since 2023-03-30
 */
@Service
public class SysOrgRolePermissionServiceImpl extends ServiceImpl<SysOrgRolePermissionMapper, SysOrgRolePermission> implements ISysOrgRolePermissionService {

    @Override
    public List<SysOrgRolePermission> getPermissionsByRoles(List<String> roles) {
        if (CollectionUtils.isEmpty(roles)) {
            return new ArrayList<>();
        }
        LambdaQueryWrapper<SysOrgRolePermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(true, SysOrgRolePermission::getRoleId, roles);
        return getBaseMapper().selectList(queryWrapper);
    }
}
