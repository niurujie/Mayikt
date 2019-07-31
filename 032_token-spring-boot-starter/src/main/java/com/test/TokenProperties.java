package com.test;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "test")//从配置文件中读取值，test.token-redis-host
public class TokenProperties {
    private String tokenRedisHost;
    private String tokenRedisPwd;

    public void setTokenRedisPwd(String tokenRedisPwd) {
        this.tokenRedisPwd = tokenRedisPwd;
    }

    public void setTokenRedisHost(String tokenRedisHost) {
        this.tokenRedisHost = tokenRedisHost;
    }

    public String getTokenRedisPwd() {
        return tokenRedisPwd;
    }

    public String getTokenRedisHost() {
        return tokenRedisHost;
    }

    @Override
    public String toString() {
        return "TokenProperties{" +
                "tokenRedisHost='" + tokenRedisHost + '\'' +
                ", tokenRedisPwd='" + tokenRedisPwd + '\'' +
                '}';
    }
}
