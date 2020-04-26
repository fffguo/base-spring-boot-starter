package com.gitee.free2free.base.spring.boot.api.demo;

import com.gitee.free2free.base.spring.boot.api.BaseApiResponse;
import lombok.Data;

import java.util.List;

/**
 * 坐标转换 response
 * 逆地理编码的简单返回不包含兴趣点
 *
 * @author liliang
 * @date 2017/6/6
 */
@Data
public class PhoneApiResponse implements BaseApiResponse {

    /**
     * 状态码，0为正常,
     * 310请求参数信息有误，
     * 311Key格式错误,
     * 306请求有护持信息请检查字符串,
     * 110请求来源未被授权
     */
    private int status;

    /**
     * 对status的描述
     */
    private String message;

    /**
     * 坐标转换结果，转换后的坐标顺序与输入顺序一致
     */
    private List<Location> locations;


    @Override
    public boolean successful() {
        return status == 0;
    }
}
