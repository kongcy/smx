/**
 * Copyright © 2018 organization baomidou
 * <pre>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <pre/>
 */
package com.xtxk.dataSource.model;

/**
 * Druid监控配置
 *
 * @author TaoYu
 */
public class DruidStatConfig {

    private Long slowSqlMillis;

    private Boolean logSlowSql;

    private Boolean mergeSql;

    public Long getSlowSqlMillis() {
        return slowSqlMillis;
    }

    public void setSlowSqlMillis(Long slowSqlMillis) {
        this.slowSqlMillis = slowSqlMillis;
    }

    public Boolean getLogSlowSql() {
        return logSlowSql;
    }

    public void setLogSlowSql(Boolean logSlowSql) {
        this.logSlowSql = logSlowSql;
    }

    public Boolean getMergeSql() {
        return mergeSql;
    }

    public void setMergeSql(Boolean mergeSql) {
        this.mergeSql = mergeSql;
    }
}