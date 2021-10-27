package com.xin.service;

/*
@data 2021/8/17 0:35
@PACKAGE_NAME com.xin.service
*/

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

public class TestSpiderPost {

    @Test
    public void test() throws IOException {
        //创建httpClient对象；
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //发起get请求
        HttpPost httpPost = new HttpPost("https://www.jd.com/");

        //模拟回车的操作；
        CloseableHttpResponse response = httpClient.execute(httpPost);


        //解析响应，拿到数据；
        if (response.getStatusLine().getStatusCode()==200){
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity, "utf8");
            System.out.println(content);
        }


    }
}
