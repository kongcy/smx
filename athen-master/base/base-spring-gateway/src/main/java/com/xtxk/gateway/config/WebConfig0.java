package com.xtxk.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;


/**
 * 跨域配置
 */
@Configuration
public class WebConfig0 {
//public class WebConfig implements WebMvcConfigurer{

	/**\
	 * 新版
	 * @param registry
	 */
	/*@Override
	public void addCorsMappings(CorsRegistry registry){
		registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedHeaders("*")
				.allowedMethods("*")
				.allowCredentials(true)
				.maxAge(3600);
	}*/

	/**
	 * 旧版
	 */
/*	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer() {
			@Override public void addCorsMappings(CorsRegistry registry){
				registry.addMapping("/**")
						.allowedOrigins("*")
						.allowedHeaders("*")
						.allowedMethods("*")
						.allowCredentials(true)
						.exposedHeaders(HttpHeaders.SET_COOKIE)
						.maxAge(3600);
			}
		};
	}*/

	private CorsConfiguration corsConfig(){
		CorsConfiguration corsConfiguration=new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setMaxAge(3600L);
		return corsConfiguration;
	}

	@Bean
	public CorsWebFilter corsWebFilter(){
		UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**",corsConfig());
		return new CorsWebFilter(source);
	}

	/*@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**",corsConfig());
		FilterRegistrationBean<CorsFilter> bean=new FilterRegistrationBean<>(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}*/
}
