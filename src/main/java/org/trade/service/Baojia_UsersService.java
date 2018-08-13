package org.trade.service;

import org.trade.entity.Baojia_Users;
import org.trade.entity.Users;

import java.util.List;

//筛选供应商审核
public interface Baojia_UsersService {
    void check1(int id[], String advice, Users users,int bid);
    void check2(int id[], String advice, Users users,int bid);
    void check3(int id[],Users users,int bid,int bgid[]);
    List<Baojia_Users> findAllCheckPerson(int bid);//审核人员查询
}
