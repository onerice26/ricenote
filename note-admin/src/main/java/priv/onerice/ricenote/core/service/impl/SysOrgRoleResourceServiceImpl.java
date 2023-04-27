package priv.onerice.ricenote.core.service.impl;

import org.springframework.util.CollectionUtils;
import priv.onerice.ricenote.core.entity.SysOrgRoleResource;
import priv.onerice.ricenote.core.entity.SysResource;
import priv.onerice.ricenote.core.mapper.SysOrgRoleResourceMapper;
import priv.onerice.ricenote.core.service.ISysOrgRoleResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 系统组织角色资源表 服务实现类
 * </p>
 *
 * @author onerice
 * @since 2023-03-30
 */
@Service
public class SysOrgRoleResourceServiceImpl extends ServiceImpl<SysOrgRoleResourceMapper, SysOrgRoleResource> implements ISysOrgRoleResourceService {

    @Override
    public List<SysResource> getResourcesByRoles(List<String> roles) {
        if (CollectionUtils.isEmpty(roles)) {
            return new ArrayList<>();
        }
        return getBaseMapper().getResourcesByRoles(roles);
    }
}
