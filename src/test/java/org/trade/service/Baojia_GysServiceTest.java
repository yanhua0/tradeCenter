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
public class Baojia_GysServiceTest {
   @Autowired
   private Baojia_GysService baojia_gysService;
   @Autowired
   private UsersMapper usersMapper;
    @Test
    public void refuse() {
         int bjid[]={236,237};
         String name="2";
         int id[]={128,129};
         String advice="驳回";
        baojia_gysService.refuse(bjid,advice,name,id);
    }

    @Test
    public void findAllChecklevel1() throws Exception {
        Users u=usersMapper.selectByPrimaryKey(5);
      System.out.println();

    }

    @Test
    public void findAllChecklevel11() {
    }

    @Test
    public void findAllChecklevel2() {
    }

    @Test
    public void refuse1() {
        int njid[]={245};
        String advice="驳回";
        String name="1";
        int id[]={6};
        baojia_gysService.refuse(njid,advice,name,id);
    }
}