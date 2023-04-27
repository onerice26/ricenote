package priv.onerice.ricenote.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import priv.onerice.ricenote.core.entity.SysOrgRole;
import priv.onerice.ricenote.core.mapper.SysOrgRoleMapper;
import priv.onerice.ricenote.core.service.ISysOrgRoleService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 系统组织角色表 服务实现类
 * </p>
 *
 * @author onerice
 * @since 2023-03-30
 */
@Service
public class SysOrgRoleServiceImpl extends ServiceImpl<SysOrgRoleMapper, SysOrgRole> implements ISysOrgRoleService {

    @Override
    public List<SysOrgRole> getRoleByOrgId(String orgId) {
        if (StringUtils.isBlank(orgId)) {
            return new ArrayList<SysOrgRole>();
        }
        LambdaQueryWrapper<SysOrgRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(true, SysOrgRole::getOrgId, orgId);
        return getBaseMapper().selectList(queryWrapper);
    }
}
