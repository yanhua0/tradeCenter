package org.trade.service;

import org.trade.entity.BuyInfo;
import org.trade.entity.BuyInfo_Users;
import org.trade.entity.Users;

import java.util.List;
//采购信息发布审核
public interface BuyInfo_UsersService {
    void checkBuyInfo1(BuyInfo buyInfo, Users users);//第一级审核
    void checkBuyInfo2(BuyInfo buyInfo, Users users);//第二级审核
    List<BuyInfo_Users> findAllCheckPerson(int bid);
}
