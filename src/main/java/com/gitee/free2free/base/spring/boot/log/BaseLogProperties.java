package com.gitee.free2free.base.spring.boot.log;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lfg
 * @version 1.0
 */
@Data
@ConfigurationProperties(prefix = "free2free.base.log")
public class BaseLogProperties {

    /**
     * 是否开启日志
     */
    private Boolean enabled = true;
    /**
     * 请求日志格式
     */
    private String requestFormat = "sessionId:" + BaseLogKeyConstant.SESSION_ID
            + ",请求接口:" + BaseLogKeyConstant.METHOD_NAME
            + ",请求参数：" + BaseLogKeyConstant.REQUEST;
    /**
     * 返回日志格式
     */
    private String responseFormat = "sessionId:" + BaseLogKeyConstant.SESSION_ID
            + ",请求接口:" + BaseLogKeyConstant.METHOD_NAME
            + ",返回参数：" + BaseLogKeyConstant.RESPONSE;


}
