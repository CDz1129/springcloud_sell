# springcloud_sell

eureka:服务注册中心

# 客户端负载均衡:
## ribbon:
### 内部机制
- 服务发现
- 服务选择规则
- 服务监听(检查所有服务,做到高效剔除)
### 内部组件
- ServerList
- IRule
规则的使用,通过源码可以发现 若不设置使用的是RoundRobinRule(轮询),也可以自定义规则或者切换其他规则
通过yml参数设置

- ServerListFilter
步骤:

1.通过ServerList获取所有的服务列表

2.通过ServerListFilter过滤部分地址

3.通过IRule定义的规则命中一台地址

### 使用到的组件:
- RestTemplate:spring提供的restFul请求接口(用到LoadBalancer(ribbon)来实现客户端的 软负载均衡)
- Feign(本质http客户端)
    - 声明式REST客户端，伪RPC
    - 采用基于`接口加注解`的形式
- Zuul

# spring cloud config
    配置中心：使用的目的是为了更容易管理配置文件，微服务架构中项目分散，配置往往比较难于管理，如果还是传统项目一样将配置文件写在项目中，会有很大的管理成本。
    
主要作用：
   - 同一所有项目，管理配置文件。
   - 做到修改配置文件不需要重启项目
## server端
新建config项目加入依赖：
- `spring-cloud-starter-netflix-eureka-client`同样也是一个客户端
- `spring-cloud-config-server`config server依赖
- `spring-cloud-starter-bus-amqp`消息总线（用于修改文件项目实时更新）

### 添加配置
```
// eureka 客户端的相关配置 就不写在这里了 ...
  cloud:
    config:
      server:
        git:
          uri: https://gitlab.com/your-Repository-url //配置文件的git仓库地址
          username: @username //登陆用户名
          password: @password //密码
          basedir: E:\Workspaces\springcloud_sell\config\basedir //本地快照存储目录
          //（这里configserver 会在链接上git后从上下载一份一样的在本地，当git链接不上时使用本地配置文件）
 ```
 ### 注意
 启动后就成为一个config客户端了，通过 `http://localhost:8082/order-dev.yml`直接访问配置文件
 如果直接访问``是会报404 看启动日子可知
 ```
 {[/{name}-{profiles}.properties],methods=[GET]}" onto public org.springframework.http.ResponseEntity<java.lang.String> org.springframework.cloud.config.server.environment.EnvironmentController.properties(java.lang.String,java.lang.String,boolean) throws java.io.IOException
 "{[/{name}-{profiles}.yml || /{name}-{profiles}.yaml],methods=[GET]}" onto public org.springframework.http.ResponseEntity<java.lang.String> org.springframework.cloud.config.server.environment.EnvironmentController.yaml(java.lang.String,java.lang.String,boolean) throws java.lang.Exception
 "{[/{name}/{profiles:.*[^-].*}],methods=[GET]}" onto public org.springframework.cloud.config.environment.Environment org.springframework.cloud.config.server.environment.EnvironmentController.defaultLabel(java.lang.String,java.lang.String)
 "{[/{label}/{name}-{profiles}.properties],methods=[GET]}" onto public org.springframework.http.ResponseEntity<java.lang.String> org.springframework.cloud.config.server.environment.EnvironmentController.labelledProperties(java.lang.String,java.lang.String,java.lang.String,boolean) throws java.io.IOException
 "{[/{label}/{name}-{profiles}.json],methods=[GET]}" onto public org.springframework.http.ResponseEntity<java.lang.String> org.springframework.cloud.config.server.environment.EnvironmentController.labelledJsonProperties(java.lang.String,java.lang.String,java.lang.String,boolean) throws java.lang.Exception
 "{[/{name}/{profiles}/{label:.*}],methods=[GET]}" onto public org.springframework.cloud.config.environment.Environment org.springframework.cloud.config.server.environment.EnvironmentController.labelled(java.lang.String,java.lang.String,java.lang.String)
 "{[/{name}-{profiles}.json],methods=[GET]}" onto public org.springframework.http.ResponseEntity<java.lang.String> org.springframework.cloud.config.server.environment.EnvironmentController.jsonProperties(java.lang.String,java.lang.String,boolean) throws java.lang.Exception
 "{[/{label}/{name}-{profiles}.yml
 
 //截取部分 还有很多形式
 ```
 访问格式必须要是其指定的格式：
 
 `{name}-{profiles}.yml`中name：配置文件的名字，profiles：环境。
 
 `{label}/{name}-{profiles}.yml`,label:指定git的分支
 
 **值得注意的是**:在用浏览器访问时，修改后缀名比如我们在git上是yml格式，访问后缀该为properties，会将yml自动转换为properties格式。
 
 ## config客户端
 引入依赖：`spring-cloud-config-client`,不需要在启动类上加入注解。
 
 ### 使用
 修改yml配置文件
```
//这里顺序是，1，服务先注册到 eureka上，
//2，然后在去config服务
//3，这里发现没有设置访问那个文件 其根据application.name来查找配置文件
eureka:
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
spring:
  cloud:
    config:
      discovery:
        enabled: true
        service-id: CONFIG
      profile: dev
  application:
    name: order
```

###　注意
这里有很多人发现eureka client设置去掉一样也可以加载，但是我也是非常的纳闷，他是怎么知道config项目的，因为config项目跟此项目是没有关联的，如果没有注册到eureka，是不可能找到的。

后来发现每个eureka客户端启动时，如果没有配置注册中心地址，那么就会使用默认`http://localhost:8761/eureka/`.

## 消息总线（spring cloud bus）
上面做到了，配置的集中化管理，但是其中如果想要实现，配置的实时更新还是不行的。

# zuul网关服务
为什么要使用服务网关：如果有多个服务，那么客户端如何去调用这些服务？一个一个服务去打交道，这是一个非常不现实的。

需要一个角色来充当request的同一入口，zuul在spring cloud 中就充当这一角色。

 