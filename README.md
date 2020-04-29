# base-spring-boot

#### 介绍
一个springboot的基础库，负责springboot项目的一些初始化工作，例如日志切面，接口通用返回格式baseController

#### 软件架构
软件架构说明
1. jdk 1.8
2. fastjson
3. spring aop
4. spring web
5. hutool

#### 安装教程

1.  引入依赖即可,
```
        <dependency>
            <groupId>com.gitee.free2free</groupId>
            <artifactId>base-spring-boot-starter</artifactId>
            <version>2.0.0-RELEASE</version>
        </dependency>
```
2.  xxxx
3.  xxxx

#### 使用说明

1.  baseController：只需在controller上继承baseController即可

[=>查看详情<=](https://gitee.com/free2free/base-spring-boot-starter/wikis/1.%20BaseController?sort_id=1845423)

```
@RestController
public class DemoController  extends BaseController {

        @RequestMapping("/demo1")
        public Result demo1() {
            return success();
        }
}

```

2.  使用日志切面：只需要在接口上添加@AroundLog注解即可

[=> 查看详情<=](https://gitee.com/free2free/base-spring-boot-starter/wikis/2.%20%E4%BD%BF%E7%94%A8%E6%97%A5%E5%BF%97%E5%88%87%E9%9D%A2@FreeLog?sort_id=1845426)

```
    /**
     * 最基础用法    @AroundLog
     * 请求url：http://127.0.0.1:8080/log1/value1?arg2=value2
     *
     * @param arg1 参数1
     * @param arg2 参数2
     * @return 返回结果
     */
    @AroundLog
    @RequestMapping("/log1/{arg1}")
    public Result log1(@PathVariable String arg1, @RequestParam String arg2) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("arg1", arg1);
        map.put("arg2", arg2);
        return success(map);
    }
```
3.异常处理ExceptionHandler
>不新建ExceptionHandler类的话，会有baseExceptionHandler行使一些默认的异常处理。 如果想实现自定义的异常处理，可以新建自己的ExceptionHandler，只需继承BaseExceptionHandler， 重写BaseExceptionHandler的相关方法即可不新建ExceptionHandler类的话，会有baseExceptionHandler行使一些默认的异常处理。 如果想实现自定义的异常处理，可以新建自己的ExceptionHandler，只需继承BaseExceptionHandler， 重写BaseExceptionHandler的相关方法即可

>[=>查看详情<=](https://gitee.com/free2free/base-spring-boot-starter/wikis/3.%20%E5%BC%82%E5%B8%B8%E5%A4%84%E7%90%86ExceptionHandler?sort_id=1859544)

```java
/**
 * 项目自定义 异常处理，继承 baseExceptionHandler
 *
 * @author lfg
 * @version 1.0
 */
@Slf4j
@ControllerAdvice
public class ProjectExceptionHandler extends BaseExceptionHandler {
    public ProjectExceptionHandler() {
        super();
    }
    //--------------------------------------可以自定义其他异常处理方法、或重写父类方法-----------------------------

    /**
     * 处理 ProjectException
     *
     * @param response response
     * @param e        异常
     */
    @ExceptionHandler(ProjectException.class)
    public void handleBaseException(HttpServletResponse response, ProjectException e) {
        log.error("ProjectException:{}", e.getMessage(), e);
        print(response, JSONObject.toJSONString(new Result(e.getCode(), e.getMessage())));
    }

    @Override
    protected void noticeDing(Exception e) {
        super.noticeDing(e);
    }
}
```

4.钉钉异常通知

[=>查看详情<=](https://gitee.com/free2free/base-spring-boot-starter/wikis/4.%20%E9%92%89%E9%92%89%E5%BC%82%E5%B8%B8%E9%80%9A%E7%9F%A5?sort_id=2165672)
> 配置文件
>
```yaml
base:
  ding:
    web-hook: https://oapi.dingtalk.com/robot/send?access_token=739cee21357169a79a97ebf6cec7e5d01a7f46b9f7a3fd75ab2dada541b0e009
    secret: SECc1fcf0b97789885185d674602698659829c12aa128a6fef0f0de7f18c168f5a5
```

> 当发生DingException 或 其他未在exceptionHandler中捕获的一般异常时，会发钉钉通知
```java
/**
 * 钉钉异常通知demo
 * 需要配置文件中 配置 base.ding.web-hook，才会触发推送
 * <p>
 * 1.自定义关键词校验：  需配置 '告警' 关键字
 * 2.签名校验：         需配置 base.ding.secret
 * <p>
 * 官方文档：https://ding-doc.dingtalk.com/doc#/serverapi2/qf2nxq
 * <p>
 * ps：
 * 1.可以自定义钉钉图片url  pictureUrl
 * 2.可以自定义跳转url，钉钉通知点击打开后的页面，程序会自动在该页面后拼接?sessionId=xxx&msg=xxx，可以实现自定义的异常占线方式
 *
 * @author lfg
 * @version 1.0
 */
@RestController
public class DingController extends BaseController {
    /**
     * 其他自定义异常：http://127.0.0.1:8080/ding1/value1?arg2=value2
     *
     * @return response
     */
    @RequestMapping("/ding1/{arg1}")
    public Result exception4(@PathVariable String arg1, @RequestParam String arg2) throws Exception {
        throw new Exception("数组下标越界");
    }
}

```

5.通用接口调用

[=>查看详情<=](https://gitee.com/free2free/base-spring-boot-starter/wikis/5.%20%E9%80%9A%E7%94%A8%E6%8E%A5%E5%8F%A3%E8%B0%83%E7%94%A8?sort_id=2165686)

```java
@SpringBootTest
@RunWith(SpringRunner.class)
public class ApiTest {
    @Test
    public void get1() {
        BaseApi baseApi = new BaseApi();

        TranslateRequest request = new TranslateRequest();
        request.setLocations("39.12,116.83;30.21,115.43");
        request.setType(1);
        request.setKey(KEY);

        TranslateResponse response = baseApi.execute(request);
        Assert.assertTrue(response.isSuccess());
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
