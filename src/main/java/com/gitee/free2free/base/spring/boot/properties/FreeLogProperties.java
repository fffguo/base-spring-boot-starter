package com.gitee.free2free.base.spring.boot.properties;

import com.gitee.free2free.base.spring.boot.config.FreeLogKeyConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author lfg
 * @version 1.0
 */
@Data
@ConfigurationProperties(prefix = "free2free.base.log")
public class FreeLogProperties {

    /**
     * 是否开启日志
     */
    private Boolean enabled = true;
    /**
     * 请求日志格式
     */
    private String requestFormat = "sessionId:" + FreeLogKeyConstant.SESSION_ID
            + ",请求接口:" + FreeLogKeyConstant.METHOD_NAME
            + ",请求参数：" + FreeLogKeyConstant.REQUEST;
    /**
     * 返回日志格式
     */
    private String responseFormat = "sessionId:" + FreeLogKeyConstant.SESSION_ID
            + ",请求接口:" + FreeLogKeyConstant.METHOD_NAME
            + ",返回参数：" + FreeLogKeyConstant.RESPONSE;


}
