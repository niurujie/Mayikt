
package com.test;

import org.springframework.beans.factory.annotation.Autowired;

public class TokenService {
    @Autowired
    private TokenProperties tokenProperties;

    public String getToken() {
        return "result:" + tokenProperties.toString();
    }

}
