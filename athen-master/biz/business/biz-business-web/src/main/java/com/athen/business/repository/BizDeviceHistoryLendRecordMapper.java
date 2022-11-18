package com.athen.business.repository;

import com.athen.business.api.model.BizDeviceHistoryLendRecord;
import com.athen.business.api.model.BizDeviceHistoryLendRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BizDeviceHistoryLendRecordMapper {
    long countByExample(BizDeviceHistoryLendRecordExample example);

    int deleteByExample(BizDeviceHistoryLendRecordExample example);

    int deleteByPrimaryKey(String id);

    int insert(BizDeviceHistoryLendRecord record);

    int insertSelective(BizDeviceHistoryLendRecord record);

    List<BizDeviceHistoryLendRecord> selectByExample(BizDeviceHistoryLendRecordExample example);

    BizDeviceHistoryLendRecord selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BizDeviceHistoryLendRecord record, @Param("example") BizDeviceHistoryLendRecordExample example);

    int updateByExample(@Param("record") BizDeviceHistoryLendRecord record, @Param("example") BizDeviceHistoryLendRecordExample example);

    int updateByPrimaryKeySelective(BizDeviceHistoryLendRecord record);

    int updateByPrimaryKey(BizDeviceHistoryLendRecord record);

    int updateByRfidAndUserId(@Param("record") BizDeviceHistoryLendRecord record);
}