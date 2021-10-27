package com.jsoup;

/*
@data 2021/8/21 21:04
@PACKAGE_NAME com.jsoup
*/

import cn.hutool.core.io.FileUtil;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class JoupFirstTest {
    @Test
    public void testUrl() throws IOException {
        //解析Url
        //第一个参数url,第二个参数超时时间；
        //https://pic.netbian.com/
        Document document = Jsoup.parse(new URL("https://yun.itheima.com"), 1000);
//        Document document = Jsoup.parse(new URL("https://pic.netbian.com/"), 1000);
        System.out.println(document);
        //使用标签选择器，获取title标签中的内容；
        String title = document.getElementsByTag("title").first().text();
        System.out.println(title);
    }
    @Test
    public void testString() throws IOException {
        //使用工具类读取文件,获取字符串；
        String content = FileUtils.readFileToString(new File("C:\\Users\\A\\Desktop\\test.html"), "utf8");
        //解析字符串；
        Document doc = Jsoup.parse(content);
        //获取第一个标题；
        String title = doc.getElementsByTag("title").first().text();
        System.out.println(title);
    }

    @Test
    public void testFile() throws IOException {
        //使用工具类读取文件,获取字符串；
        Document document = Jsoup.parse(new File("C:\\Users\\A\\Desktop\\test.html"), "utf8");

        String title = document.getElementsByTag("title").first().text();
        System.out.println(title);

    }
}
