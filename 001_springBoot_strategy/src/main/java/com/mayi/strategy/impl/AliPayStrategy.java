package com.mayi.strategy.impl;

import com.mayi.strategy.PayStrategy;
import org.springframework.stereotype.Component;

/**
 * Created by IDEA
 * User:wang7
 * Date:2019/5/8
 * Time:22:35
 */
@Component
public class AliPayStrategy implements PayStrategy {

    @Override
    public String toPayHtml() {
        return "调用阿里支付接口.......";
    }
}
