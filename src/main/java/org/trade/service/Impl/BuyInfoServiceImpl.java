package org.trade.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trade.dao.BuyInfoMapper;
import org.trade.dao.BuyInfo_UsersMapper;
import org.trade.entity.BuyInfo;
import org.trade.entity.BuyInfo_Users;
import org.trade.entity.Users;
import org.trade.exception.TradeException;
import org.trade.service.BuyInfoService;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class BuyInfoServiceImpl implements BuyInfoService {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BuyInfoMapper buyInfoMapper;

    @Autowired
    private BuyInfo_UsersMapper buyInfo_usersMapper;
//更新订单
    @Override
    public void updateBuyInfo(String bj,String bjp,String lj,String ap,String endTimes,String createTimes,String stimes,String etimes,
                               BuyInfo buyInfo,HttpSession session) throws ParseException {
        //设置double参数
        if (bj.equals("不要求")) {
            buyInfo.setBaojiaPrice(-1);
        }
        if (bj.equals("要求")) {
            if (bjp.equals("")) {
                logger.error("要求缴纳时金额不能为空！");
                throw new TradeException("要求缴纳时金额不能为空！");
            } else {
                buyInfo.setBaojiaPrice(Double.parseDouble(bjp));
            }
        }
        if (lj.equals("不要求")) {
            buyInfo.setAgreePrice(-1);
        }
        if (lj.equals("要求")) {
            if (ap.equals("")) {
                logger.error("要求缴纳时金额不能为空！");
                throw new TradeException("要求缴纳时金额不能为空！");
            } else {
                buyInfo.setAgreePrice(Double.parseDouble(ap));
            }

        }
        Users u = (Users) session.getAttribute("users");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //设置时间格式
        Date e = simpleDateFormat.parse(endTimes);
        buyInfo.setEndTime(e);
        buyInfo.setCreateTime(simpleDateFormat.parse(createTimes));
        buyInfo.setStime(simpleDateFormat.parse(stimes));
        buyInfo.setEtime(simpleDateFormat.parse(etimes));
        buyInfo.setUid(u.getId());
        buyInfo.setCreatePerson(u.getName());
        System.out.println(buyInfo);
        logger.info("buyinfo",buyInfo);
        BuyInfo buyInfos=buyInfoMapper.checkState(buyInfo);//判断是否已经创建了采购订单
        //该用户保存状态有一条数据,保存
        if(buyInfos!=null){
            buyInfo.setId(buyInfos.getId());
            buyInfo.setCheckLevel(-1);
            buyInfo.setSno(buyInfos.getSno());
            buyInfoMapper.updateByPrimaryKey(buyInfo);
        }
        //插入
        else{
            buyInfo.setCheckLevel(-1);
            SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyyMMddHHmmssSSS");
            String s=simpleDateFormat1.format(new Date());
            buyInfo.setSno(s);
            int c=buyInfoMapper.insert(buyInfo);//插入时返回id
            BuyInfo_Users buyInfo_users=new BuyInfo_Users();
            buyInfo_users.setBid(buyInfo.getId());
            buyInfo_users.setUid(u.getId());
            buyInfo_users.setCheckAdvice("--");
            buyInfo_usersMapper.insert(buyInfo_users);
      }

    }

    @Override
    public BuyInfo findById(int id) {
        BuyInfo buyInfo=buyInfoMapper.selectByPrimaryKey(id);
        return buyInfo;
    }

    @Override
    public BuyInfo findByIdAndCheckLevel(int id) {
        return buyInfoMapper.selectByCheckLevel(id);
    }

    //提交采购信息
    @Override
    public int save(BuyInfo buyInfo) {
        BuyInfo buyInfos=buyInfoMapper.checkState(buyInfo);
        buyInfos.setCheckLevel(0);
        return buyInfoMapper.updateByPrimaryKey(buyInfos);
    }
    //发布信息时查看用户之前是否有保存了未提交的采购信息
    @Override
    public BuyInfo checkState(int uid) {
        BuyInfo b=new BuyInfo();
        b.setUid(uid);
        return buyInfoMapper.checkState(b);
    }
//发布的采购信息首页显示
    @Override
    //按照发布时间越早的优先排前面
    public List<BuyInfo> findAll() {
        return buyInfoMapper.findAll(0,7);
    }

    @Override
    public PageInfo<BuyInfo> findByPage(Integer page) {
        PageHelper.startPage(page, 7);
        List<BuyInfo> list=buyInfoMapper.queryAll();
        return new PageInfo<BuyInfo>(list);
    }

    @Override
    public List<BuyInfo> findByCheckLevel0(Users users) {
        List<BuyInfo> buyInfos=buyInfoMapper.findByCheckLevel0();
        for(int i=0;i<buyInfos.size();i++){
             if(buyInfos.get(i).getUsers().getSno()!=users.getSno()){
              buyInfos.remove(i);
                 i=i-1;
            }
        }
        return buyInfos;
    }

    @Override
    public List<BuyInfo> findByCheckLevel1(Users users) {
        List<BuyInfo> buyInfos=buyInfoMapper.findByCheckLevel1();
        for(int i=0;i<buyInfos.size();i++){
            if(buyInfos.get(i).getUsers().getSno()!=users.getSno()){
                buyInfos.remove(i);
                i=i-1;
            }
        }
        return buyInfos;
    }

    @Override
    public  PageInfo<BuyInfo> findAllInEffectiveTime(int page) {
        PageHelper.startPage(page,5);
        Date now=new Date();
        List<BuyInfo> list=buyInfoMapper.findAllInEffectiveTime(now);
        return new PageInfo<BuyInfo>(list);
        }

    @Override
    public List<BuyInfo> selectSuppliers1(Users users) {
        List<BuyInfo> buyInfos=buyInfoMapper.selectSuppliers1();
        for(int i=0;i<buyInfos.size();i++){
            if(buyInfos.get(i).getUsers().getSno()!=users.getSno()){
                buyInfos.remove(i);
                i=i-1;
            }
        }
        return buyInfos;

    }

    @Override
    public List<BuyInfo> selectSuppliers2(Users users) {
        List<BuyInfo> buyInfos=buyInfoMapper.selectSuppliers2();
        for(int i=0;i<buyInfos.size();i++){
            if(buyInfos.get(i).getUsers().getSno()!=users.getSno()){
                buyInfos.remove(i);
                i=i-1;
            }
        }
        return buyInfos;
    }

    @Override
    public List<BuyInfo> selectSuppliers3(Users users) {
        List<BuyInfo> buyInfos=buyInfoMapper.selectSuppliers3();
        for(int i=0;i<buyInfos.size();i++){
            if(buyInfos.get(i).getUsers().getSno()!=users.getSno()){
                buyInfos.remove(i);
                i=i-1;
            }
        }
        return buyInfos;
    }

    @Override
    public List<BuyInfo> find() {
        Date d=new Date();
        return buyInfoMapper.findAllInEffectiveTime(d);
    }

    @Override
    public  boolean checkSno(int id,Users users) {
        BuyInfo buyInfo=buyInfoMapper.selectByPrimaryKey(id);
        if(buyInfo.getUsers().getSno()!=users.getSno()){
            return false;
        }
        return true;
    }

//    @Override
//    public List<BuyInfo> selectSuppliers4(int uid) {
//        return buyInfoMapper.selectSuppliers4(uid);
//    }


}
