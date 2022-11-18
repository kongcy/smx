package com.athen.business.service;

import cn.hutool.log.Log;
import com.athen.business.api.dto.DeviceBindDTO;
import com.athen.business.api.dto.LendRecordDTO;
import com.athen.business.api.model.*;
import com.athen.business.api.service.DeviceService;
import com.athen.business.repository.*;
import com.github.pagehelper.Page;
import com.xtxk.core.date.DateFormatType;
import com.xtxk.core.date.DateUtil;
import com.xtxk.core.exception.Assert;
import com.xtxk.core.util.A;
import com.xtxk.core.util.LogUtil;
import com.xtxk.core.util.U;
import com.xtxk.core.vo.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import static com.xtxk.core.vo.PageList.toPage;

/**
 * @author chenying
 * @date 2022/10/17 2:23 PM
 * @time 2:23 PM
 * @since 1.0.0
 **/
@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private BizDeviceMapper deviceMapper;
    @Autowired
    private BizDeviceLendRecordMapper lendRecordMapper;
    @Autowired
    private BizDeviceBackRecordMapper backRecordMapper;
    @Autowired
    private BizDeviceStoreMapper storeMapper;
    @Autowired
    private BizDeviceHistoryLendRecordMapper historyLendRecordMapper;
    @Override
    public BizDevice findDeviceByrfId(String rfid) {
        Assert.checkNull(rfid,"RFID标签不能为空！");
        List<BizDevice> ds = deviceMapper.findDeviceByrfId(rfid);
        return A.first(ds);
    }

    @Override
    public PageList findDeviceByCondition(String condition) {
        Page<BizDevice> page = (Page<BizDevice>) deviceMapper.findDeviceByCondition(condition);
        return toPage(page.getTotal(), page.getPageNum(), page.getResult());
    }

    /**
     * 分页查询录入设备信息
     *
     * @param condition
     */
    @Override
    public PageList findDeviceStore(String condition) {
        Page<BizDevice> page = (Page<BizDevice>) deviceMapper.findDeviceStore(condition);
        return toPage(page.getTotal(), page.getPageNum(), page.getResult());
    }

    @Override
    @Transactional
    public int bindDevice(DeviceBindDTO device) {
        Assert.checkNull(device,"设备信息不能为空!");
        Assert.checkNull(device.getRfid(),"RFID不能为空！");
        Assert.checkNull(device.getDeviceCode(),"工具编号不能为空");
        BizDevice record = A.first(deviceMapper.findDeviceByCode(device.getDeviceCode()));
        Assert.checkIsTrue(U.isBlank(record),"系统找不到该工具数据！");
        BizDeviceStore s = storeMapper.selectByPrimaryKey(device.getRfid());
        Assert.checkNoNull(s,"该设备已经录入，请勿重复录入！");
        BizDeviceStore store = new BizDeviceStore();
        store.setRfid(device.getRfid());
        store.setDeviceCode(device.getDeviceCode());
        store.setHj(device.getHj());
        store.setFj(device.getFj());
        store.setCs(device.getCs());
        store.setCf(device.getCf());
        store.setXh(device.getXh());
        store.setCheckEndTime(device.getCheckEndTime());
        store.setCheckBeginTime(DateUtil.addDays(store.getCheckEndTime(),-30));
        store.setCheckTime(record.getCheckTime());
        int count=storeMapper.insert(store);
        return count;
    }

    @Override
    @Transactional
    public int save(BizDevice device) {
        Assert.checkNull(device,"设备信息不能为空!");
        Assert.checkNull(device.getRfid(),"RFID不能为空！");
        Assert.checkNull(device.getDeviceCode(),"工具编号不能为空");
        Assert.checkNull(device.getDeviceName(),"工具名称不能为空！");
        BizDevice d = A.first(deviceMapper.findDeviceByCode(device.getDeviceCode()));
        Assert.checkNoNull(d,"工具名称已经存在，请勿重复提交！");
        device.setId(U.uuid());
        device.setCreateTime(new Date());
        device.setUpdateTime(new Date());
        Date checkEndTime = device.getCheckEndTime();
        Date checkBeginTime = DateUtil.addDays(checkEndTime,-device.getCheckTime());
        int count = deviceMapper.insert(device);
        if(count>0){
           if(U.isNotBlank(device.getCf())){
               BizDeviceStore.BizDeviceStoreBuilder builder = BizDeviceStore.builder();
               builder.deviceCode(device.getDeviceCode())
                       .cf(device.getCf())
                       .hj(device.getHj())
                       .fj(device.getFj())
                       .xh(device.getXh())
                       .cs(device.getCs())
                       .rfid(device.getRfid())
                       .checkTime(device.getCheckTime())
                       .checkBeginTime(checkBeginTime)
                       .checkEndTime(checkEndTime);
               storeMapper.insert(builder.build());
           }
        }
        return count;
    }

    /**
     * 修改设备
     *
     * @param record
     */
    @Transactional
    @Override
    public int update(BizDevice record) {
        Assert.checkNull(record,"工具信息不能为空!");
        Assert.checkNull(record.getId(),"工具ID不能为空！");
        BizDevice device = deviceMapper.selectByPrimaryKey(record.getId());
        Assert.checkNull(device,"系统无法找到该工具信息！");
        record.setDeviceCode(null);
        int count = deviceMapper.updateByPrimaryKeySelective(record);
        if(count>0){
           if(U.isNotBlank(record.getCheckTime())){
               List<BizDeviceStore> storeList = storeMapper.findDeviceStoreByCode(record.getDeviceCode());
               if(A.isNotEmpty(storeList)){
                   for(BizDeviceStore store:storeList){
                       Date beginTime = DateUtil.addDays(store.getCheckEndTime(),-record.getCheckTime());
                       store.setCheckTime(record.getCheckTime());
                       store.setCheckBeginTime(beginTime);
                       storeMapper.insertSelective(store);
                   }
               }
           }
        }
        return count;
    }

    /**
     * 修改工具存储信息
     *
     * @param store
     */
    @Override
    public int updateDeviceStore(BizDeviceStore store) {
        Assert.checkNull(store,"工具储存信息不能为空！");
        Assert.checkNull(store.getRfid(),"RFID不能为空！");
        Assert.checkNull(findDeviceStoreByRfid(store.getRfid()),"系统找不到该条工具存储信息！");
        store.setDeviceCode(null);
        Date checkBeginTime = DateUtil.addDays(store.getCheckEndTime(),-store.getCheckTime());
        store.setCheckBeginTime(checkBeginTime);
        return storeMapper.updateByPrimaryKeySelective(store);
    }

    /**
     * 查找设备储存信息
     *
     * @param rfid
     */
    @Override
    public BizDeviceStore findDeviceStoreByRfid(String rfid) {
        Assert.checkNull(rfid,"RFID不能为空!");
        return storeMapper.selectByPrimaryKey(rfid);
    }

    @Override
    @Transactional
    public int lend(LendRecordDTO record) {
        Assert.checkNull(record,"设备信息不能为空！");
        Assert.checkNull(record.getDeviceCode(),"工具编号不能为空");
        Assert.checkNull(record.getRfid(),"RFID不能为空");
        BizDevice device = A.first(deviceMapper.findDeviceByCode(record.getDeviceCode()));
        Assert.checkIsTrue(U.isBlank(device),"该设备不存在！");
        BizDeviceStore store = storeMapper.selectByPrimaryKey(record.getRfid());
        Assert.checkIsTrue(U.isBlank(store),"该设备存储信息不存在！");
      //  Assert.checkIsTrue(!verifyTime(store.getCheckEndTime()),"该设备超出或即将超出有效期，不能借出！");
        List<BizDeviceLendRecord> records = lendRecordMapper.findLendRecordByDeviceCodeAndUser(record.getRfid(),record.getCurrentUser());
        Assert.checkIsTrue(A.isNotEmpty(records),"你已经借用了该设备！");
        BizDeviceLendRecord lendRecord = new BizDeviceLendRecord();
        lendRecord.setId(U.uuid());
        lendRecord.setLendCount(1);
        lendRecord.setRfid(record.getRfid());
        lendRecord.setDeviceCode(record.getDeviceCode());
        lendRecord.setBeginTime(new Date());
        lendRecord.setLendUser(record.getCurrentUser());
        lendRecord.setLendDepart(record.getRfid());
        lendRecord.setCreateUser(record.getCurrentUser());
        lendRecord.setDeviceName(device.getDeviceName());
        int count = lendRecordMapper.insert(lendRecord);
        if(count>0){
            BizDeviceHistoryLendRecord historyLendRecord = BizDeviceHistoryLendRecord.builder()
                    .id(U.uuid()).lendCount(1).
                    lendDepart(record.getRfid())
                    .lendUser(record.getCurrentUser())
                    .deviceCode(record.getDeviceCode())
                    .deviceName(device.getDeviceName())
                    .beginTime(lendRecord.getBeginTime())
                    .state(0)
                    .createUser(record.getCurrentUser()).build();
            historyLendRecordMapper.insert(historyLendRecord);
          //  backRecordMapper.deleteBackRecordByRfidAndUserId(record.getRfid(),record.getCurrentUser());
        }
        return count;
    }

    /***
     * 验证有效期
     * @param checkEndTime
     */
    @Override
    public boolean verifyTime(Date checkEndTime,Integer checkTime,Integer checkTime_) {
        LogUtil.LOG.info("========================verifyTime:====================="+checkEndTime+"-"+checkTime+"-"+checkTime_);
        if(U.isBlank(checkEndTime)||U.isBlank(checkTime)||U.isBlank(checkTime_)){
            return true;
        }
        Date d = DateUtil.addDays(checkEndTime,-30);
        Date cDate = DateUtil.parse(DateUtil.formatDate(new Date()),DateFormatType.YYYY_MM_DD);
        String time = "2022-11-17";
        Date checkTime_2 =DateUtil.parse(time,DateFormatType.YYYY_MM_DD);
        if(cDate.before(d)){
            return true;
        }
        return false;
    }

    public static void main(String args[]){
        Date cDate = DateUtil.parse(DateUtil.formatDate(new Date()),DateFormatType.YYYY_MM_DD);
        System.out.println(cDate);
        String time = "2022-11-16";
        Date checkTime_2 =DateUtil.parse(time,DateFormatType.YYYY_MM_DD);
        if(cDate.before(checkTime_2)){
           System.out.println("---------");
        }
        if(cDate.before(checkTime_2)){
            System.out.println("---------");
        }


    }

    /**
     * 查询需检定工具清单
     */
    @Override
    public List<BizDevice> findCheckDevice() {
        return deviceMapper.findCheckDevice();
    }

    @Override
    @Transactional
    public int back(LendRecordDTO record) {
        Assert.checkNull(record,"设备信息不能为空！");
        Assert.checkNull(record.getDeviceCode(),"工具编号不能为空");
        Assert.checkNull(record.getRfid(),"RFID不能为空");
        BizDevice device = A.first(deviceMapper.findDeviceByCode(record.getDeviceCode()));
        Assert.checkIsTrue(U.isBlank(device),"该设备不存在！");
        List<BizDeviceLendRecord> records = lendRecordMapper.findLendRecordByDeviceCodeAndUser(record.getRfid(),record.getCurrentUser());
    //    List<BizDeviceBackRecord>  backRecords = backRecordMapper.findBackRecordByRfidAndUserId(record.getRfid(),record.getCurrentUser());
        Assert.checkIsTrue(A.isEmpty(records),"你没有借用该设备！");
        BizDeviceBackRecord backRecord = new BizDeviceBackRecord();
        backRecord.setId(U.uuid());
        backRecord.setRfid(record.getRfid());
        backRecord.setDeviceCode(record.getDeviceCode());
        backRecord.setBackTime(new Date());
        backRecord.setBackCount(1);
        backRecord.setUserId(record.getCurrentUser());
        backRecord.setBackUser(record.getCurrentUser());
        backRecord.setDeviceName(device.getDeviceName());
        backRecord.setOperator(record.getCurrentUser());
        int count = backRecordMapper.insert(backRecord);
        if(count>0){
           lendRecordMapper.deleteLendRecordByRfidAndUserId(record.getRfid(),record.getCurrentUser());
           BizDeviceHistoryLendRecord historyLendRecord = BizDeviceHistoryLendRecord.builder()
                   .state(1).lendUser(record.getCurrentUser())
                   .lendDepart(record.getRfid()).endTime(new Date()).build();
           historyLendRecordMapper.updateByRfidAndUserId(historyLendRecord);
        }
        return count;
    }

    @Override
    public PageList findDeviceLendList(BizDeviceLendRecord record) {
        record.setLendUser(U.isBlank(record.getUserId())?null:record.getUserId());
        record.setLendDepart(U.isBlank(record.getRfid())?null:record.getRfid());
        record.setDeviceName(U.isBlank(record.getDeviceName())?null: record.getDeviceName());
        record.setDeviceCode(U.isBlank(record.getDeviceCode())?null: record.getDeviceCode());
        Page<BizDeviceLendRecord> page = (Page<BizDeviceLendRecord>) lendRecordMapper.findPageLendRecord(record);
        return toPage(page.getTotal(), page.getPageNum(), page.getResult());
    }

    @Override
    public PageList findDeviceBackList(BizDeviceBackRecord record) {
        BizDeviceBackRecordExample example = null;
        if(record!=null){
            example = new BizDeviceBackRecordExample();
            BizDeviceBackRecordExample.Criteria criteria = example.or();
            if(U.isNotBlank(record.getUserId())){
                criteria.andBackUserEqualTo(record.getUserId());
            }
            if(U.isNotBlank(record.getDeviceCode())){
                criteria.andDeviceCodeLike(record.getDeviceCode());
            }
            if(U.isNotBlank(record.getDeviceName())){
                criteria.andDeviceNameLike(U.like(record.getDeviceName()));
            }
        }
        Page<BizDeviceBackRecord> page = (Page<BizDeviceBackRecord>) backRecordMapper.selectByExample(example);
        return toPage(page.getTotal(), page.getPageNum(), page.getResult());
    }

    @Override
    public int saveHistoryLendRecord(BizDeviceHistoryLendRecord record) {
        return 0;
    }

    /**
     * 更新设备状态
     *
     * @param record
     */
    @Override
    public int updateDeviceState(BizDevice record) {
        return 0;
    }
}
