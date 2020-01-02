# base-spring-boot

#### 介绍
一个springboot的基础库，负责springboot项目的一些初始化工作，例如日志切面，接口通用返回格式baseController

#### 软件架构
软件架构说明
jdk 1.8
fastjson
lombok
spring aop
spring web

#### 安装教程

1.  xxxx
2.  xxxx
3.  xxxx

#### 使用说明

1.  使用日志切面：只需要在接口上添加Log注解即可
```
    @Log("这里填写接口名称")
    @RequestMapping("/test2")
    public Result<Object> test2(@RequestBody Test2Req req) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("arg1", req.getArg1());
        map.put("arg2", req.getArg2());
        return success(map);
    }
```

2.  baseController：只需在controller上继承baseController即可

```
@RestController
public class Test extends BaseController {
    //可以使用success()、fail()、success(Object data)、fail(String msg)、fail(String code, String msg)等方法
    @Log("这里填写接口名称")
    @RequestMapping("/test1")
    public Result<Object> test1(String a, String b) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("a", a);
        map.put("b", b);
        return success(map);
    }
}

```


#### 参与贡献

1.  Fork 本仓库
2.  新建 Feat_xxx 分支
3.  提交代码
4.  新建 Pull Request


#### 码云特技

1.  使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2.  码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3.  你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4.  [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5.  码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6.  码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)