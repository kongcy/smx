package com.xtxk.system.web;

import com.xtxk.core.json.JsonResult;
import com.xtxk.core.util.RequestUtils;
import com.xtxk.system.api.model.SysLog;
import com.xtxk.system.api.model.SysLoginRecord;
import com.xtxk.system.api.service.SysLogService;
import com.xtxk.system.api.service.SysLoginRecordService;
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
 * @Description 日志系统
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/5/18
 */
@Api(tags = "系统日志")
@Controller
@RequestMapping(value = "/system/api/")
public class SysLogController {
    @Autowired
    private SysLogService logService;
    @Autowired
    private SysLoginRecordService loginRecordService;
    @Autowired
    private SystemService systemService;

    @GetMapping(value = "/log/findLogs")
    @ApiOperation("分页查询日志列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "limit", value = "条数", dataType = "int", paramType = "query")
    })
    public JsonResult findLogs() {
        SysLog sysLog = RequestUtils.convertObj(SysLog.class);
        return success("查询成功", logService.findLogs(sysLog));
    }

    @PostMapping(value = "/log/save")
    @ApiOperation("保存日志")
    public JsonResult saveLog(@RequestBody SysLog sysLog) {
        return success("添加成功", logService.saveLog(sysLog));
    }

    @GetMapping(value = "/log/findLoginPageRecord")
    @ApiOperation(value = "分页查询登录记录列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户标识(账号或ID)", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "beginTime", value = "登录时间", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态(-1登录失败,0锁定,1登录成功)", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "loginIp", value = "登录IP", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "state", value = "用户状态", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "currentPage", value = "当前页", dataType = "int", paramType = "query", required = true),
            @ApiImplicitParam(name = "limit", value = "条数", dataType = "int", paramType = "query", required = true)
    })
    public JsonResult findLoginPageRecord() {
        SysLoginRecord record = RequestUtils.convertObj(SysLoginRecord.class);
        return pageList("查询成功", loginRecordService.findPageRecordByCondition(record));
    }

    @GetMapping(value = "/log/findLoginRecord")
    @ApiOperation(value = "查询用户登录记录列表")
    public JsonResult findLoginRecord(@RequestParam String userId) {
        return success("查询成功", systemService.findLateLoginRecord(userId));
    }
}
