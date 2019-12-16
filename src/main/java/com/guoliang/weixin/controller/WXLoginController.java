package com.guoliang.weixin.controller;

import com.guoliang.weixin.utils.HttpClientUtil;
import com.guoliang.weixin.utils.JsonFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WXLoginController{

    @RequestMapping("/wxLogin")

    public JsonFormat wxLogin(String code){
        System.out.println("wxlogin -code :"+ code);

        String url = "https://api.weixin.qq.com/sns/jscode2session";

        Map<String,String> param = new HashMap<>();
        param.put("appid", "wxa2049f5aead89372");
        param.put("secret", "3a62d9b55028c644bacdd8412fada021");
        param.put("js_code",code);
        param.put("grant_type","authorization_code");

        String wxResult = HttpClientUtil.doGet(url,param);
        System.out.println(wxResult);

        return JsonFormat.ok(wxResult);
    }


}