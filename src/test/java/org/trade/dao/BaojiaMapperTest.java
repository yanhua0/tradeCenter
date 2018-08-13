package org.trade.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit Spring配置文件的位置
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class BaojiaMapperTest {
    @Autowired
    private BaojiaMapper baojiaMapper;
    @Test
    public void findByCheckInfo() {

        System.out.println(baojiaMapper.findByCheckInfo(27,1));
    }

    @Test
    public void updateSetCheckLevel() {
        int id[]={35,36};
       // baojiaMapper.updateSetCheckLevel(id);
    }
}