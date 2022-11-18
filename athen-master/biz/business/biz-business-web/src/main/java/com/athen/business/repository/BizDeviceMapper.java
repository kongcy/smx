package com.athen.business.repository;

import com.athen.business.api.model.BizDevice;
import com.athen.business.api.model.BizDeviceExample;
import java.util.List;

import com.xtxk.core.annotation.Page;
import org.apache.ibatis.annotations.Param;

public interface BizDeviceMapper {
    long countByExample(BizDeviceExample example);

    int deleteByExample(BizDeviceExample example);

    int deleteByPrimaryKey(String id);

    int insert(BizDevice record);

    int insertSelective(BizDevice record);

    List<BizDevice> selectByExample(BizDeviceExample example);

    BizDevice selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BizDevice record, @Param("example") BizDeviceExample example);

    int updateByExample(@Param("record") BizDevice record, @Param("example") BizDeviceExample example);

    int updateByPrimaryKeySelective(BizDevice record);

    int updateByPrimaryKey(BizDevice record);

    List<BizDevice> findDeviceByrfId(@Param("rfid")String rfid);
    @Page
    List<BizDevice> findDeviceByCondition(@Param("condition")String condition);

    List<BizDevice> findDeviceByCode(@Param("deviceCode")String deviceCode);

    @Page
    List<BizDevice> findCheckDevice();
    @Page
    List<BizDevice> findDeviceStore(@Param("condition")String condition);
}