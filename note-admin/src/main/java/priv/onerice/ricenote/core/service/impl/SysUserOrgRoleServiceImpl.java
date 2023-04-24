package priv.onerice.ricenote.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import priv.onerice.ricenote.core.entity.SysUserOrgRole;
import priv.onerice.ricenote.core.mapper.SysUserOrgRoleMapper;
import priv.onerice.ricenote.core.service.ISysUserOrgRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<SysUserOrgRole> getRoleByUserId(String userId) {
        if (StringUtils.isBlank(userId)) {
            return new ArrayList<>();
        }
        LambdaQueryWrapper<SysUserOrgRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(true, SysUserOrgRole::getUserId, userId);
        return getBaseMapper().selectList(queryWrapper);
    }
}
