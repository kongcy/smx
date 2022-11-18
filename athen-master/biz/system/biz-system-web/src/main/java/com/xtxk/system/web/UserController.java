package com.xtxk.system.web;

import com.xtxk.core.exception.Assert;
import com.xtxk.core.json.JsonResult;
import com.xtxk.core.util.RequestUtils;
import com.xtxk.core.util.U;
import com.xtxk.system.api.DTO.SysUserRoleDTO;
import com.xtxk.system.api.DTO.SysUserDTO;
import com.xtxk.system.api.model.SysUser;
import com.xtxk.system.api.model.SysUserOrg;
import com.xtxk.system.api.service.SystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import static com.xtxk.core.json.JsonResult.pageList;
import static com.xtxk.core.json.JsonResult.success;

/**
 * 用户管理
 *
 * @author cheny
 * @version 1.0
 */
@Api(tags = "用户服务")
@RestController
@RequestMapping(value = "/system/api")
public class UserController {

    @Autowired
    private SystemService systemService;

    @GetMapping(value = "/user/findUserList")
    @ApiOperation(value = "分页查询用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "模糊查询（姓名、工号、手机号、身份证）", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "currentPage", value = "当前页", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "limit", value = "条数", dataType = "int", paramType = "query", required = true)
    })
    public JsonResult findUserList(String condition) {
      //  SysUser sysUser = RequestUtils.convertObj(SysUser.class);
        return pageList("查询成功", systemService.findPageUserByCondition(condition));
    }

    @PostMapping(value = "/user/create")
    @ApiOperation(value = "新增用户")
    public JsonResult create(@RequestBody SysUserDTO user) {
        user.setLoginIp(RequestUtils.getRealIp());
        return success("保存成功!", systemService.saveUser(user));
    }

    @PostMapping(value = "/user/update")
    @ApiOperation(value = "更新用户")
    public JsonResult update(@RequestBody SysUserDTO user) {
        return success("更新成功!", systemService.updateUser(user));
    }

    @RequestMapping(value = "/user/findRoleByUserId", method = RequestMethod.GET)
    @ApiOperation(value = "查找用户下角色")
    public JsonResult findRoleByUserId(@RequestParam String userId) {
        return success("查询成功", systemService.findRoleByUserId(userId));
    }

    @RequestMapping(value = "/user/bindUserRole", method = RequestMethod.POST)
    @ApiOperation(value = "用户绑定角色(支持批量)")
    public JsonResult bindUserRole(@RequestBody SysUserRoleDTO userRoleDTO) {
        return success("绑定成功", systemService.bindUserRole(userRoleDTO));
    }

    @RequestMapping(value = "/user/findUserListByOrgId", method = RequestMethod.GET)
    @ApiOperation(value = "查询组织机构下用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orgId", value = "组织机构ID", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "currentPage", value = "当前页", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "limit", value = "条数", dataType = "int", paramType = "query", required = true)
    })
    public JsonResult findUserListByOrgId(@RequestParam String orgId) {
        return pageList("查询成功", systemService.findUserListByOrgId(orgId));
    }

    @RequestMapping(value = "/user/bindUserOrg", method = RequestMethod.POST)
    @ApiOperation(value = "用户绑定机构")
    public JsonResult bindUserOrg(@RequestBody SysUserOrg userOrg) {
        return success("绑定成功", systemService.bindUserOrg(userOrg));
    }

    @RequestMapping(value = "/user/batchDelete", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "批量删除用户")
    public JsonResult batchDelete(@RequestParam String ids) {
        Assert.checkIsTrue(U.isBlank(ids), "请至少选择一个用户！");
        return success("删除成功!", systemService.batchDeleteUser(ids.split(",")));
    }

    @RequestMapping(value = "/user/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "删除用户")
    public JsonResult delete(@RequestParam String id) {
        return success("删除成功", systemService.deleteUserById(id));
    }

}
