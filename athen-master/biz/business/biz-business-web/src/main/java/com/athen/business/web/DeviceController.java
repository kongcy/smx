package com.athen.business.web;

import com.athen.business.api.dto.DeviceBindDTO;
import com.athen.business.api.dto.LendRecordDTO;
import com.athen.business.api.model.BizDevice;
import com.athen.business.api.model.BizDeviceBackRecord;
import com.athen.business.api.model.BizDeviceLendRecord;
import com.athen.business.api.model.BizDeviceStore;
import com.athen.business.service.DeviceServiceImpl;
import com.xtxk.core.json.JsonResult;
import com.xtxk.core.util.RequestUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.xtxk.core.json.JsonResult.*;

/**
 * @author chenying
 * @date 2022/10/17 1:15 PM
 * @time 1:15 PM
 * @since 1.0.0
 **/
@Api(tags = "设备服务")
@Controller
@RequestMapping(value = "/business/api")
public class DeviceController {
    @Autowired
    private DeviceServiceImpl deviceService;

    @GetMapping("/findDeviceByrfId")
    @ApiOperation(value = "RFID查询设备")
    public JsonResult findDeviceByrfId(String rfid) {
        BizDevice device = deviceService.findDeviceByrfId(rfid);
        return success("查询成功", device);
    }
    @GetMapping("/findDeviceByCondition")
    @ApiOperation(value = "工具类型模糊查询(工具编号、设备名称、物料号)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "查询条件(工具编号、设备名称、物料号)", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "currentPage", value = "当前页", dataType = "int", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "limit", value = "条数", dataType = "int", paramType = "query", defaultValue = "10")
    })
    public JsonResult findDeviceByCondition(String condition){
        return pageList("查询成功",deviceService.findDeviceByCondition(condition));
    }
    @PostMapping("/bindDevice")
    @ApiOperation(value = "绑定设备")
    public JsonResult bindDevice(@RequestBody DeviceBindDTO bindDTO) {
        return deviceService.bindDevice(bindDTO)>0?success("操作成功"):fail("录入失败！");
    }
    @PostMapping("/saveDevice")
    @ApiOperation(value = "保存设备")
    public JsonResult save(@RequestBody BizDevice device){
        return success("保存成功",deviceService.save(device));
    }
    @PostMapping("/updateDevice")
    @ApiOperation(value = "更新工具信息")
    public JsonResult update(@RequestBody BizDevice record){
        return deviceService.update(record)>0?success("修改成功"):fail("修改失败");
    }
    @PostMapping("/updateDeviceStore")
    @ApiOperation(value = "更新工具储存信息")
    public JsonResult updateDeviceStore(@RequestBody BizDeviceStore store){
        return deviceService.updateDeviceStore(store)>0?success("修改成功"):fail("修改失败");
    }
    @GetMapping("/findDeviceStoreByRfid")
    @ApiOperation(value = "通过rfid查询工具储存信息")
    public JsonResult findDeviceStoreByRfid(String rfid){
        return success("查询成功",deviceService.findDeviceStoreByRfid(rfid));
    }
    @GetMapping("/findCheckDevice")
    @ApiOperation(value = "查询需检定工具清单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页", dataType = "int", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "limit", value = "条数", dataType = "int", paramType = "query", defaultValue = "10")
    })
    public JsonResult findCheckDevice(String condition){
        return pageList("查询成功",deviceService.findCheckDevice());
    }
    @GetMapping("/findDeviceStore")
    @ApiOperation(value = "查询录入工具信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "condition", value = "查询条件(工具编号、rfid)", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "currentPage", value = "当前页", dataType = "int", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "limit", value = "条数", dataType = "int", paramType = "query", defaultValue = "10")
    })
    public JsonResult findDeviceStore(String condition){
        return pageList("查询成功",deviceService.findDeviceStore(condition));
    }
    @PostMapping("/lend")
    @ApiOperation(value = "设备借出")
    public JsonResult lend(@RequestBody LendRecordDTO record) {
        return success("操作成功", deviceService.lend(record));
    }
    @PostMapping("/back")
    @ApiOperation(value = "设备归还")
    public JsonResult back(@RequestBody LendRecordDTO record) {
        return success("操作成功", deviceService.back(record));
    }
    @GetMapping("/findLendDeviceList")
    @ApiOperation(value = "借用列表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "当前用户Id", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "deviceCode", value = "设备编号", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "deviceName", value = "设备名称", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "rfid", value = "rfid标签", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "currentPage", value = "当前页", dataType = "int", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "limit", value = "条数", dataType = "int", paramType = "query", defaultValue = "10")
    })
    public JsonResult findDeviceLendList() {
        BizDeviceLendRecord record = RequestUtils.convertObj(BizDeviceLendRecord.class);
        return pageList("操作成功", deviceService.findDeviceLendList(record));
    }
    @GetMapping("/findBackDeviceList")
    @ApiOperation(value = "归还列表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "当前用户", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "deviceCode", value = "设备编号", dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "currentPage", value = "当前页", dataType = "int", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "limit", value = "条数", dataType = "int", paramType = "query", defaultValue = "10")
    })
    public JsonResult findDeviceBackList(){
        BizDeviceBackRecord record = RequestUtils.convertObj(BizDeviceBackRecord.class);
        return pageList("操作成功", deviceService.findDeviceBackList(record));
    }
}
