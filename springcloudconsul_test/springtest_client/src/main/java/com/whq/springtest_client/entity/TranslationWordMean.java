package com.whq.springtest_client.entity;


import java.util.List;

/**
 * @author LHZ
 * @date 2023/7/7 10:13
 */
public class TranslationWordMean {

    //中文翻译
    List<String> translation;

    //翻译结果发音地址
    String TSpeakUrl;

    //基本释义
    List<String> explains;

    public List<String> getTranslation() {
        return translation;
    }

    public void setTranslation(List<String> translation) {
        this.translation = translation;
    }

    public String getTSpeakUrl() {
        return TSpeakUrl;
    }

    public void setTSpeakUrl(String TSpeakUrl) {
        this.TSpeakUrl = TSpeakUrl;
    }

    public List<String> getExplains() {
        return explains;
    }

    public void setExplains(List<String> explains) {
        this.explains = explains;
    }
}
