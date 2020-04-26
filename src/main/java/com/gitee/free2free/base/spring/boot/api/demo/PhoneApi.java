package com.gitee.free2free.base.spring.boot.api.demo;

import com.gitee.free2free.base.spring.boot.api.BaseApi;

/**
 * PhoneApi
 *
 * @author lfg
 * @version 1.0
 */
public class PhoneApi {

    /**
     * 转换经纬度
     *
     * @param request 请求参数
     * @return response
     */
    public PhoneApiResponse translate(PhoneApiRequest request) {
        BaseApi baseApi = new BaseApi();
        return baseApi.execute(request);
    }


}
