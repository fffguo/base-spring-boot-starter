package com.gitee.free2free.base.spring.boot.api.demo;

import cn.hutool.http.ContentType;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSONObject;
import com.gitee.free2free.base.spring.boot.api.BaseApiRequest;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * 坐标转换 request
 * 最后构建的url为：https://apis.map.qq.com/ws/coord/v1/translate?locations=xxx&type=xxx&key=xxx
 *
 * @author lfg
 * @version 1.0
 */
@Data
@Builder
public class PhoneApiRequest implements BaseApiRequest<PhoneApiResponse> {

    /**
     * 预转换的坐标，支持批量转换，
     * 格式：纬度前，经度后，纬度和经度之间用",“分隔，每组坐标之间使用”;"分隔；
     * 批量支持坐标个数以HTTP GET方法请求上限为准
     */
    @NonNull
    private String locations;

    /**
     * 输入的locations的坐标类型
     * 可选值为[1,6]之间的整数，每个数字代表的类型说明：
     * 1 GPS坐标
     * 2 sogou经纬度
     * 3 baidu经纬度
     * 4 mapbar经纬度
     * 5 [默认]腾讯、google、高德坐标
     * 6 sogou墨卡托
     */
    @NonNull
    private Integer type;

    /**
     * 开发密钥（Key）
     */
    @NonNull
    private String key;

    @Override
    public String takeUrl() {
        return "https://apis.map.qq.com/ws/coord/v1/translate";
    }

    @Override
    public Method takeMethod() {
        return Method.GET;
    }

    @Override
    public ContentType takeContentType() {
        return ContentType.FORM_URLENCODED;
    }

    @Override
    public Class<PhoneApiResponse> takeResponse() {
        return PhoneApiResponse.class;
    }

    @Override
    public String toJsonString() {
        return JSONObject.toJSONString(this);
    }
}
