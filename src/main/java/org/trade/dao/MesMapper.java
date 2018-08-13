package org.trade.dao;

import org.trade.entity.Mes;

import java.util.List;

public interface MesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Mes record);

    int insertSelective(Mes record);

    Mes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Mes record);

    int updateByPrimaryKey(Mes record);
    List<Mes> findAll(int id);
    List<Mes> findByState(int id);
    int update(List<Mes> mes);
    int insertListMes(List<Mes> list);
}