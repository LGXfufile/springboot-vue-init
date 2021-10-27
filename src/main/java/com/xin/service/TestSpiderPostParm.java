package com.xin.service;

/*
@data 2021/8/17 0:35
@PACKAGE_NAME com.xin.service
*/

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestSpiderPostParm {

    @Test
    public void test() throws IOException {
        //创建httpClient对象；
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //发起get请求
        HttpPost httpPost = new HttpPost("https://cn.pornhub.com/video/search");

        //声明List集合，封装数据
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new BasicNameValuePair("search", "girl"));

        //创建表单的entity对象,第一个参数是封装好的数据，第二个参数是编码；
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(pairs,"UTF8");

        //设置表单中的Entity对象到post请求中；
        httpPost.setEntity(urlEncodedFormEntity);

        //模拟回车的操作；
        CloseableHttpResponse response = httpClient.execute(httpPost);

        //解析响应，拿到数据；
        if (response.getStatusLine().getStatusCode()==200){
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity, "utf8");
            System.out.println("content:"+content);
        }


    }
}
