package com.xtxk.redis.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by foresee on 2019-03-06.
 */
public interface IRedisService {

    /**
     * 设置字符串类型缓存
     *
     * @param key
     * @param value
     */
    void setString(final String key, String value);

    /**
     * 设置字符串类型缓存并设置过期时间
     *
     * @param key
     * @param value
     * @param expire 过期时间，单位：秒
     */
    void setString(final String key, String value, TimeUnit expire);

    /**
     * 设置对象类型缓存
     *
     * @param key
     * @param value
     */
    void setObject(final String key, Object value);

    /**
     * 设置对象类型缓存并设置过期时间
     *
     * @param key
     * @param value
     * @param expire
     */
    void setObject(final String key, Object value, TimeUnit expire);

    /**
     * 方法重载
     * {@link #setObject(String, java.io.Serializable, int)}
     *
     * @param key
     * @param value
     * @param expire
     * @param cover  false：有则不覆盖，无则添加; true:无论有无均覆盖
     */
    void setObject(final String key, Object value, TimeUnit expire, boolean cover);

    /**
     * 读取字符串类型缓存
     *
     * @param key
     * @return
     */
    String getString(String key);

    /**
     * 读取字符串类型缓存并设置过期时间
     *
     * @param key
     * @param expire
     * @return
     */
    String getString(String key, TimeUnit expire);

    /**
     * 读取对象类型缓存
     *
     * @param key
     * @return
     */
    Object getObject(String key);

    /**
     * 读取对象型缓存并设置过期时间
     *
     * @param key
     * @param expire
     * @return
     */
    Object getObject(String key, TimeUnit expire);

    /**
     * 根据key删除缓存内容
     *
     * @param key
     */
    void removeObject(String key);

    /**
     * 设置缓存过期时间
     *
     * @param key
     * @param expire
     */
    void expire(String key, TimeUnit expire);

    /**
     * 返回当前选定数据库中的key数量
     *
     * @return
     */
    long getRedisSize();

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    boolean exists(String key);

    /**
     * 使用哈希结构存储设置对象类型缓存并设置过期时间
     *
     * @param key
     * @param field
     * @param value
     * @param expire
     */
    boolean setHashObject(String key, String field, Object value, int expire);

    /**
     * 使用哈希结构存储设置Map缓存并设置过期时间
     *
     * @param key
     * @param values
     * @param expire
     */
    <T> boolean setHashObject(final String key, final Map<String, T> values, final int expire);

    /**
     * 使用哈希结构存储设置Map缓存并设置过期时间（适用大数据量存储）
     *
     * @param key
     * @param values
     * @param expire
     */
    void setHashObjectWithPiple(final String key, final Map<String, Object> values, final int expire);

    /**
     * 读取哈希结构中某个field的内容
     *
     * @param key
     * @param field
     * @return
     */
    Object getHashObject(final String key, final String field);

    /**
     * 获取hash key值的数量
     *
     * @param key
     */
    long getHashSize(final String key);

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
    Object getHashObject(final String key, final String field, final int threadSleep);

    /**
     * 读取哈希结构中所有values值
     *
     * @param key
     * @return
     */
    List<?> getHashObject(final String key);

    /**
     * 根据key值获取整个HashMap对象
     *
     * @param key
     * @return
     */
    Map<Object, Object> getHashMap(final String key);

    /**
     * 根据key和 模糊匹配的field查询hash对象
     *
     * @param key
     * @param fieldMatch 模糊匹配的field
     * @param count      需要返回结果集个数
     * @return
     */
    List<?> scanHashData(final String key, final String fieldMatch, final long count);

    /**
     * 移除哈希结构中的某个field内容
     *
     * @param key
     * @param field
     */
    void removeHashObject(final String key, final String field);


    /**
     * 数组存放队列中
     */
    <T> boolean leftPushAll(final String key, final List<T> models, final int expire);

    /**
     * 单独放入对队列中
     * value为对象类型，且不设置过期时间，默认永久
     */
    boolean leftPush(final String key, final Object value, final int expire);

    /**
     * 获取某个队列中元素值
     * *
     */
    long size(final String key);

    /**
     * 弹出最右边的元素，弹出之后该值在列表中将不复存在
     */
    <T> T rightPop(final String key);


    /**
     * 根据index获取list值 *
     */
    <T> T index(final String key, long index);


    /**
     * @param key
     */
    void delete(String key);

    /**
     * @param key
     * @param value 如果不存在就返回1 存在就返回0
     */
    String setnx(String key, String value);

    /**
     * 设置过期时间
     *
     * @param key
     * @param time
     */
    void expire(String key, long time);


}
