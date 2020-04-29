package com.gitee.free2free.base.spring.boot.api;

import cn.hutool.http.ContentType;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NonNull;

/**
 * sdk 请求类接口
 * 1.使用takeXxx() 用以规避bean转jsonString时的问题：以getUrl()为例，会自动生成url字段，值为getUrl()的值
 *
 * @author lfg
 * @version 1.0
 */
public interface BaseApiRequest<Response extends BaseApiResponse> {

    /**
     * 获取请求url
     *
     * @return url
     */
    @NonNull
    @JSONField(serialize = false)
    @JsonIgnore
    String getUrl();

    /**
     * 获取请求方式
     *
     * @return Http方法枚举
     */
    @NonNull
    @JSONField(serialize = false)
    @JsonIgnore
    Method getMethod();

    /**
     * 获取 content-type
     *
     * @return 常用Content-Type类型枚举
     */
    @NonNull
    @JSONField(serialize = false)
    @JsonIgnore
    ContentType getContentType();

    /**
     * 获取返回类型 Response.class
     *
     * @return 接口返回类型.class
     */
    @JSONField(serialize = false)
    @JsonIgnore
    Class<Response> getResponse();

    /**
     * 获取jsonString
     *
     * @return json
     */
    default String toJsonString() {
        return JSONObject.toJSONString(this);
    }

}
