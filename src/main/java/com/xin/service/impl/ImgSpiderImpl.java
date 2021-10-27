package com.xin.service.impl;

/*
@data 2021/8/22 19:03
@PACKAGE_NAME com.xin.service.impl
*/

import com.xin.service.ImgSpider;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ImgSpiderImpl implements ImgSpider {

    //从0秒开始，每五秒执行一次；
//    @Scheduled(cron = "0/5 * * * * *")
    @Override
    public void spider() throws IOException {
        String url = "https://pic.netbian.com/";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(1000)
                .setConnectionRequestTimeout(500)
                .setSocketTimeout(10 * 1000)
                .build();
        httpPost.setConfig(requestConfig);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        System.out.println(response);
    }

    @Test
    public void test() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //发起get请求
        HttpGet httpGet = new HttpGet("https://www.zhihu.com/");

        //模拟回车的操作；
        CloseableHttpResponse response = httpClient.execute(httpGet);

        //解析响应，拿到数据；
        if (response.getStatusLine().getStatusCode()==200){
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity, "utf8");
            System.out.println(content);
        }
    }
}
