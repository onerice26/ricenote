package priv.onerice.ricenote.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import priv.onerice.ricenote.core.entity.SysOrgRole;

import java.util.List;

/**
 * <p>
 * 系统组织角色表 服务类
 * </p>
 *
 * @author onerice
 * @since 2023-03-30
 */
public interface ISysOrgRoleService extends IService<SysOrgRole> {

    /**
     * 获取组织下的所有角色
     * @param orgId
     * @return
     */
    List<SysOrgRole> getRoleByOrgId(String orgId);
}
