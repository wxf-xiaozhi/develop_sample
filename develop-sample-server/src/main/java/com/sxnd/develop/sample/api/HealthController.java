package com.sxnd.develop.sample.api;


import com.sxnd.develop.framework.entity.ResponseResult;
import com.sxnd.develop.sample.model.param.MyParam;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/health")
public class HealthController {

    @Value("${spring.application.name:skypivot-dispatch-server}")
    String serverName;


//    @Autowired
//    ControllerFeignClient controllerFeignClient;
//
//    @Autowired
//    SkypivotSampleFeignClient sampleFeignClient;


    @Autowired
    private RedissonClient redisson;

    @GetMapping("/sayHello")
    public ResponseResult<String> sayHello(){
        String str = "Yeah, is me ! ["+serverName+"]";
        log.info(str);
//        controllerFeignClient.sayHello();
        return ResponseResult.Success(str);
    }

//    @GetMapping("/saySampleHello")
//    public ResponseResult<String> saySampleHello(){
//        String str = "Yeah, is me ! ["+serverName+"]";
//        log.info(str);
//        sampleFeignClient.sayHello();
//        return ResponseResult.Success(str);
//    }
    @PostMapping("/testValidate")
    public ResponseResult<String> testValidate(@Valid @RequestBody MyParam param){
        String str = "Yeah, is me ! ["+serverName+"]";
        log.info(str);
        return ResponseResult.Success(str);
    }






}
