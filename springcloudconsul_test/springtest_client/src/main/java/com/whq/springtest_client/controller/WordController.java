package com.whq.springtest_client.controller;

import com.alibaba.fastjson2.JSON;
import com.lhz.entity.Result;
import com.lhz.util.ResultUtil;
import com.whq.springtest_client.entity.TranslationWordMean;
import com.whq.springtest_server.feign.WordFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
public class WordController {
//    @Autowired
//    RestTemplate restTemplate;

    @Autowired
    WordFeignService wordFeignService;
    @GetMapping("/getWord")
    public Result order(@RequestParam(name = "word") String word) {
        Result<String> body = wordFeignService.getWordByChinese(word);
        if (body.getData() == null) {
            return ResultUtil.error(500, "error");
        }
        String ansJson = body.getData();
        TranslationWordMean translationWordMean = JSON.parseObject(ansJson, TranslationWordMean.class);
        return ResultUtil.success(translationWordMean);
    }



}
