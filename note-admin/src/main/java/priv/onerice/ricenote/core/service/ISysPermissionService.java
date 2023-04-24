package priv.onerice.ricenote.core.service;

import priv.onerice.ricenote.core.entity.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 系统权限表 服务类
 * </p>
 *
 * @author onerice
 * @since 2023-03-30
 */
public interface ISysPermissionService extends IService<SysPermission> {

    /**
     * 查询权限详情
     *
     * @return
     */
    List<SysPermission> getPermissionsByUserId(List<String> permissions);
}
