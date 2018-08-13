package org.trade.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trade.dao.UsersMapper;
import org.trade.entity.Users;
import org.trade.service.UsersServcie;
@Service
public class UsersServiceImpl implements UsersServcie {
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public Users login(Users users) {
      return  usersMapper.login(users);
    }

    @Override
    public Users findById(int id) {
        return usersMapper.selectByPrimaryKey(id);
    }
}
