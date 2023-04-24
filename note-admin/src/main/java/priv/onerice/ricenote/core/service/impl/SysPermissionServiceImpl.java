package priv.onerice.ricenote.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import priv.onerice.ricenote.core.entity.SysPermission;
import priv.onerice.ricenote.core.mapper.SysPermissionMapper;
import priv.onerice.ricenote.core.service.ISysPermissionService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 系统权限表 服务实现类
 * </p>
 *
 * @author onerice
 * @since 2023-03-30
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

    @Override
    public List<SysPermission> getPermissionsByUserId(List<String> permissions) {
        if (CollectionUtils.isEmpty(permissions)) {
            return new ArrayList<>();
        }
        LambdaQueryWrapper<SysPermission> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(true, SysPermission::getId, permissions);
        return getBaseMapper().selectList(queryWrapper);
    }
}
