package com.lfg.base.spring.boot.controller;

import com.lfg.base.spring.boot.config.Log;
import com.lfg.base.spring.boot.domain.Result;
import com.lfg.base.spring.boot.domain.req.Test2Req;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lfg
 * @version 1.0
 */
@RestController
public class Test extends BaseController {

    @Log("这里填写接口名称")
    @RequestMapping("/test1")
    public Result<Object> test1(String a, String b) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("a", a);
        map.put("b", b);
        return success(map);
    }

    @Log("这里填写接口名称")
    @RequestMapping("/test2")
    public Result<Object> test2(@RequestBody Test2Req req) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("arg1", req.getArg1());
        map.put("arg2", req.getArg2());
        return success(map);
    }
}
