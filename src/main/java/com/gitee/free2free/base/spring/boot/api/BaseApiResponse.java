package com.gitee.free2free.base.spring.boot.api;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * sdk 接口返回类
 *
 * @author lfg
 * @version 1.0
 */
public interface BaseApiResponse {

    /**
     * true: 成功   |   false: 失败
     *
     * @return 是/否 成功
     */
    @JSONField(serialize = false)
    @JsonIgnore
    boolean isSuccess();
}
