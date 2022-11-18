package com.xtxk.swagger.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.google.common.collect.Ordering;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.OperationNameGenerator;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.DocumentationPluginsManager;
import springfox.documentation.spring.web.readers.operation.OperationReader;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;
import springfox.documentation.swagger2.mappers.ServiceModelToSwagger2MapperImpl;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2WebMvc
@EnableKnife4j
public class SwaggerServiceConfiguration {
    public static final String AUTHORIZATION_HEADER = "AuthorToken";

    /**
     * 定义接口的总体信息
     *
     * @return /api
     */
    private ApiInfo webApiInfo() {

        return new ApiInfoBuilder()
                .title("后台服务-API接口文档")
                .description("本文档描述了网站接口定义")
                .version("3.0")
                .contact(new Contact("XXX", "", ""))
                .build();
    }


    @Bean
    // Docket 生成的接口bean  web 用户
    public Docket webApiConfig() {

        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        ticketPar.name(AUTHORIZATION_HEADER).description("user Token")//Token 以及Authorization 为自定义的参数，session保存的名字是哪个就可以写成那个
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build(); //header中的ticket参数非必填，传空也可以
        pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                //只显示api路径下的页面
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
        return docket;
    }
}
