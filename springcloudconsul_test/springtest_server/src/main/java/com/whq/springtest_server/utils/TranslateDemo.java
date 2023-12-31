package com.whq.springtest_server.utils;


import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.whq.springtest_server.entity.TranslationWordMean;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 网易有道智云翻译服务api调用demo
 * api接口: https://openapi.youdao.com/api
 */
public class TranslateDemo {

    private static final String APP_KEY = "45aae62601561f5b";     // 您的应用ID
    private static final String APP_SECRET = "ROwaG859mwBf6AXOLzq0H52XGU5lDQDa";  // 您的应用密钥

    public static void main(String[] args) throws NoSuchAlgorithmException {
        // 添加请求参数
        Map<String, String[]> params = createRequestParams("hello");
        // 添加鉴权相关参数
        AuthV3Util.addAuthParams(APP_KEY, APP_SECRET, params);
        // 请求api服务
        byte[] result = HttpUtil.doPost("https://openapi.youdao.com/api", null, params, "application/json");
        // 打印返回结果
        if (result != null) {
            System.out.println(new String(result, StandardCharsets.UTF_8));
        }
        String s = new String(result, StandardCharsets.UTF_8);
        JSONObject jsonObject = JSON.parseObject(s);
        Boolean isWord = (Boolean)jsonObject.get("isWord");

        System.out.println((String) jsonObject.get("tSpeakUrl"));
        System.exit(1);
    }

    public static String getWordResult(String word) throws NoSuchAlgorithmException {
        // 添加请求参数
        Map<String, String[]> params = createRequestParams(word);
        // 添加鉴权相关参数
        AuthV3Util.addAuthParams(APP_KEY, APP_SECRET, params);
        byte[] result = HttpUtil.doPost("https://openapi.youdao.com/api", null, params, "application/json");
        // 打印返回结果

        String s = new String(result, StandardCharsets.UTF_8);
        JSONObject jsonObject = JSON.parseObject(s);
        Boolean isWord = (Boolean)jsonObject.get("isWord");
        if (isWord) {
            TranslationWordMean ans = new TranslationWordMean();
            List<String> translation = (List<String>) jsonObject.get("translation");
            JSONObject jsonObject1 = (JSONObject) jsonObject.get("basic");
            List<String> explains = (List<String>) jsonObject1.get("explains");
            String tSpeakUrl = (String) jsonObject.get("tSpeakUrl");

            ans.setExplains(explains);
            ans.setTSpeakUrl(tSpeakUrl);
            ans.setTranslation(translation);
            return JSON.toJSONString(ans);
        }else {
            return "not word";
        }
    }

    private static Map<String, String[]> createRequestParams(String word) {
        /*
         * note: 将下列变量替换为需要请求的参数
         * 取值参考文档: https://ai.youdao.com/DOCSIRMA/html/%E8%87%AA%E7%84%B6%E8%AF%AD%E8%A8%80%E7%BF%BB%E8%AF%91/API%E6%96%87%E6%A1%A3/%E6%96%87%E6%9C%AC%E7%BF%BB%E8%AF%91%E6%9C%8D%E5%8A%A1/%E6%96%87%E6%9C%AC%E7%BF%BB%E8%AF%91%E6%9C%8D%E5%8A%A1-API%E6%96%87%E6%A1%A3.html
         */
        String q = word;
        String from = "auto";
        String to = "auto";
//        String vocabId = "您的用户词表ID";

        return new HashMap<String, String[]>() {{
            put("q", new String[]{q});
            put("from", new String[]{from});
            put("to", new String[]{to});
        }};
    }
}
