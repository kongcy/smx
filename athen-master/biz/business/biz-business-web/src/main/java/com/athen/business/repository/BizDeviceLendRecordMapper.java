package com.athen.business.repository;

import com.athen.business.api.model.BizDeviceLendRecord;
import com.athen.business.api.model.BizDeviceLendRecordExample;
import java.util.List;

import com.xtxk.core.annotation.Page;
import org.apache.ibatis.annotations.Param;

public interface BizDeviceLendRecordMapper {
    long countByExample(BizDeviceLendRecordExample example);

    int deleteByExample(BizDeviceLendRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(BizDeviceLendRecord record);

    int insertSelective(BizDeviceLendRecord record);

    @Page
    List<BizDeviceLendRecord> selectByExample(BizDeviceLendRecordExample example);

    BizDeviceLendRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BizDeviceLendRecord record, @Param("example") BizDeviceLendRecordExample example);

    int updateByExample(@Param("record") BizDeviceLendRecord record, @Param("example") BizDeviceLendRecordExample example);

    int updateByPrimaryKeySelective(BizDeviceLendRecord record);

    int updateByPrimaryKey(BizDeviceLendRecord record);

    List<BizDeviceLendRecord> findLendRecordByDeviceCodeAndUser(@Param("deviceCode") String deviceCode ,@Param("userId") String userId);

    int deleteLendRecordByRfidAndUserId(@Param("rfid") String rfid ,@Param("userId") String userId);
    @Page
    List<BizDeviceLendRecord> findPageLendRecord(BizDeviceLendRecord record);
}