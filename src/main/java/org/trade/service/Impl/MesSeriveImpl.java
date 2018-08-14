package org.trade.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trade.dao.MesMapper;
import org.trade.entity.Mes;
import org.trade.service.MesService;

import java.util.List;

@Service
public class MesSeriveImpl implements MesService {
    @Autowired
    private MesMapper mesMapper;
    @Override
    public PageInfo<Mes> findByPage(int page,int id) {
        PageHelper.startPage(page, 8);
        List<Mes> list=mesMapper.findAll(id);//分页的下一行一定要是分页的数据
        List<Mes> mes=mesMapper.findByState(id);//未读信息
        if(list.size()>0){
            if(mes.size()>0){
                mesMapper.update(mes);
            }

        }
        return new PageInfo<Mes>(list);

    }

    @Override
    public List<Mes> findByState(int id) {
        return mesMapper.findByState(id);
    }

    @Override
    public void delete(int id) {
        if(mesMapper.selectByPrimaryKey(id)!=null){
            mesMapper.deleteByPrimaryKey(id);
        }
    }
}
