package priv.onerice.ricenote.core.mapper;

import org.apache.ibatis.annotations.Param;
import priv.onerice.ricenote.core.entity.SysOrgRolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import priv.onerice.ricenote.core.entity.SysPermission;

import java.util.List;

/**
 * <p>
 * 系统组织角色权限表 Mapper 接口
 * </p>
 *
 * @author onerice
 * @since 2023-03-30
 */
public interface SysOrgRolePermissionMapper extends BaseMapper<SysOrgRolePermission> {
    /**
     * 通过角色IDS查询权限信息
     * @param roles 角色IDS
     * @return SysPermission
     */
    List<SysPermission> getPermissionsByRoles(@Param("roles") List<String> roles);
}
