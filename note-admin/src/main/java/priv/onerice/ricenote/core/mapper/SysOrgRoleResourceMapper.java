package priv.onerice.ricenote.core.mapper;

import org.apache.ibatis.annotations.Param;
import priv.onerice.ricenote.core.entity.SysOrgRoleResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import priv.onerice.ricenote.core.entity.SysResource;

import java.util.List;

/**
 * <p>
 * 系统组织角色资源表 Mapper 接口
 * </p>
 *
 * @author onerice
 * @since 2023-03-30
 */
public interface SysOrgRoleResourceMapper extends BaseMapper<SysOrgRoleResource> {

    /**
     * 通过角色IDS查询菜单信息
     *
     * @param roles 角色IDS
     * @return SysOrgRoleResource
     */
    List<SysResource> getResourcesByRoles(@Param("roles") List<String> roles);
}
