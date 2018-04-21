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