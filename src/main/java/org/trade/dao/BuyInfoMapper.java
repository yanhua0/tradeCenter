package org.trade.dao;

import org.apache.ibatis.annotations.Param;
import org.trade.entity.BuyInfo;

import java.util.Date;
import java.util.List;

public interface BuyInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BuyInfo record);

    int insertSelective(BuyInfo record);

    BuyInfo selectByPrimaryKey(Integer id);
    BuyInfo selectByCheckLevel(Integer id);

    int updateByPrimaryKeySelective(BuyInfo record);

    int updateByPrimaryKey(BuyInfo record);
    BuyInfo checkState(BuyInfo buyInfo);
    BuyInfo findByUid(int id);
    List<BuyInfo> findAll(@Param("offset") int offset, @Param("limit") int limit);//分页查询
    List<BuyInfo> queryAll();
    List<BuyInfo> findAllInEffectiveTime(Date nowTime);//查询所有有效时间的采购信息，用于阳光用户报价

    List<BuyInfo> findByCheckLevel0();//第一级审核的采购信息
    List<BuyInfo> findByCheckLevel1();//第二级审核的采购信息

    List<BuyInfo> selectSuppliers1();//进行第一级筛选供应商操作,查询有供应商报价的采购信息
    List<BuyInfo> selectSuppliers2();//进行第二级筛选供应商操作,查询有供应商报价的采购信息
    List<BuyInfo> selectSuppliers3();//进行第三级筛选供应商操作,查询有供应商报价的采购信息

    List<BuyInfo> selectSuppliers4(int uid); //根据用户id查询用户中标的信息
}