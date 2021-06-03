package com.shenzhou.player.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域配置
 *
 * @author Mr.Cao
 **/
@Configuration
public class CorsConfig {

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        // 是否允许请求带有验证信息
        config.setAllowCredentials(true);
        // 允许服务端访问的客户端请求头
        config.addAllowedHeader("*");
        // 如果要限制 HEADER 或 METHOD 请自行更改
        config.addAllowedMethod("*");
        // 设置你要允许的网站域名,如果全允许则设为 *
        config.addAllowedOriginPattern("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        // 这个顺序很重要,为避免麻烦请设置在最前
        bean.setOrder(0);
        return bean;
    }

}
