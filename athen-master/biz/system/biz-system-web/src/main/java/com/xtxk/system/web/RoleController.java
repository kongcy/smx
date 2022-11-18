package com.xtxk.system.web;

import com.xtxk.core.exception.Assert;
import com.xtxk.core.json.JsonResult;
import com.xtxk.core.util.RequestUtils;
import com.xtxk.core.util.U;
import com.xtxk.system.api.DTO.SysAuthorDetailDTO;
import com.xtxk.system.api.DTO.SysRoleDetailDTO;
import com.xtxk.system.api.model.SysRole;
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
 * 角色管理
 */
@Api(tags = "角色管理")
@Controller
@RequestMapping(value = "/system/api")
public class RoleController {
    @Autowired
    private SystemService systemService;

    @GetMapping(value = "/role/findRoleList")
    @ApiOperation("查询角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "角色名", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "code", value = "角色编码", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "state", value = "角色状态", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "currentPage", value = "当前页", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "条数", dataType = "int", paramType = "query")
    })
    public JsonResult findRoleList() {
        SysRole sysRole = RequestUtils.convertObj(SysRole.class);
        return pageList("查询成功!", systemService.finsPageRoleByCondition(sysRole));
    }

    @PostMapping(value = "/role/create")
    @ApiOperation("新增角色")
    public JsonResult create(@RequestBody SysRoleDetailDTO role) {
        return success("添加成功", systemService.saveRole(role));
    }

    @RequestMapping(value = "/role/update", method = RequestMethod.POST)
    @ApiOperation("更新角色")
    public JsonResult update(@RequestBody SysRoleDetailDTO role) {
        return success("更新成功", systemService.updateRole(role));
    }

    @RequestMapping(value = "/role/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation("删除角色")
    public JsonResult delete(@RequestParam String roleId) {
        systemService.deleteRoleById(roleId);
        return success("删除成功!");
    }

    @RequestMapping(value = "/role/batchDelete", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation("批量删除角色")
    public JsonResult batchDelete(@RequestParam String ids) {
        Assert.checkIsTrue(U.isBlank(ids),"请至少选择一个角色！");
        systemService.batchDeleteRole(ids.split(","));
        return success("删除成功!");
    }

    @RequestMapping(value = "/role/findUserByRoleId", method = RequestMethod.GET)
    @ApiOperation("查询角色下所有的用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色Id", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "currentPage", value = "当前页", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "条数", dataType = "int", paramType = "query")
    })
    public JsonResult findUserByRoleId(@RequestParam String roleId) {
        return pageList("查询成功!", systemService.findUserByRoleId(roleId));
    }

    @RequestMapping(value = "/role/findResByRoleId", method = RequestMethod.GET)
    @ApiOperation("查询角色下所有菜单的操作权限")
    public JsonResult findResByRoleId(@RequestParam String roleId) {
        return success("查询成功", systemService.GetResourceTreeHasSelect(roleId));
    }

    @RequestMapping(value = "/role/authorize", method = RequestMethod.POST)
    @ApiOperation("角色授权")
    public JsonResult authorize(@RequestBody SysAuthorDetailDTO detailDTO) {
        return success("授权成功", systemService.authorize(detailDTO));
    }

}
