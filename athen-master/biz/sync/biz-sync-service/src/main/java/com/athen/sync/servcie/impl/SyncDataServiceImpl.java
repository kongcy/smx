package com.athen.sync.servcie.impl;

import com.athen.sync.servcie.SyncDataService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author chenying
 * @date 2022/11/7 4:41 PM
 * @time 4:41 PM
 * @since 1.0.0
 **/
@Service
public class SyncDataServiceImpl implements SyncDataService {
    /**
     * 从本地同步到服务端
     *
     * @param primaryJdbcTemplate
     * @param secondaryJdbcTemplate
     */
    @Override
    public int localSyncServer(JdbcTemplate primaryJdbcTemplate, JdbcTemplate secondaryJdbcTemplate) {
        return 0;
    }

    @Override
    public int serverSyncLocal(JdbcTemplate primaryJdbcTemplate, JdbcTemplate secondaryJdbcTemplate) {
        return 0;
    }
}
