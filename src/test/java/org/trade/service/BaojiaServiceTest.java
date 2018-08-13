package org.trade.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.trade.entity.Baojia;

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit Spring配置文件的位置
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class BaojiaServiceTest {
    @Autowired
    private BaojiaService baojiaService;
    @Test
    public void updateBaojia() {
        Baojia baojia=baojiaService.findCheckInfo(27,4);

       // System.out.println(baojiaService.updateBaojia(baojia,27));
    }
}