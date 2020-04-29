package com.gitee.free2free.base.spring.boot.ding;

import cn.hutool.http.ContentType;
import cn.hutool.http.Method;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gitee.free2free.base.spring.boot.api.BaseApiRequest;
import com.gitee.free2free.base.spring.boot.config.DingConfig;
import com.gitee.free2free.base.spring.boot.utils.DingNoticeUtils;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 钉钉request
 *
 * @author lfg
 * @version 1.0
 */
@Data
@Component
public class DingRequest implements BaseApiRequest<DingResponse> {


    @JSONField(serialize = false)
    @JsonIgnore
    private DingConfig dingConfig;

    /**
     * 消息类型
     */
    @JSONField(name = "msgtype")
    private String msgType = "link";

    /**
     * link
     */
    private LinkDTO link;

    public DingRequest() {
    }

    @Override
    public String getUrl() {
        return DingNoticeUtils.constructRequestUrl(dingConfig.getWebHook(), dingConfig.getSecret());
    }

    @Override
    public Method getMethod() {
        return Method.POST;
    }

    @Override
    public ContentType getContentType() {
        return ContentType.JSON;
    }

    @Override
    public Class<DingResponse> getResponse() {
        return DingResponse.class;
    }
}
