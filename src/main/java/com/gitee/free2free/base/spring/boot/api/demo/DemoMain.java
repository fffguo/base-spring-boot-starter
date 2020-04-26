package com.gitee.free2free.base.spring.boot.api.demo;

import com.alibaba.fastjson.JSONObject;
import com.gitee.free2free.base.spring.boot.api.BaseApi;

/**
 * main
 * new生成实例的方式仅做演示，spring中可以改造成注入bean的方式
 *
 * @author lfg
 * @version 1.0
 */
public class DemoMain {

    /**
     * 所有接口都可调用execute方法执行http请求
     *
     * @param args args
     */
    public static void main(String[] args) {
        BaseApi baseApi = new BaseApi();
        PhoneApiResponse response = baseApi.execute(
                PhoneApiRequest.builder()
                        .key("R4SBZ-XKQC6-2OMS3-MBK4H-NBOZO-Q7FSJ")
                        .locations("39.12,116.83;30.21,115.43")
                        .type(1)
                        .build()
        );
        System.out.println(JSONObject.toJSONString(response));
    }

    /**
     * 可以封装一层，使调用接口更清晰容易一些，
     * 即调用方想要调用“转换经纬度”，由原先得知道构造PhoneApiRequest，来执行execute()
     * 变成只要知道调用translate方法，即可
     *
     * @param args args
     */
    public static void main2(String[] args) {
        PhoneApi api = new PhoneApi();
        PhoneApiResponse response = api.translate(PhoneApiRequest.builder()
                .key("R4SBZ-XKQC6-2OMS3-MBK4H-NBOZO-Q7FSJ")
                .locations("39.12,116.83;30.21,115.43")
                .type(1)
                .build());
        System.out.println(JSONObject.toJSONString(response));
    }
}
