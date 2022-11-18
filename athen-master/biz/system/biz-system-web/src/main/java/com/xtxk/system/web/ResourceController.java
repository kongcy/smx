package com.xtxk.system.web;

import com.xtxk.core.annotation.NotNeedPermission;
import com.xtxk.core.exception.Assert;
import com.xtxk.core.json.JsonResult;
import com.xtxk.system.api.model.SysResource;
import com.xtxk.system.api.service.SystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.xtxk.core.json.JsonResult.success;

/**
 * 菜单资源管理
 */
@Controller
@Api(tags = "菜单管理")
@RequestMapping(value = "/system/api")
public class ResourceController {

    @Autowired
    private SystemService systemService;

    @GetMapping(value = "/res/findResList")
    @ApiOperation(value = "分页查找菜单列表")
    public JsonResult findMenuList() {
        return success("查询成功", "");
    }

    @RequestMapping(value = "/res/findResourceTree", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户权限树形菜单列表(所有属性)")
    public JsonResult findResourceTree(@RequestParam String userId) {
        return success("查询成功", systemService.findResourceTree(userId));
    }

    @RequestMapping(value = "/res/findTreeMenu", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户授权树形菜单")
    public JsonResult findMenu(String userId) {
        return success("查询成功", systemService.findTreeMenu(userId));
    }
    /**
     * 读取单个资源
     */
    @NotNeedPermission
    @GetMapping(value = "/res/findRes")
    @ApiOperation(value = "通过id查找菜单")
    public JsonResult findRes(@RequestParam String id) {
        return success("查询成功", systemService.findResById(id));
    }
    /**
     * 新增菜单
     * @param res
     * @return
     */
    @RequestMapping(value = "/res/create", method = RequestMethod.POST)
    @ApiOperation(value = "新增菜单")
    public JsonResult create(@RequestBody SysResource res) {
        return success("添加成功", systemService.saveResource(res));
    }
    /**
     * 更新菜单
     */
    @RequestMapping(value = "/res/update", method = RequestMethod.POST)
    @ApiOperation(value = "更新菜单")
    public JsonResult update(@RequestBody SysResource resource) {
        return success("更新成功", systemService.updateResource(resource));
    }
    /**
     * 删除资源
     * @param resId 资源ID
     */
    @RequestMapping(value = "/res/delete", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "删除资源")
    public JsonResult delete(@RequestParam String resId) {
        return success("删除资源成功", systemService.deleteResource(resId));
    }
    @RequestMapping(value = "/res/batchDelete", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "删除资源")
    public JsonResult batchDelete(@RequestParam String resIds) {
        Assert.checkNull(resIds,"请至少选择一个资源菜单！");
        return success("删除资源成功", systemService.batchDeleteResource(resIds.split(",")));
    }
    @RequestMapping(value = "/res/findResIdsByRoleId",method = RequestMethod.GET)
    @ApiOperation(value = "根据角色ID查询菜单")
    public JsonResult findResByRoleId(@RequestParam String roleId){
        return success("根据角色ID查询菜单成功",systemService.findResIdsByRoleId(roleId));
    }
}
