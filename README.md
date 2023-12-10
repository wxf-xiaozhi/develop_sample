# sample服务

此项目为微服务拆分基础样板工程，新建微服务可从本工程拉区代码，基于此工程进行二开
启动添加参数 -DNACOS_ADDR=127.0.0.1 -DNAMESPACE=local -Dcustom.log.level=debug


#开源框架版本
java 1.8

spring-boot 2.7.16

spring-cloud 2021.0.8

spring-cloud-alibaba 2021.0.4.0

nacos 2.0.3

#样板工程目前包含功能

####基于pgsql的JPA的增删改查

####基于redission框架的redisAPI
基于分布式锁功能考虑，故用此API,目前支持 string hash zset三种数据类型操作，set list后续逐渐丰富,支持自定义解码器。
支持基于redission的分布式锁（demo在RedissionLockTest）

####基于rabbitMQ的生产和监听，标准的RabbitMQ配置，包含（发布订阅、消息路由、主题、RPC）模式

####打开feign的调用全日志打印，在联调时方便排查问题，生产环境关闭

####基于feign的远程调用，自定义feign的编解码、拦截器

####基于feign远程调用实现了tranceId传递，可以基于tranceId做链路追踪，使服务之间请求调用时根据tranceID关联，方便排查问题

####实现了基于header的自定义调用下游服务实例的功能（springboot多种版本实现），使请求能路由到自己启动的服务，默认走系统默认的服务，不在需要远程debug,提高开发联调效率，只能在工作站环境使用、其他环境由于网络限制，无法使用此功能

####实现了基于maven插件（flatten-maven-plugin）对于模块版本的统一管理，减少修改版本号所要修改的文件

# 后续计划
##集成事件驱动开源框架 spring-cloud-stream
    spring-cloud-stream（支持多种中间件 rabbitMQ kafka等）
