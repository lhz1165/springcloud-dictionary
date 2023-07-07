package com.whq.springtest_client.controller;

import com.alibaba.fastjson2.JSON;
import com.whq.springtest_client.entity.Result;
import com.whq.springtest_client.entity.TranslationWordMean;
import com.whq.springtest_client.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/user")
public class WordController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/getWord")
    public Result order(@RequestParam(name = "word") String word) {
        Result body = restTemplate.getForObject("http://springtest-service/word/get?word={1}",Result.class,word);
        String ansJson = (String) body.getData();
        TranslationWordMean translationWordMean = JSON.parseObject(ansJson, TranslationWordMean.class);
        return ResultUtil.success(translationWordMean);
    }



}
