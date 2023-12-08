package com.sxnd.develop.sample.feign;

import com.ifyou.skypivot.framework.entity.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * rpc接口
 *
 * @author
 * @version 1.0
 * @date 2022-07-21
 */
@FeignClient("sample")
public interface SampleFeignClient {


    @GetMapping("/health/sayHello")
    ResponseResult<String> sayHello();




}
