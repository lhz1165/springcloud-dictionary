package com.whq.springtest_server.entity;

import lombok.Data;

import java.util.List;

/**
 * @author LHZ
 * @date 2023/7/7 10:13
 */
@Data
public class TranslationWordMean {

    //中文翻译
    List<String> translation;

    //翻译结果发音地址
    String tSpeakUrl;

    //基本释义
    List<String> explains;

}
