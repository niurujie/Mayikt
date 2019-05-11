package com.mayi.mapper.entity;

import lombok.Data;

/**
 * Created by IDEA
 * User:wang7
 * Date:2019/5/8
 * Time:22:48
 */
@Data
public class PaymentChannelEntity {
    /** ID */
    private Integer id;
    /** 渠道名称 */
    private String channelName;
    /** 渠道ID */
    private String channelId;
    /**
     * 策略执行beanId
     */
    private String strategyBeanId;
}
