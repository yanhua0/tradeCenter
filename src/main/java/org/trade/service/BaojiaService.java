package org.trade.service;

import org.trade.dto.PayResult;
import org.trade.entity.Baojia;
import org.trade.entity.BuyInfo;

public interface BaojiaService {
   Baojia findById(int id);
   Baojia findCheckInfo(int bid,int uid);
   void insert(Baojia baojia,int bid,int uid,String transportPrices,String prices);
   PayResult<String> updateBaojia(Baojia baojia,int bid,int uid,String transportPrice,String unitPrice) throws Exception;
   PayResult<BuyInfo> payMoney(int id,int uid,int number);


}
