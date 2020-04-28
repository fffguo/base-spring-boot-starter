package com.gitee.free2free.base.spring.boot.log;

/**
 * controller 日志切面类型
 *
 * @author lfg
 * @version 1.0
 */
public enum AroundType {
    /**
     * REQUEST : 打印请求日志
     * RESPONSE ：打印返回日志
     * DATA ： 打印请求和返回日志
     */
    REQUEST, RESPONSE, DATA;

    /**
     * 是否能打印request日志
     *
     * @param type 类型
     * @return true or false
     */
    public static boolean canPrintRequest(AroundType type) {
        return type == REQUEST || type == DATA;
    }

    /**
     * 是否能打印response日志
     *
     * @param type 类型
     * @return true or false
     */
    public static boolean canPrintResponse(AroundType type) {
        return type == RESPONSE || type == DATA;
    }
}
