package com.xtxk.swagger.annotation;

import com.xtxk.swagger.config.SwaggerServiceConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({SwaggerServiceConfiguration.class})
@Configuration
public @interface EnableSwaggerService {
}
