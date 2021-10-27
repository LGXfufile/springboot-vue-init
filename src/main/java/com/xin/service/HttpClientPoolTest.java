package com.xin.service;

/*
@data 2021/8/21 20:06
@PACKAGE_NAME com.xin.service
*/

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

public class HttpClientPoolTest {

    @Test
    public void test() throws IOException {

        //创建连接池管理器
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

        //设置最大连接数；
        cm.setMaxTotal(100);
        //使用连接池管理者发起请求；
        doGet(cm);
        doGet(cm);
    }

    private void doGet(PoolingHttpClientConnectionManager cm) throws IOException {
        //不是每次都创建连接池对象，而是从连接池中获取对象；
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        HttpGet httpGet = new HttpGet("https://yun.itheima.com");

        //配置请求信息
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(1000)
                .setConnectionRequestTimeout(500)
                .setSocketTimeout(10 * 1000)
                .build();
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        if (response.getStatusLine().getStatusCode()==200){
            String content = EntityUtils.toString(response.getEntity(), "utf8");
            System.out.println(content.length());
        }
        if (response!=null){
            response.close();
        }

    }
}
