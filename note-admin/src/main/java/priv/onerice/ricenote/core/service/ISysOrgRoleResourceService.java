package priv.onerice.ricenote.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import priv.onerice.ricenote.core.entity.SysOrgRoleResource;
import priv.onerice.ricenote.core.entity.SysResource;

import java.util.List;

/**
 * <p>
 * 系统组织角色资源表 服务类
 * </p>
 *
 * @author onerice
 * @since 2023-03-30
 */
public interface ISysOrgRoleResourceService extends IService<SysOrgRoleResource> {
    /**
     *
     * @param roles
     * @return
     */
    List<SysResource> getResourcesByRoles(List<String> roles);
}
