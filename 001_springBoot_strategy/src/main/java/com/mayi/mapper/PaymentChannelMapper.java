package com.mayi.mapper;

import com.mayi.mapper.entity.PaymentChannelEntity;
import org.apache.ibatis.annotations.Select;

/**
 * Created by IDEA
 * User:wang7
 * Date:2019/5/8
 * Time:22:48
 */
public interface PaymentChannelMapper {
    @Select("\n" +
            "SELECT  id as id ,CHANNEL_NAME as CHANNELNAME ,CHANNEL_ID as CHANNELID,strategy_bean_id AS strategybeanid\n" +
            "FROM payment_channel where CHANNEL_ID=#{payCode}")
    public PaymentChannelEntity getPaymentChannel(String payCode);
}
