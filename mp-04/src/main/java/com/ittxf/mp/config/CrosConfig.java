package com.ittxf.mp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CrosConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //全部接口
                .allowedOrigins("http://localhost:5173") //允许所有来源，比如：http://localhost:5173
                .allowedMethods("*") // 允许所有方法
                .allowedHeaders("*") // 允许所有头
                .allowCredentials(true) // 允许携带cookie/token凭证
                .maxAge(3600); // 预检请求缓存时间
    }
}
