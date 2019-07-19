#后端开发通用脚手架
    
##一、项目简介
    这是一个用于前后台分离的后端通用的基础脚手架，主要用于快速开发项目的初始阶段。

##二、运行部署
1. 地址（禁止出现用户名密码）
    >测试环境地址：
    <br>
    生产环境地址：

##三、接口文档
1. 项目使用 **swagger** 作为前后端交互文档，地址：
    > http://loaclhost:8080/swagger-ui.html

##四、软件架构
1. SpringBoot
1. SpringMVC Validation
1. ThreadPoolTaskExecutor
1. Swagger2
1. Redis
1. Lombok
1. Gson

##五、注意事项
1. 系统中有统一的异常处理，非必要不要捕获exception，除非是业务反馈（余额不足），否则直接声明异常，如确有必要捕获异常，注意抛出可用信息异常进行消息传递;
1. 业务异常 传递消息，杜绝 return 1  、 return head  、return errCode 等返回，建议类似使用AssertionUtils.message("your message");
1. 后台强制检查，对于任何前台检查保持不信任，防止通过非信任方式调用：建议使用Spring MVC @Validated 进行bean检查 https://blog.csdn.net/Mynewclass/article/details/79086372;
1. 返回信息约定 ，常规的在 CustomResultCodeEnum 中定义，非常规情况请注意把控；
1. bean命名约定，请使用全称驼峰命名规则，严禁使用简称，除非简称是社会上广为人知约定俗成的;
1. 返回任何展示层的对象，建议增加VO，通过 entity转VO 直接返回VO对象，任何情况不建议直接返回entity;
1. 若用 BeanUtils.copyProperties 建议 VO中的成员变量 和 entity中成员变量 除非（类型、语义、对应关系等）严格一致 否则 使用不一样的名称。（范型检查和范型擦除）;
1. 数据库表名 使用 下划线 进行语义分隔的同时注意数据库对表名长度的限制，oracle 30字符以内，mysql 64字符;
1. service中涉及到事务的（增、改、删）必须指定rollbackFor：@Transactional(rollbackFor = Exception.class);
1. controller层按业务模块严格对应，service层无要求;
1. 接口命名尽量遵守RESTfull api规范（简单使用：对服务器有更改的使用post请求，没有的使用get请求）
1. 尽量保证重要模块的单元测试，但在快速开发的过程中不建议使用TDD进行编程;
1. 使用“阿里编码规约”检查代码，尽量使自己的所有代码符合 规约 要求;
1. 小步实现、提交：任何需求模块，建议分步实现，并在各个阶段保持提交到自己的分支上;
1. 保持可运行：任何提交保证程序能正常启动和使用;

##六、项目结构
1. base包
    >存放与业务关联性不强的内容或公共类，如 配置类、拦截器、统一封装类、AOP和util等。
1. controller、service和dao包：
    >存放业务相关的分层相关类。
1. bean包：
    >存放业务相关的封装类 如 entity（po）、vo、dto、query（查询封装类）、parse（解析封装类）

##七、用户使用说明
1. 管理员：
1. 普通用户：