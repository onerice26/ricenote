package priv.onerice.ricenote.core.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.onerice.ricenote.base.Result;
import priv.onerice.ricenote.base.vo.MenuVo;
import priv.onerice.ricenote.core.dto.SysUserDTO;
import priv.onerice.ricenote.core.entity.SysOrgRole;
import priv.onerice.ricenote.core.entity.SysResource;
import priv.onerice.ricenote.core.entity.SysUser;
import priv.onerice.ricenote.core.service.ISysOrgRoleResourceService;
import priv.onerice.ricenote.core.service.ISysUserOrgRoleService;
import priv.onerice.ricenote.core.service.ISysUserService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author onerice
 * @since 2023-03-30
 */
@RestController
@RequestMapping("/sys/user")
@Api(tags = "用户数据")
public class SysUserController {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysUserOrgRoleService userOrgRoleService;

    @Autowired
    private ISysOrgRoleResourceService orgRoleResourceService;

    @GetMapping("/list")
    @ApiOperation(value = "用户列表查询", notes = "用户数据")
    public Result getPageListInfo(@RequestBody SysUserDTO sysUserDTO) {
        return Result.success();
    }

    @GetMapping("/info")
    @ApiOperation(value = "用户首页信息", notes = "用户数据")
    public Result getUserInfo() {
        String loginId = StpUtil.getLoginIdAsString();
        JSONObject ret = new JSONObject();
        SysUser info = userService.getById(loginId);
        info.setPassword(null);
        info.setSalt(null);
        ret.put("userInfo", info);
        // 用户菜单
        List<SysOrgRole> orgRole = userOrgRoleService.getRoleByUserIdAndOrgId(info.getId(), info.getOrgId());
        List<String> orgRoleIds = orgRole.stream().distinct().map(SysOrgRole::getId).collect(Collectors.toList());
        List<SysResource> menuList = orgRoleResourceService.getResourcesByRoles(orgRoleIds);
        List<MenuVo> rootMenu = menuList.stream().filter(ie -> StringUtils.equals(ie.getParentId(), "root")).map(ie -> {
            MenuVo menuVo = new MenuVo();
            BeanUtils.copyProperties(ie, menuVo);
            return menuVo;
        }).collect(Collectors.toList());
        for (MenuVo menu : rootMenu) {
            menu.setChildren(treeMenuList(menuList, menu.getId()));
        }
        // 菜单树形
        ret.put("roleList", rootMenu);
        // 用户权限：按钮权限
        List<String> permissionList = StpUtil.getPermissionList();
        ret.put("permissions", permissionList);
        ret.put("userInfo", info);
        return Result.success(ret);
    }

    public List<MenuVo> treeMenuList(List<SysResource> menuList, String pid) {
        return menuList.stream().filter(ie -> StringUtils.equals(ie.getParentId(), pid)).map(ie -> {
            MenuVo menuVo = new MenuVo();
            BeanUtils.copyProperties(ie, menuVo);
            return menuVo;
        }).collect(Collectors.toList());
    }

}
