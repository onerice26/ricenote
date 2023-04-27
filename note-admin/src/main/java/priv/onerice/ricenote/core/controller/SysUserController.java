package priv.onerice.ricenote.core.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priv.onerice.ricenote.base.Result;
import priv.onerice.ricenote.core.entity.SysUser;
import priv.onerice.ricenote.core.service.ISysUserService;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author onerice
 * @since 2023-03-30
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户数据")
public class SysUserController {

    @Autowired
    private ISysUserService userService;

    @GetMapping("/{id}")
    @ApiOperation(value = "图形验证码", notes = "图形验证码")
    public Result getUserInfo(@PathVariable("id") String id) {
        SysUser info = userService.getById(id);
        // 用户菜单
        // 
        return Result.success(info);
    }

}
