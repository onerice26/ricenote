package priv.onerice.ricenote.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import priv.onerice.ricenote.core.entity.SysUser;
import priv.onerice.ricenote.core.mapper.SysUserMapper;
import priv.onerice.ricenote.core.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author onerice
 * @since 2023-03-30
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Override
    public SysUser getUserByName(String username) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(true, SysUser::getUsername, username);
        return getOne(queryWrapper);
    }
}
