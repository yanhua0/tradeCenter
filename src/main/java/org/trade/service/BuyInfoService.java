package org.trade.service;

import com.github.pagehelper.PageInfo;
import org.trade.entity.BuyInfo;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

public interface BuyInfoService {

    void updateBuyInfo(String bj,String bjp,String lj,String ap,String endTimes,String createTimes,String stimes,String etimes,
                       BuyInfo buyInfo,HttpSession session) throws ParseException;
    BuyInfo findById(int id);

    int save(BuyInfo buyInfo);
    BuyInfo checkState(int uid);
    List<BuyInfo> findAll();
    PageInfo<BuyInfo> findByPage(Integer page);
    List<BuyInfo> findByCheckLevel0();
    List<BuyInfo> findByCheckLevel1();
    List<BuyInfo> findAllInEffectiveTime();//查询所有有效时间的采购信息，用于阳光用户报价
    List<BuyInfo> selectSuppliers1();
    List<BuyInfo> selectSuppliers2();
    List<BuyInfo> selectSuppliers3();
    List<BuyInfo> find();
//    List<BuyInfo> selectSuppliers4(int uid); //根据用户id查询用户中标的信息
}
