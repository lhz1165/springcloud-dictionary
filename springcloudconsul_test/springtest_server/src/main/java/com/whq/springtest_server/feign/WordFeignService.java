package com.whq.springtest_server.feign;

import com.lhz.entity.Result;
import com.whq.springtest_server.feign.impl.WordFeignServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author LHZ
 * @date 2023/7/7 13:41
 */
@FeignClient(name ="springtest-service" , path ="/word",fallback = WordFeignServiceImpl.class)
public interface WordFeignService {

    @GetMapping("/get")
    public Result<String> getWordByChinese(@RequestParam String word);
}
