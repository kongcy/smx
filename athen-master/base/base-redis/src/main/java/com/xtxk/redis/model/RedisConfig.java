package com.xtxk.redis.model;

import com.xtxk.core.json.JsonUtil;
import lombok.Data;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

/*** redis配置 **/
@Data
public class RedisConfig extends RedisProperties {

    /**
     * 判断运行环境是线上还是线下
     ***/
    private boolean online;

    public RedisConfig(boolean online) {
        super();
        this.online = online;
    }

    public Integer getMaxTotal() {
        Jedis jedis = this.getJedis();
        if (jedis != null) {
            Pool pool = jedis.getPool();
            if (pool != null) {
                return pool.getMaxActive();
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
