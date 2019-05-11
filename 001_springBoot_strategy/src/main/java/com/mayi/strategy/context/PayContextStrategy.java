package com.mayi.strategy.context;

import com.mayi.mapper.PaymentChannelMapper;
import com.mayi.mapper.entity.PaymentChannelEntity;
import com.mayi.strategy.PayStrategy;
import com.mayi.utils.SpringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by IDEA
 * User:wang7
 * Date:2019/5/8
 * Time:22:45
 */
@Component
public class PayContextStrategy {

    @Autowired
    private PaymentChannelMapper paymentChannelMapper;

    public String toPayHtml(String payCode){
        //1.使用payCode参数查询数据库获取beanid
        PaymentChannelEntity paymentChannelEntity=paymentChannelMapper.getPaymentChannel(payCode);
        if (paymentChannelEntity==null){
            return "没有该渠道信息";
        }
        //2.获取到bean的id之后，使用Spring容器获取实例对象
        String beanId=paymentChannelEntity.getStrategyBeanId();
        if (StringUtils.isEmpty(beanId)){
            return "该渠道没有配置beanid";
        }
        //3.执行该实现的方法即可.... aliPayStrategy
        PayStrategy payStrategy=SpringUtils.getBean(beanId,PayStrategy.class);
        return payStrategy.toPayHtml();
    }



}
