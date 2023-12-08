package com.sxnd.develop.sample.api;


import com.ifyou.skypivot.controller.feign.ControllerFeignClient;
import com.ifyou.skypivot.sample.feign.SkypivotSampleFeignClient;
import com.sxnd.develop.framework.entity.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/health")
public class HealthController {

    @Value("${spring.application.name:skypivot-dispatch-server}")
    String serverName;


    @Autowired
    ControllerFeignClient controllerFeignClient;

    @Autowired
    SkypivotSampleFeignClient sampleFeignClient;


    @Autowired
    private RedissonClient redisson;

    @GetMapping("/sayHello")
    public ResponseResult<String> sayHello(){
        String str = "Yeah, is me ! ["+serverName+"]";
        log.info(str);
        controllerFeignClient.sayHello();
        return ResponseResult.Success(str);
    }

    @GetMapping("/saySampleHello")
    public ResponseResult<String> saySampleHello(){
        String str = "Yeah, is me ! ["+serverName+"]";
        log.info(str);
        sampleFeignClient.sayHello();
        return ResponseResult.Success(str);
    }







}
