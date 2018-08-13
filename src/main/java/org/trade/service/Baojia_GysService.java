package org.trade.service;

import org.trade.entity.Baojia_Gys;

import java.util.List;

public interface Baojia_GysService {
    List<Baojia_Gys> findAllChecklevel1(int id) throws Exception;//第一级审核可以看到的报价信息和供应商信息--id为采购信息id
    List<Baojia_Gys> findAllChecklevel2(int id) throws Exception;//第二级审核可以看到的报价信息和供应商信息--id为采购信息id
    List<Baojia_Gys> findAllChecklevel3(int id) throws Exception;//第三级审核可以看到的报价信息和供应商信息--id为采购信息id
    void refuse(int bjid[],String advice,String name,int id[]);
}
