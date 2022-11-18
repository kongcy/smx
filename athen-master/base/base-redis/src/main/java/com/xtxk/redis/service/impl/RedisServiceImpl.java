package com.xtxk.redis.service.impl;

import com.xtxk.redis.service.IRedisService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.core.ConvertingCursor;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by foresee on 2019-03-06.
 */
public class RedisServiceImpl implements IRedisService {
    private static final Logger logger = LogManager.getLogger();

    private RedisTemplate<String, Object> redisTemplate;

    public RedisServiceImpl() {
    }

    public RedisServiceImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 设置字符串类型缓存
     *
     * @param key
     * @param value
     */
    @Override
    public void setString(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置字符串类型缓存并设置过期时间
     *
     * @param key
     * @param value
     * @param expire 过期时间，单位：秒
     */
    @Override
    public void setString(String key, String value, TimeUnit expire) {
        redisTemplate.opsForValue().set(key, value, 3000, expire);
    }

    /**
     * 设置对象类型缓存
     *
     * @param key
     * @param value
     */
    @Override
    public void setObject(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置对象类型缓存并设置过期时间
     *
     * @param key
     * @param value
     * @param expire
     */
    @Override
    public void setObject(String key, Object value, TimeUnit expire) {
        redisTemplate.opsForValue().set(key, value, 3000, expire);
    }

    /**
     * 方法重载
     * {@link #setObject(String, java.io.Serializable, int)}
     *
     * @param key
     * @param value
     * @param expire
     * @param cover  false：有则不覆盖，无则添加; true:无论有无均覆盖
     */
    @Override
    public void setObject(String key, Object value, TimeUnit expire, boolean cover) {

    }

    /**
     * 读取字符串类型缓存
     *
     * @param key
     * @return
     */
    @Override
    public String getString(String key) {
        return redisTemplate.opsForValue().get(key).toString();
    }

    /**
     * 读取字符串类型缓存并设置过期时间
     *
     * @param key
     * @param expire
     * @return
     */
    @Override
    public String getString(String key, TimeUnit expire) {
        expire(key, expire);
        return getString(key);
    }

    /**
     * 读取对象类型缓存
     *
     * @param key
     * @return
     */
    @Override
    public Object getObject(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 读取对象型缓存并设置过期时间
     *
     * @param key
     * @param expire
     * @return
     */
    @Override
    public Object getObject(String key, TimeUnit expire) {
        return null;
    }

    /**
     * 根据key删除缓存内容
     *
     * @param key
     */
    @Override
    public void removeObject(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 设置缓存过期时间
     *
     * @param key
     * @param expire
     */
    @Override
    public void expire(String key, TimeUnit expire) {
        redisTemplate.expire(key, 3000, expire);
    }

    /**
     * 返回当前选定数据库中的key数量
     *
     * @return
     */
    @Override
    public long getRedisSize() {
        return redisTemplate.keys("*").size();
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    @Override
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 使用哈希结构存储设置对象类型缓存并设置过期时间
     *
     * @param key
     * @param field
     * @param value
     * @param expire
     */
    @Override
    public boolean setHashObject(String key, String field, Object value, int expire) {
        try {
            redisTemplate.opsForHash().put(key, field, value);
            if (expire > 0) {
                expire(key, expire);
            }
        } catch (Exception e) {
            logger.error("redis缓存失败！" + e.getMessage(), e);
            return false;
        }
        return true;
    }

    /**
     * 使用哈希结构存储设置Map缓存并设置过期时间
     *
     * @param key
     * @param values
     * @param expire
     */
    @Override
    public <T> boolean setHashObject(String key, Map<String, T> values, int expire) {
        try {
            redisTemplate.opsForHash().putAll(key, values);
            expire(key, expire);
        } catch (Exception e) {
            logger.error("redis缓存失败！" + e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * 使用哈希结构存储设置Map缓存并设置过期时间（适用大数据量存储）
     *
     * @param key
     * @param values
     * @param expire
     */
    @Override
    public void setHashObjectWithPiple(String key, Map<String, Object> values, int expire) {

    }

    /**
     * 读取哈希结构中某个field的内容
     *
     * @param key
     * @param field
     * @return
     */
    @Override
    public Object getHashObject(String key, String field) {
        try {
            return redisTemplate.opsForHash().get(key, field);
        } catch (Exception e) {
            logger.error("=========================》从redis中获取key： {}对应的值 ，错误信息： {} ", key, e.getMessage());
        }
        return null;
    }

    /**
     * 获取hash key值的数量
     *
     * @param key
     */
    @Override
    public long getHashSize(String key) {
        try {
            return redisTemplate.opsForHash().size(key);
        } catch (Exception e) {
            logger.error("=========================》从redis中获取key值size: {} ，错误信息： {} ", key, e.getMessage());
        }
        return 0;
    }

    /**
     * 方法重载
     * {@link #getHashObject(String, String)}
     * <pre>
     * 尝试读取数据，当第一次读取不到数据时，再尝试线程休眠，根据休眠时间再次读取数据
     * 使用场景：取数据的时候数据没有，正在入库，变更码表
     * </pre>
     *
     * @param key
     * @param field
     * @param threadSleep 单位：毫秒
     * @return
     */
    @Override
    public Object getHashObject(String key, String field, int threadSleep) {
        return null;
    }

    /**
     * 读取哈希结构中所有values值
     *
     * @param key
     * @return
     */
    @Override
    public List<?> getHashObject(String key) {
        try {
            return redisTemplate.opsForHash().values(key);
        } catch (Exception e) {
            logger.error("从redis中获取key值: {} 数据失败，错误信息： {} ", key, e.getMessage());
            return null;
        }
    }

    /**
     * 根据key值获取整个HashMap对象信息
     *
     * @param key
     * @return
     */
    @Override
    public Map<Object, Object> getHashMap(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 根据key和 模糊匹配的field查询hash对象
     *
     * @param key
     * @param fieldMatch 模糊匹配的field
     * @param count      需要返回结果集个数
     * @return
     */
    @Override
    public List<?> scanHashData(String key, String fieldMatch, long count) {
        List<Object> result = new ArrayList<>();
        ScanOptions options = ScanOptions.scanOptions()
                .count(count).match(key).build();
        RedisSerializer<String> redisSerializer = (RedisSerializer<String>) redisTemplate.getKeySerializer();
        Cursor cursor = (Cursor) redisTemplate.executeWithStickyConnection(redisConnection -> new ConvertingCursor<>(redisConnection.scan(options), redisSerializer::deserialize));
        while (cursor.hasNext()) {
            result.add(cursor.next());
        }
        try {
            cursor.close();
        } catch (IOException e) {

        }
        return result;
    }

    /**
     * 移除哈希结构中的某个field内容
     *
     * @param key
     * @param field
     */
    @Override
    public void removeHashObject(String key, String field) {
        try{
            redisTemplate.opsForHash().delete(key, field);
        }catch (Exception e){
            logger.error("删除redis key: {}失败~"+e.getMessage());
        }
    }

    /**
     * 数组存放队列中
     * List
     *
     * @param key
     * @param models
     * @param expire
     */
    @Override
    public <T> boolean leftPushAll(String key, List<T> models, int expire) {
        redisTemplate.opsForList().leftPushAll(key, models);
        expire(key, expire);
        return true;
    }

    /**
     * 单独放入对队列中
     * value为对象类型，且不设置过期时间，默认永久
     * List
     *
     * @param key
     * @param value
     * @param expire
     */
    @Override
    public boolean leftPush(String key, Object value, int expire) {
        redisTemplate.opsForList().leftPush(key, value);
        return false;
    }

    /**
     * 获取某个队列中元素值
     * List
     *
     * @param key
     */
    @Override
    public long size(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 弹出最右边的元素，弹出之后该值在列表中将不复存在
     * List
     *
     * @param key
     */
    @Override
    public <T> T rightPop(String key) {
        return (T) redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 根据index获取list值 *
     *
     * @param key
     * @param index -1 返回最后一个,下标越界返回null
     */
    @Override
    public <T> T index(String key, long index) {
        return (T) redisTemplate.opsForList().index(key, index);
    }

    /**
     * @param key
     */
    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * @param key
     * @param value 如果不存在就返回1 存在就返回0
     */
    @Override
    public String setnx(String key, String value) {
        return null;
    }

    /**
     * 设置过期时间
     *
     * @param key
     * @param time
     */
    @Override
    public void expire(String key, long time) {
        redisTemplate.opsForValue().set(key, time);
    }
}
