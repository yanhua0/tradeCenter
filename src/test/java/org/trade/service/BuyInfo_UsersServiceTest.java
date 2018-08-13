package org.trade.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit Spring配置文件的位置
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class BuyInfo_UsersServiceTest {
@Autowired
private BuyInfo_UsersService buyInfo_usersService;
    @Test
    public void findAllCheckPerson() {
    System.out.println(buyInfo_usersService.findAllCheckPerson(21).get(0).getUid());
    }
}