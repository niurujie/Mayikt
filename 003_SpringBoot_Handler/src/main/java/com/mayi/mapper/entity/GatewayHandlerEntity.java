package com.mayi.mapper.entity;

import lombok.Data;

/**
 * Created by IDEA
 * User:wang7
 * Date:2019/5/9
 * Time:23:07
 */
@Data
public class GatewayHandlerEntity {
    /**
     * 主键ID
     */
    private Integer id;
    /**
     * handler名称
     */
    private String handlerName;
    /**
     * handler主键id
     */
    private String handlerId;
    /**
     * 下一个handler
     */
    private String nextHandlerId;
}
