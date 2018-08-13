package org.trade.dao;

import org.trade.entity.BuyInfo_Users;

import java.util.List;

public interface BuyInfo_UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BuyInfo_Users record);

    int insertSelective(BuyInfo_Users record);

    BuyInfo_Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BuyInfo_Users record);

    int updateByPrimaryKey(BuyInfo_Users record);
    List<BuyInfo_Users> findAllCheckPerson(int bid);
}