package org.trade.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit Spring配置文件的位置
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class UsersMapperTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UsersMapper usersMapper;

    @Test
    public void selectByPrimaryKey() {
        System.out.println(usersMapper.selectByPrimaryKey(1));

    }

    @Test
    public void selectByPrimaryKey1() {
    }
}