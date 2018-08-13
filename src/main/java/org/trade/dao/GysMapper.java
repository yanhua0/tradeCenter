package org.trade.dao;

import org.trade.entity.Gys;

public interface GysMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Gys record);

    int insertSelective(Gys record);

    Gys selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Gys record);

    int updateByPrimaryKey(Gys record);
    Gys findByUid(int uid);

}