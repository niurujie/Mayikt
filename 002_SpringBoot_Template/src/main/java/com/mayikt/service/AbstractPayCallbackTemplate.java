package com.mayikt.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: Wangmh
 * @date: 2019/5/31  模板方法抽象类
 * @time: 15:19
 */
@Slf4j
@Component
public abstract class AbstractPayCallbackTemplate {
    public String asyncCallBack() {
        // 1. 支付回调验证参数
        Map<String, String> verifySignatureMap = verifySignature();
        // 2. 参数验证成功，写入日志中..
        payLog(verifySignatureMap);
        String analysisCode = verifySignatureMap.get("analysisCode");
        if (!analysisCode.equals("200")) {
            return resultFail();
        }
        // 3. 执行回调异步相关逻辑
        return asyncService(verifySignatureMap);
    }

    /**
     * 每个子类需要实现 实现业务解析操作
     *
     * @return
     */
    protected abstract String asyncService(Map<String, String> verifySignatureMap);

    /**
     * 日志收集
     *
     * @param verifySignatureMap
     */
    private void payLog(Map<String, String> verifySignatureMap) {
        log.info(">>>>>>>>>>第二步 写入payLog........");
    }

    ;

    /**
     * 异步返回结果
     *
     * @return
     */
    protected abstract String resultSuccess();

    /**
     * 异步返回失败
     *
     * @return
     */
    protected abstract String resultFail();


    /**
     * 支付回调验证参数
     *
     * @return
     */
    protected abstract Map<String, String> verifySignature();
}

