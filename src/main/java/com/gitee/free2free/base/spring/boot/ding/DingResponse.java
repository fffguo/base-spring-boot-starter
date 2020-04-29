package com.gitee.free2free.base.spring.boot.ding;

import com.alibaba.fastjson.annotation.JSONField;
import com.gitee.free2free.base.spring.boot.api.BaseApiResponse;
import lombok.Data;

/**
 * 钉钉Response
 *
 * @author lfg
 * @version 1.0
 */
@Data
public class DingResponse implements BaseApiResponse {
    /**
     * errCode
     */
    @JSONField(name = "errcode")
    private Integer errCode;

    /**
     * errMsg
     */
    @JSONField(name = "errmsg")
    private String errMsg;

    @Override
    public boolean isSuccess() {
        return errCode != null && errCode == 0;
    }

}
