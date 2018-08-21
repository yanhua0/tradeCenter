package org.trade.service;

import com.github.pagehelper.PageInfo;
import org.trade.entity.BuyInfo;
import org.trade.entity.Users;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

public interface BuyInfoService {

    void updateBuyInfo(String bj,String bjp,String lj,String ap,String endTimes,String createTimes,String stimes,String etimes,
                       BuyInfo buyInfo,HttpSession session) throws ParseException;
    BuyInfo findById(int id);
    BuyInfo  findByIdAndCheckLevel(int id);
    int save(BuyInfo buyInfo);
    BuyInfo checkState(int uid);
    List<BuyInfo> findAll();
    PageInfo<BuyInfo> findByPage(Integer page);
    List<BuyInfo> findByCheckLevel0(Users users);
    List<BuyInfo> findByCheckLevel1(Users users);
    PageInfo<BuyInfo> findAllInEffectiveTime(int page);//查询所有有效时间的采购信息，用于阳光用户报价
    List<BuyInfo> selectSuppliers1(Users users);
    List<BuyInfo> selectSuppliers2(Users users);
    List<BuyInfo> selectSuppliers3(Users users);
    List<BuyInfo> find();
    boolean checkSno(int id,Users users);//是否是同一家公司
//    List<BuyInfo> selectSuppliers4(int uid); //根据用户id查询用户中标的信息
}
