package org.trade.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.trade.dao.BuyInfoMapper;
import org.trade.dao.UsersMapper;
import org.trade.entity.BuyInfo;
import org.trade.entity.Users;

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit Spring配置文件的位置
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class BuyInfoServiceTest {
   @Autowired
   private BuyInfoService buyInfoService;
    @Autowired
    private BuyInfoMapper buyInfoMapper;
    @Autowired
    private UsersMapper usersMapper;
    @Test
    public void create(){
   // buyInfoService.findAllInEffectiveTime(1);
      //  System.out.println(buyInfoService.findAllInEffectiveTime(1).getList().size());
    }

    @Test
    public void create1() {
        BuyInfo b=buyInfoMapper.selectByPrimaryKey(1);

        System.out.println(b.getUsers().getSno());
       // buyInfoMapper.updateByPrimaryKey(b);
    }

    @Test
    public void find() {
        Users u=usersMapper.selectByPrimaryKey(5);
        System.out.println(buyInfoService.selectSuppliers1(u).get(0).getId());

    }
}