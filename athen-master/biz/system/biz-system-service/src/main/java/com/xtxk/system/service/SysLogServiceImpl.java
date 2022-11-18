package com.xtxk.system.service;

import com.github.pagehelper.Page;
import com.xtxk.core.util.LogUtil;
import com.xtxk.core.util.RequestUtils;
import com.xtxk.core.util.U;
import com.xtxk.core.vo.PageList;
import com.xtxk.system.api.model.SysLog;
import com.xtxk.system.api.service.SysLogService;
import com.xtxk.system.repository.SysLogMapper;
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
 * @Date 2022/5/17
 */
@RestController
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogMapper logMapper;

    /**
     * 保存日志
     *
     * @param sysLog
     */
    @Override
    public boolean saveLog(@RequestBody SysLog sysLog) {
        try {
            if (U.isBlank(sysLog.getUserId())) {
                sysLog.setUserId("admin");
            }
            sysLog.setLogId(U.uuid());
            sysLog.setCreateTime(new Date());
            sysLog.setLoginSource(RequestUtils.OsType.PC.name());
            if (logMapper.insertSelective(sysLog) > 0) {
                return true;
            }
        } catch (Exception e) {
            LogUtil.LOG.error("登录日志保存失败！");
        }
        return false;
    }

    @Override
    public boolean saveLog(String userId, String ip) {
        SysLog.SysLogBuilder builder = SysLog.builder();
        builder
                .logId(U.uuid())
                .loginIp(ip)
                .loginSource(RequestUtils.OsType.PC.name())
                .userId(U.isBlank(userId) ? "admin" : userId);
        SysLog sysLog = builder.build();
        if (logMapper.insertSelective(sysLog) > 0) {
            return true;
        }
        return false;
    }

    @Override
    public PageList findLogs(@RequestBody SysLog sysLog) {
        Page<SysLog> page = (Page<SysLog>) logMapper.selectByExample(null);
        return toPage(page.getTotal(), page.getPageNum(), page.getResult());
    }
}
