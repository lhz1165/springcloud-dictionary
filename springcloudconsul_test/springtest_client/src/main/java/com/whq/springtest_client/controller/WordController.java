package com.whq.springtest_client.controller;

import com.whq.springtest_client.entity.Result;
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
        return body;
    }



}
