package org.trade.dao;

import org.trade.entity.Users;

import java.util.List;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
    int updateMoney(Users users);
    int updateMoney2(Users users);
    int updateSome(List<Users> users);
    Users login(Users users);
}