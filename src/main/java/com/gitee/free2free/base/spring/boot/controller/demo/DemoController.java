package com.gitee.free2free.base.spring.boot.controller.demo;

import com.gitee.free2free.base.spring.boot.controller.BaseController;
import com.gitee.free2free.base.spring.boot.controller.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 演示使用controller
 *
 * @author lfg
 * @version 1.0
 */
@RestController
public class DemoController extends BaseController {

    @RequestMapping("/demo1")
    public Result demo() {
        return success();
    }

}
