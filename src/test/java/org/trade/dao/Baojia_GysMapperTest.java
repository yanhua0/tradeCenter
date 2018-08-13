package org.trade.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit Spring配置文件的位置
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class Baojia_GysMapperTest {
   @Autowired
   private  Baojia_GysMapper baojia_gysMapper;
    @Test
    public void selectByPrimaryKey() {
        System.out.println(baojia_gysMapper.selectByPrimaryKey(132));
    }

    @Test
    public void findAllChecklevel1() {
        System.out.println(baojia_gysMapper.findAllChecklevel1(12));
    }

    @Test
    public void findByArray() {
        int id[]={6};
        System.out.println(baojia_gysMapper.findByArray(id));
    }
}