package priv.onerice.ricenote.core.service;

import priv.onerice.ricenote.core.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author onerice
 * @since 2023-03-30
 */
public interface ISysUserService extends IService<SysUser> {

    SysUser getUserByName(String username);
}
