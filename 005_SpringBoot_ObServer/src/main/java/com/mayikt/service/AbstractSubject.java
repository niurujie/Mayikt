package com.mayikt.service;

/**
 * @author: Wangmh
 * @date: 2019/5/31  抽象主题者
 * @time: 16:31
 */
public interface AbstractSubject {

    /**
     * 添加observer
     *
     * @param obServer
     */
    void addObServer(ObServer obServer);


    /**
     * 删除observer
     *
     * @param obServer
     */
    void rmoveObServer(ObServer obServer);

    /**
     * 通知所有的notifyObServerAll
     *
     * @param message
     */
    void notifyObServerAll(String message);

    /**
     * 设置更新内容
     */
    void setNtifyMessage(String message);
}
