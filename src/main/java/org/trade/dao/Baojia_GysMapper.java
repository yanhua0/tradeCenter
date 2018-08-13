package org.trade.dao;

import org.trade.entity.Baojia_Gys;

import java.util.List;

public interface Baojia_GysMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Baojia_Gys record);

    int insertSelective(Baojia_Gys record);

    Baojia_Gys selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Baojia_Gys record);

    int updateByPrimaryKey(Baojia_Gys record);
    List<Baojia_Gys> findAllChecklevel1(int id);//第一级审核可以看到的报价信息和供应商信息--id为采购信息id
    List<Baojia_Gys> findAllChecklevel2(int id);//第二级审核可以看到的报价信息和供应商信息--id为采购信息id
    List<Baojia_Gys> findAllChecklevel3(int id);//第三级审核可以看到的报价信息和供应商信息--id为采购信息id
    List<Baojia_Gys> findByArray(int id[]);
}