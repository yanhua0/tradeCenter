package org.trade.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.trade.entity.Mes;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit Spring配置文件的位置
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class MesMapperTest {
@Autowired
private MesMapper mesMapper;
    @Test
    public void update() {
System.out.println(mesMapper.findByState(1));
    mesMapper.update(mesMapper.findByState(1));
    }

    @Test
    public void insertListMes() {
        Mes n=new Mes("231",2,"12",0);
        Mes n2=new Mes("2312",2,"12",0);
        List<Mes> s=new ArrayList<Mes>();
        s.add(n);
        s.add(n2);
        mesMapper.insertListMes(s);
    }
}