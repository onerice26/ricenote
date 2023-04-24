package priv.onerice.ricenote.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import priv.onerice.ricenote.core.entity.SysUserOrgRole;

import java.util.List;

/**
 * <p>
 * 系统用户组织角色 服务类
 * </p>
 *
 * @author onerice
 * @since 2023-03-30
 */
public interface ISysUserOrgRoleService extends IService<SysUserOrgRole> {

    /**
     * 通过用户ID查询角色信息
     *
     * @param userId 用户ID
     * @return list
     */
    List<SysUserOrgRole> getRoleByUserId(String userId);
}
