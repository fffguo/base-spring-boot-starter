package com.gitee.free2free.base.spring.boot.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lfg
 * @version 1.0
 */
@Data
@ConfigurationProperties(prefix = "free2free.base.log")
public class FreeBaseStarterProperties {

    /**
     * 是否开启日志
     */
    private Boolean enabled = true;
    /**
     * 请求日志格式
     */
    private String requestFormat = "sessionId:{sessionId},请求接口:[{methodName}],请求参数：{request}";
    /**
     * 返回日志格式
     */
    private String responseFormat = "sessionId:{sessionId},请求接口:[{methodName}],返回结果：{response}";


}
