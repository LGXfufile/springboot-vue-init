package com.xin;

import com.xin.service.ImgSpider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

@SpringBootTest
class SpringbootVueLearningApplicationTests {

    @Autowired
    ImgSpider imgSpider;

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        System.out.println(dataSource.getConnection());
    }
    @Test
    void teshhhtt() throws IOException {

        imgSpider.spider();

    }

}
