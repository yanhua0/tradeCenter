package org.trade.dao;

import org.trade.entity.Baojia_Users;

import java.util.List;

public interface Baojia_UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Baojia_Users record);

    int insertSelective(Baojia_Users record);

    Baojia_Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Baojia_Users record);

    int updateByPrimaryKey(Baojia_Users record);
    List<Baojia_Users> findByBid(int bid);
}