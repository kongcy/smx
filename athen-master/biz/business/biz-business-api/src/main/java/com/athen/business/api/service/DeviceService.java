package com.athen.business.api.service;

import com.athen.business.api.dto.DeviceBindDTO;
import com.athen.business.api.dto.LendRecordDTO;
import com.athen.business.api.model.*;
import com.xtxk.core.vo.PageList;

import java.util.Date;
import java.util.List;

/**
 * @author chenying
 * @date 2022/10/17 1:17 PM
 * @time 1:17 PM
 * @since 1.0.0
 **/
public interface DeviceService {
    /**
     * 通过rfid查询工具信息
     *
     * @param rfid 标签
     */
    BizDevice findDeviceByrfId(String rfid);

    /**
     * 分页查询设备信息
     *
     * @param condition 工具编号、名称
     */
    PageList findDeviceByCondition(String condition);

    /**
     * 分页查询录入设备信息
     * */
    PageList findDeviceStore(String condition);

    /**
     * 绑定工具
     */
    int bindDevice(DeviceBindDTO device);

    /**
     * 保存并绑定工具
     */
    int save(BizDevice device);

    /**
     * 修改设备
     * */
    int update(BizDevice record);

    /**
     * 修改工具存储信息
     * */
    int updateDeviceStore(BizDeviceStore store);

    /**
     * 查找设备储存信息
     * */
    BizDeviceStore findDeviceStoreByRfid(String rfid);

    /**
     * 借用工具
     */
    int lend(LendRecordDTO record);

    /***
     * 验证有效期
     */
    boolean  verifyTime(Date checkEndTime,Integer checkTime,Integer checkTime_);

    /**
     * 查询需检定工具清单
     * */
    List<BizDevice> findCheckDevice();

    /**
     * 归还工具
     */
    int back(LendRecordDTO record);

    /**
     * 查询实时借用列表
     */
    PageList findDeviceLendList(BizDeviceLendRecord record);

    /**
     * 查询归还记录
     */
    PageList findDeviceBackList(BizDeviceBackRecord record);

    /**
     * 保存历史借用记录
     */
    int saveHistoryLendRecord(BizDeviceHistoryLendRecord record);

    /**
     * 更新设备状态
     */
    int updateDeviceState(BizDevice record);

}
