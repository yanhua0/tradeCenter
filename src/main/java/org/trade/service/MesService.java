package org.trade.service;

import com.github.pagehelper.PageInfo;
import org.trade.entity.Mes;

import java.util.List;


public interface MesService {
    PageInfo<Mes> findByPage(int page, int id);
    List<Mes> findByState(int id);
    void delete(int id);
}
