package com.xtxk.redis.config;

import com.xtxk.core.util.LogUtil;
import com.xtxk.redis.model.RedisConfig;
import com.xtxk.redis.service.IRedisService;
import com.xtxk.redis.service.impl.RedisServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import redis.clients.jedis.JedisPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by foresee on 2019-03-06.
 * redis 配置类
 */
@Configuration
public class RedisServiceConfiguration {

    @Value("${online:'false'}")
    private boolean online;

    @Bean
    public RedisConfig getConfig() {
        return new RedisConfig(online);
    }

    /**
     * redis连接池信息
     **/
    @Bean
    @ConditionalOnMissingBean
    public JedisPoolConfig poolConfig(RedisConfig redisConfig) {
        if (redisConfig == null) {
            redisConfig = getConfig();
        }
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisConfig.getMaxTotal());
        return jedisPoolConfig;
    }


    @Bean
    @ConditionalOnMissingBean
    public RedisConnectionFactory getJedisConnectionFactory() {
        RedisConfig redisConfig = getConfig();
        if (LogUtil.ROOT_LOG.isInfoEnabled()) {
            LogUtil.ROOT_LOG.info("redis config配置信息: {}", redisConfig.toString());
        }
        RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration();
        standaloneConfig.setDatabase(0);
        standaloneConfig.setHostName(redisConfig.getHost());
        standaloneConfig.setPort(redisConfig.getPort());
        standaloneConfig.setPassword(RedisPassword.of(redisConfig.getPassword()));

        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder configurationBuilder = (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
        configurationBuilder.poolConfig(poolConfig(redisConfig));
        return new JedisConnectionFactory(standaloneConfig, configurationBuilder.build());
    }

    /**
     * redis存放key，value json持久化
     **/
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    @ConditionalOnMissingBean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(getJedisConnectionFactory());
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);

        // value使用json序列化
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
        StringRedisSerializer redisSerializer = new StringRedisSerializer();
        template.setKeySerializer(redisSerializer);
        template.setValueSerializer(redisSerializer);
        template.setHashKeySerializer(redisSerializer);
        template.setHashValueSerializer(redisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    @ConditionalOnMissingBean
    public IRedisService redisService() {
        return new RedisServiceImpl(redisTemplate());
    }

}
