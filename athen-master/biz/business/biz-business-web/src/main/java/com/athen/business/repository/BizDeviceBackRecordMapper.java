package com.athen.business.repository;

import com.athen.business.api.model.BizDeviceBackRecord;
import com.athen.business.api.model.BizDeviceBackRecordExample;
import java.util.List;

import com.xtxk.core.annotation.Page;
import org.apache.ibatis.annotations.Param;

public interface BizDeviceBackRecordMapper {
    long countByExample(BizDeviceBackRecordExample example);

    int deleteByExample(BizDeviceBackRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(BizDeviceBackRecord record);

    int insertSelective(BizDeviceBackRecord record);
    @Page
    List<BizDeviceBackRecord> selectByExample(BizDeviceBackRecordExample example);

    BizDeviceBackRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BizDeviceBackRecord record, @Param("example") BizDeviceBackRecordExample example);

    int updateByExample(@Param("record") BizDeviceBackRecord record, @Param("example") BizDeviceBackRecordExample example);

    int updateByPrimaryKeySelective(BizDeviceBackRecord record);

    int updateByPrimaryKey(BizDeviceBackRecord record);

    List<BizDeviceBackRecord> findBackRecordByRfidAndUserId(@Param("rfid")  String rfid,@Param("userId")String userId);

    int deleteBackRecordByRfidAndUserId(@Param("rfid") String rfid ,@Param("userId") String userId);
}