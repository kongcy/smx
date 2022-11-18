package com.xtxk.system.web;

import com.xtxk.core.exception.Assert;
import com.xtxk.core.json.JsonResult;
import com.xtxk.core.util.RequestUtils;
import com.xtxk.system.api.model.SysOrganization;
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
 * @Description
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/2/21
 */
@Api(tags = "机构服务")
@Controller
@RequestMapping(value = "/system/api")
public class OrganizationController {
    @Autowired
    private SystemService systemService;

    @GetMapping(value = "/org/findOrgList")
    @ApiOperation(value = "查找机构列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "机构名称", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "code", value = "机构code", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "state", value = "启用状态", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "currentPage", value = "当前页", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "limit", value = "条数", dataType = "int", paramType = "query", required = true)
    })
    public JsonResult findOrgList() {
        SysOrganization organization = RequestUtils.convertObj(SysOrganization.class);
        return pageList("查询成功", systemService.findPageOrgByCondition(organization));
    }
    @GetMapping(value = "/org/findOrgTreeList")
    @ApiOperation(value = "查找树形机构列表")
    public JsonResult findOrgTreeList(){
        return success("查询成功",systemService.findOrgTreeList());
    }
    @PostMapping(value = "/org/create")
    @ApiOperation(value = "新增机构")
    public JsonResult create(@RequestBody SysOrganization organization) {
        return success("添加成功", systemService.saveOrganization(organization));
    }
    @PostMapping(value = "/org/update")
    @ApiOperation(value = "更新机构")
    public JsonResult update(@RequestBody SysOrganization organization) {
        return success("更新成功", systemService.updateOrganization(organization));
    }
    @RequestMapping(value = "/org/delete",method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation(value = "删除机构")
    public JsonResult delete(@RequestParam String orgId) {
        return success("删除成功", systemService.deleteOrganization(orgId));
    }
    @RequestMapping(value = "/org/batchDelete",method = {RequestMethod.POST,RequestMethod.GET})
    @ApiOperation(value = "批量删除机构")
    public JsonResult batchDelete(@RequestParam String orgIds) {
        Assert.checkNull(orgIds,"请至少选择一个机构！");
        return success("删除成功", systemService.batchDeleteOrganization(orgIds.split(",")));
    }
}
