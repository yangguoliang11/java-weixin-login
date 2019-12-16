package com.guoliang.weixin.utils;


import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.Closeable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

    public static String doGet(String url, Map<String,String> param){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String resultString = "";

        CloseableHttpResponse response = null;

        try{
            URIBuilder builder = new URIBuilder(url);
            if(param != null){
                for (String key:param.keySet()){
                    builder.addParameter(key,param.get(key));
                }
            }
            URI uri = builder.build();

            HttpGet httpGet = new HttpGet();

            response = httpClient.execute(httpGet);

            if(response.getStatusLine().getStatusCode() == 200){
                resultString = EntityUtils.toString(response.getEntity(),"UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(response!=null){
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;
    }

    public static String doGet(String url){
        return doGet(url,null);
    }

    public static String doPost(String url, Map<String,String> param){

        CloseableHttpClient httpClient = HttpClients.createDefault();

        CloseableHttpResponse response = null;

        String resultString = "";

        try {
            HttpPost httpPost = new HttpPost(url);
            if(param!=null){
                List<NameValuePair> paraList = new ArrayList<>();
                for(String key: param.keySet()){
                    paraList.add(new BasicNameValuePair(key,param.get(key)));

                }
                //模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paraList);
                httpPost.setEntity(entity);
            }
            //模拟http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(),"utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resultString;

    }

    public static String doPost(String url){
        return doPost(url,null);
    }
}