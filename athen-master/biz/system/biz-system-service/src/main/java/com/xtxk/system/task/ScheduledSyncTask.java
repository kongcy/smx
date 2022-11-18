package com.xtxk.system.task;

import com.xtxk.core.date.DateUtil;
import com.xtxk.core.util.LogUtil;
import com.xtxk.system.api.service.SysFaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author chenying
 * @date 2021-09-16 12:38
 * @time 12:38
 * @since 1.0.0
 **/
@Component
public class ScheduledSyncTask {

    @Autowired
    private SysFaceService  sysFaceService;

    @Scheduled(cron = "0 0/2 * * * ? ")
    public void updateUserImage() {
        LogUtil.LOG.info("========================每隔2分钟开始同步执行一次："+ DateUtil.formatFull(new Date()) +"========================");
        int count=sysFaceService.updateUserImage();
        LogUtil.LOG.info("========================已经同步人数："+count+"========================");
    }

}
