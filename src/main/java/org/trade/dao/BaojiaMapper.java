package org.trade.dao;

import org.apache.ibatis.annotations.Param;
import org.trade.entity.Baojia;

import java.util.List;

public interface BaojiaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Baojia record);

    int insertSelective(Baojia record);

    Baojia selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Baojia record);

    int updateByPrimaryKey(Baojia record);
    List<Baojia> checkInfo(int id[]);
    Baojia findByCheckInfo(@Param("bid") int bid,@Param("uid") int uid);//多表查询获取用户的一条报价信息
    int updateSetCheckLevel1(int id[]);//第一级审核完成
    int updateSetCheckLevel2(int id[]);//第二级审核完成
    int updateSetCheckLevel3(int id[]);//第三级审核完成

    int deleteByIdArray(int id[]);//驳回,删除供应商报价信息//传入报价信息的id数组级联操作
}