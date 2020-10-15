计划采用功能迭代的方式，分成如下几个代码工程来分别介绍Spring Cloud的各项核心功能：
1. spring-health-original：包含注册中心、配置中心、服务网关、服务容错机制
2. spring-health-stream：在spring-health-original基础上添加Spring Cloud Stream
3. spring-health-oauth2：在spring-health-stream基础上添加OAuth2服务访问控制
4. spring-health-jwt：在spring-health-stream基础上添加JWT
5. spring-health-testing：包含微服务测试机制
6. spring-health-cdc：包含基于Spring Cloud Contract的面向契约测试机制
