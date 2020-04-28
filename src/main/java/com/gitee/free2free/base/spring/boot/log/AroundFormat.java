package com.gitee.free2free.base.spring.boot.log;

/**
 * @author lfg
 * @version 1.0
 */
public class AroundFormat {

    /**
     * sessionId
     */
    public static final String SESSION_ID = "{sessionId}";
    /**
     * 方法名
     */
    public static final String METHOD_NAME = "{methodName}";
    /**
     * request
     */
    public static final String REQUEST = "{request}";
    /**
     * response
     */
    public static final String RESPONSE = "{response}";

    /**
     * 默认请求日志格式
     */
    public static final String DEFAULT_REQUEST_FORMAT = "sessionId:" + SESSION_ID + ",请求接口:" + METHOD_NAME + ",请求参数：" + REQUEST;

    /**
     * 默认返回日志格式
     */
    public static final String DEFAULT_RESPONSE_FORMAT = "sessionId:" + SESSION_ID + ",请求接口:" + METHOD_NAME + ",返回参数：" + RESPONSE;

}
