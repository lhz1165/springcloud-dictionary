package com.whq.springtest_server.controller;

import com.lhz.entity.Result;
import com.lhz.util.ResultUtil;
import com.whq.springtest_server.utils.TranslateDemo;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/word")
public class WordController {


    @GetMapping("/get")
    public Result<String> getWordByChinese(@RequestParam String word) throws NoSuchAlgorithmException {
        String wordResult = TranslateDemo.getWordResult(word);
        return ResultUtil.success(wordResult);
    }

}
