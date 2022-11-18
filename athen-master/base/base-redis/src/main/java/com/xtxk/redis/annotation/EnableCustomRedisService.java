package com.xtxk.redis.annotation;

import com.xtxk.redis.config.RedisServiceConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by foresee on 2019-03-25.
 * redis注解类，只要在模块中使用该注解就可以使用redis模块
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({RedisServiceConfiguration.class})
@Configuration
public @interface EnableCustomRedisService {
}
