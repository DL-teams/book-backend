package com.dl.book.config;

import com.dl.book.common.config.BaseSwaggerConfig;
import com.dl.book.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger API文档相关配置
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.dl.book.modules")
                .title("book-backend")
                .description("图书管理系统")
                .contactName("张聪明")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
