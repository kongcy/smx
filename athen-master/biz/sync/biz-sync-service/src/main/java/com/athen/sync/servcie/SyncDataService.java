package com.athen.sync.servcie;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author chenying
 * @date 2022/11/7 4:28 PM
 * @time 4:28 PM
 * @since 1.0.0
 **/
public interface SyncDataService {
    /**
     * 从本地同步到服务端
     * */
    int localSyncServer(JdbcTemplate primaryJdbcTemplate,JdbcTemplate secondaryJdbcTemplate);


    int serverSyncLocal(JdbcTemplate primaryJdbcTemplate,JdbcTemplate secondaryJdbcTemplate);

}
