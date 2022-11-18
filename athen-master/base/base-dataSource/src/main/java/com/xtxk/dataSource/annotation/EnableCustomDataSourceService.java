package com.xtxk.dataSource.annotation;

import com.xtxk.dataSource.config.SessionFactoryConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Created by foresee on 2019-03-25.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({SessionFactoryConfiguration.class})
@Configuration
public @interface EnableCustomDataSourceService {
}
