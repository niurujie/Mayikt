
package com.test;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//定义配置类
@EnableConfigurationProperties(TokenProperties.class)//使用TokenProperties配置生效
public class TokenAutoConfiguration {

    @Bean
    public TokenService tokenService() {
        return new TokenService();
    }

}
