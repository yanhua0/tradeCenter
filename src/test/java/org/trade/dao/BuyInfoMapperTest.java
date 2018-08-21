package org.trade.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.trade.entity.BuyInfo;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit Spring配置文件的位置
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class BuyInfoMapperTest {
    @Autowired
    private BuyInfoMapper buyInfoMapper;
    @Test
    public void selectByPrimaryKey() {
        buyInfoMapper.selectByPrimaryKey(1);
    }

    @Test
    public void insert() {

        BuyInfo b=buyInfoMapper.selectByPrimaryKey(1);
        b.setId(null);
        buyInfoMapper.insert(b);
    }

    @Test
    public void updateByPrimaryKeySelective() {
        System.out.println(buyInfoMapper.findAllInEffectiveTime(new Date()).size());
    }

    @Test
    public void checkState() {
        BuyInfo b=buyInfoMapper.selectByPrimaryKey(1);
        b.setId(1);
        buyInfoMapper.checkState(b);
    }

    @Test
    public void updateByPrimaryKey() {
        BuyInfo b=buyInfoMapper.selectByPrimaryKey(10);
        b.setPayType("111111");
        buyInfoMapper.updateByPrimaryKey(b);
    }

    @Test
    public void findAllInEffectiveTime() {
        Date date=new Date();
        System.out.println(buyInfoMapper.findAllInEffectiveTime(date).size());
    }

    @Test
    public void selectSuppliers1() {

    }
}