package com.ittxf.mp.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {

    // MyBatis-Plus 的插件机制底层基于 MyBatis 的拦截器（Interceptor） 实现
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        // 创建 MybatisPlusInterceptor 拦截器链，用于集中管理 Mybatis-Plus 的各种功能拦截器
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        // 创建分页拦截器实例，指定数据库类型为 MySQL
        // DbType.MYSQL 会自动生成适合 MySQL 的分页 SQL（使用 LIMIT 语句）
        PaginationInnerInterceptor pageInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);

        // 设置单次分页查询的最大记录数限制，防止恶意大数量查询（如 pageSize=10000）
        // 当 pageSize 参数值超过 500 时，会自动调整为 500
        // 这可以有效防止内存溢出和数据库性能问题
        pageInterceptor.setMaxLimit(500L);

        // 将分页拦截器添加到拦截器链中
        // 拦截器会按照添加顺序执行，分页拦截器通常放在最后
        interceptor.addInnerInterceptor(pageInterceptor);

        // 返回配置好的拦截器实例，Spring 会将其注册到 Mybatis 的插件链中
        return interceptor;
    }
}

