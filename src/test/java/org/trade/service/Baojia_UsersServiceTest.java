package org.trade.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.trade.dao.UsersMapper;
import org.trade.entity.Users;

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit Spring配置文件的位置
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class Baojia_UsersServiceTest {
    @Autowired
    private Baojia_UsersService baojia_usersService;
    @Autowired
    private UsersMapper usersMapper;
    @Test
    public void check3() {
        int id[]={12,13};
        Users users=usersMapper.selectByPrimaryKey(3);
        int bid=12;
        int bgid[]={11,12};
        baojia_usersService.check3(id,users,bid,bgid);
    }
}