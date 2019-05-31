package com.mayikt.factory;

import com.mayikt.service.AbstractPayCallbackTemplate;
import com.mayikt.utils.SpringUtils;

/**
 * @author: Wangmh
 * @date: 2019/5/31  工厂模式获取模版
 * @time: 15:55
 */
public class TemplateFactory {
    public static AbstractPayCallbackTemplate getPayCallbackTemplate(String templateId) {
        AbstractPayCallbackTemplate payCallbackTemplate = (AbstractPayCallbackTemplate) SpringUtils.getBean(templateId);
        return payCallbackTemplate;
    }
}
