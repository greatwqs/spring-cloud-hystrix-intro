# hystrix 演示
- student-springboot 基础服务提供者
- school-springboot 调用 student 服务
- 通过postman调用school微服务
http://localhost:8088/getSchoolDetails1/abcschool
- Hystrix 标注：
```
@HystrixCommand(fallbackMethod = "callStudentService_Fallback",
   commandProperties = {
      @HystrixProperty(name = "circuitBreaker.forceClosed", value = "true"),
      @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "4000")
   },
   threadPoolKey = "studentServiceThreadPool",
   threadPoolProperties = {
      @HystrixProperty(name = "coreSize", value = "5"),
      @HystrixProperty(name = "maxQueueSize", value = "5")
   })
```
- hystrix stream:
http://localhost:8088/hystrix.stream
- hystrix dashboard
http://localhost:8088/hystrix
<img width="675" alt="dashbord" src="https://user-images.githubusercontent.com/2442089/54838917-b39b0600-4d04-11e9-9e86-17bd28d38ad7.png">
http://localhost:8088/hystrix/monitor?stream=http%3A%2F%2Flocalhost%3A8088%2Fhystrix.stream
