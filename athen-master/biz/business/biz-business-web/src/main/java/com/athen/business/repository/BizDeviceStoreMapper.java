package com.athen.business.repository;

import com.athen.business.api.model.BizDeviceStore;
import com.athen.business.api.model.BizDeviceStoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BizDeviceStoreMapper {
    long countByExample(BizDeviceStoreExample example);

    int deleteByExample(BizDeviceStoreExample example);

    int deleteByPrimaryKey(String rfid);

    int insert(BizDeviceStore record);

    int insertSelective(BizDeviceStore record);

    List<BizDeviceStore> selectByExample(BizDeviceStoreExample example);

    BizDeviceStore selectByPrimaryKey(String rfid);

    int updateByExampleSelective(@Param("record") BizDeviceStore record, @Param("example") BizDeviceStoreExample example);

    int updateByExample(@Param("record") BizDeviceStore record, @Param("example") BizDeviceStoreExample example);

    int updateByPrimaryKeySelective(BizDeviceStore record);

    int updateByPrimaryKey(BizDeviceStore record);

    List<BizDeviceStore> findDeviceStoreByCode(@Param("deviceCode") String deviceCode);
}