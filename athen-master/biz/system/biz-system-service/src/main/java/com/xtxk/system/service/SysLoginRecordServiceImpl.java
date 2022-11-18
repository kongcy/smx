package com.xtxk.system.service;

import com.github.pagehelper.Page;
import com.xtxk.core.date.DateUtil;
import com.xtxk.core.util.LogUtil;
import com.xtxk.core.util.U;
import com.xtxk.core.vo.PageList;
import com.xtxk.system.api.model.SysLoginRecord;
import com.xtxk.system.api.model.SysLoginRecordExample;
import com.xtxk.system.api.service.SysLoginRecordService;
import com.xtxk.system.repository.SysLoginRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
import static com.xtxk.core.vo.PageList.toPage;

/**
 * @Description
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/5/25
 */
@RestController
public class SysLoginRecordServiceImpl implements SysLoginRecordService {
    @Autowired
    private SysLoginRecordMapper loginRecordMapper;

    @Override
    public PageList findPageRecordByCondition(@RequestBody SysLoginRecord record) {
        SysLoginRecordExample example = null;
        if (record != null) {
            example = new SysLoginRecordExample();
            SysLoginRecordExample.Criteria criteria = example.createCriteria();
            if (U.isNotBlank(record.getUserId())) {
                criteria.andUserIdLike(U.like(record.getUserId()));
            }
            if (U.isNotBlank(record.getLoginIp())) {
                criteria.andLoginIpEqualTo(record.getLoginIp());
            }
            if (U.isNotBlank(record.getStatus())) {
                criteria.andStatusEqualTo(record.getStatus());
            }
            if (U.isNotBlank(record.getLoginSource())) {
                criteria.andLoginSourceEqualTo(record.getLoginSource());
            }
            if (U.isNotBlank(record.getBeginTime()) && U.isBlank(record.getEndTime())) {
                Date endTime = DateUtil.addMinute(record.getBeginTime(), -2);
                criteria.andBeginTimeBetween(endTime, record.getBeginTime());
            }
            if (U.isBlank(record.getBeginTime()) && U.isBlank(record.getEndTime())) {
                Date beginTime = new Date();
                Date endTime = DateUtil.addMinute(beginTime, -2);
                criteria.andBeginTimeBetween(endTime, beginTime);
            }
            example.setOrderByClause("BEGIN_TIME DESC");
        }
        Page<SysLoginRecord> page = (Page<SysLoginRecord>) loginRecordMapper.selectByExample(example);
        return toPage(page.getTotal(), page.getPageNum(), page.getResult());
    }

    /**
     * 保存记录
     *
     * @param record
     */
    @Override
    public boolean saveRecord(@RequestBody SysLoginRecord record) {
        if(null==record||U.isBlank(record.getUserId())){
            LogUtil.ROOT_LOG.warn("======================用户登录账号【loginName】为空，锁定账号失败！");
            return false;
        }
        if (loginRecordMapper.insertSelective(record) > 0) {
            return true;
        }
        return false;
    }
}
