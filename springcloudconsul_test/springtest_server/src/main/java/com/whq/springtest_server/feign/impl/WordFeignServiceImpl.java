package com.whq.springtest_server.feign.impl;

import com.lhz.entity.Result;
import com.lhz.util.ResultUtil;
import com.whq.springtest_server.feign.WordFeignService;

/**
 * @author LHZ
 * @date 2023/7/7 13:49
 */
public class WordFeignServiceImpl implements WordFeignService {
    @Override
    public Result<String> getWordByChinese(String word) {
        Result<String> res = ResultUtil.error(500, "服务错误");
        res.setData("error");
        return res;
    }
}
