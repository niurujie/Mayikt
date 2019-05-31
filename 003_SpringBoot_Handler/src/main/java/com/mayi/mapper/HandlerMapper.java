package com.mayi.mapper;

import com.mayi.mapper.entity.GatewayHandlerEntity;
import org.apache.ibatis.annotations.Select;

/**
 * Created by IDEA
 * User:wang7
 * Date:2019/5/9
 * Time:23:06
 */
public interface HandlerMapper {
    /**
     * 获取第一个GatewayHandler
     *
     * @return
     */
    @Select("SELECT  handler_name AS handlerName,handler_id AS handlerid ,prev_handler_id AS prevhandlerid ,next_handler_id AS nexthandlerid  FROM gateway_handler WHERE  prev_handler_id is null;;")
    public GatewayHandlerEntity getFirstGatewayHandler();

    @Select("SELECT  handler_name AS handlerName,handler_id AS handlerid ,prev_handler_id AS prevhandlerid ,next_handler_id AS nexthandlerid   FROM gateway_handler WHERE  handler_id=#{handlerId}")
    public GatewayHandlerEntity getByHandler(String handlerId);

}
