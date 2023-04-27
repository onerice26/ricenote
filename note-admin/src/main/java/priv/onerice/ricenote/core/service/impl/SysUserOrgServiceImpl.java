package priv.onerice.ricenote.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import priv.onerice.ricenote.core.entity.SysUserOrg;
import priv.onerice.ricenote.core.mapper.SysUserOrgMapper;
import priv.onerice.ricenote.core.service.ISysUserOrgService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户组织表 服务实现类
 * </p>
 *
 * @author onerice
 * @since 2023-03-30
 */
@Service
public class SysUserOrgServiceImpl extends ServiceImpl<SysUserOrgMapper, SysUserOrg> implements ISysUserOrgService {

    @Override
    public List<SysUserOrg> getOrgByUserId(String userId) {
        if (StringUtils.isBlank(userId)) {
            return new ArrayList<SysUserOrg>();
        }
        LambdaQueryWrapper<SysUserOrg> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(true, SysUserOrg::getUserId, userId);
        return getBaseMapper().selectList(queryWrapper);
    }
}
