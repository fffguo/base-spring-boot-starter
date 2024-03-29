package com.gitee.free2free.base.spring.boot.api;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * sdk基础工具类
 *
 * @author lfg
 * @version 1.0
 */
@Slf4j
@Component
public class BaseApi {

    /**
     * 发起请求
     * 请求方式支持 GET POST
     * Content-type 支持
     * ------------------FORM_URLENCODED ：get请求会把request参数拼接在url上
     * ------------------JSON：post请求会把request参数转json放在请求体中
     *
     * @param request    请求bean
     * @param <Response> 返回bean.class
     * @return 返回bean
     */
    public <Response extends BaseApiResponse> Response execute(BaseApiRequest<Response> request) {
        String result;
        switch (request.getMethod()) {
            case GET:
                result = handlerGet(request);
                break;
            case POST:
                result = handlerPost(request);
                break;
            default:
                throw new HttpException("不支持该请求方式");
        }
        Response response = JSONObject.parseObject(result, request.getResponse());
        if (response.isSuccess()) {
            log.debug("\n请求接口：{}\n请求参数：{}\n返回结果：{}", request.getUrl(), request.toJsonString(), result);
        } else {
            log.error("\n请求接口失败：{}\n请求参数：{}\n返回结果：{}", request.getUrl(), request.toJsonString(), result);
        }
        return response;
    }

    /**
     * get方法
     *
     * @param request    请求参数
     * @param <Response> 返回Bean.class
     * @return http请求 body内容
     */
    private <Response extends BaseApiResponse> String handlerGet(BaseApiRequest<Response> request) {
        return HttpUtil.createGet(request.getUrl())
                .contentType(request.getContentType().toString())
                .form(BeanUtil.beanToMap(request, false, true))
                .execute().body();
    }

    /**
     * 处理POST请求
     *
     * @param request    请求参数
     * @param <Response> 返回Bean.class
     * @return http请求 body内容
     */
    private <Response extends BaseApiResponse> String handlerPost(BaseApiRequest<Response> request) {
        return HttpUtil.createPost(request.getUrl())
                .contentType(request.getContentType().getValue())
                .body(request.toJsonString())
                .execute().body();
    }
}
