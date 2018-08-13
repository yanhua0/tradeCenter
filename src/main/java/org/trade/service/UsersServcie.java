package org.trade.service;

import org.trade.entity.Users;

public interface UsersServcie {
   Users login(Users users);
   Users findById(int id);

}
