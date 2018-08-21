package org.trade.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trade.dao.*;
import org.trade.entity.*;
import org.trade.exception.TradeException;
import org.trade.service.Baojia_UsersService;

import java.util.ArrayList;
import java.util.List;

@Service
public class Baojia_UsersServiceImpl implements Baojia_UsersService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private Baojia_GysMapper baojia_gysMapper;
    @Autowired
    private BaojiaMapper baojiaMapper;
    @Autowired
    private Baojia_UsersMapper baojia_usersMapper;
    @Autowired
    private MesMapper mesMapper;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private BuyInfoMapper buyInfoMapper;

    //第一集筛选供应商--点击审核通过按钮
    @Override
    public void check1(int id[], String advice, Users users, int bid) {
        //修改审核通过报价信息的检查等级
        try {//判断每条数据是不是检查等级是不是为0
            List<Baojia> list = baojiaMapper.checkInfo(id);
            BuyInfo b1=buyInfoMapper.selectByPrimaryKey(bid);
            b1.setCheckLevel(3);//进入筛选操作,关闭报价
            buyInfoMapper.updateByPrimaryKey(b1);
            for (Baojia b : list) {
                if (b.getCheckLevel() != 0) {
                    throw new TradeException("非法操作!");
                }
            }
            baojiaMapper.updateSetCheckLevel1(id);
            //插入审核意见-审核人
            insertBaojia_users(advice, users, bid);
        } catch (TradeException e) {
            throw e;
        } catch (Exception e) {
            throw new TradeException(e.getMessage());
        }

        //审核没有通过的报价信息仍然保留。
    }

    @Override
    public void check2(int[] id, String advice, Users users, int bid) {
        //修改审核通过报价信息的检查等级
        try {//判断每条数据是不是检查等级是不是为0
            List<Baojia> list = baojiaMapper.checkInfo(id);
            for (Baojia b : list) {
                if (b.getCheckLevel() != 1) {
                    throw new TradeException("非法操作!");
                }
            }
            baojiaMapper.updateSetCheckLevel2(id);
            //插入审核意见-审核人-采购信息id
            insertBaojia_users(advice, users, bid);
        } catch (TradeException e) {
            throw e;
        } catch (Exception e) {
            throw new TradeException(e.getMessage());
        }

        //审核没有通过的报价信息仍然保留。
    }

    @Override
    public void check3(int[] id, Users users, int bid, int bgid[]) {
        //修改审核通过报价信息的检查等级
        try {
            List<Baojia> list1 = baojiaMapper.checkInfo(id);
            for (Baojia b : list1) {
                if (b.getCheckLevel() != 2) {
                    throw new TradeException("非法操作!");
                }
            }

            baojiaMapper.updateSetCheckLevel3(id);

            //给成功报价的供应商发布信息
            List<Baojia_Gys> bg = baojia_gysMapper.findByArray(bgid);
            if (bg.size() > 0) {
                List<Mes> list = new ArrayList<Mes>();
                List<Users> userlist = new ArrayList<Users>();
                Mes[] mes = new Mes[bg.size()];
                Users[] us = new Users[bg.size()];
                double money = bg.get(0).getBaojia().getBuyInfo().getUsers().getMoney();
                double freeze2 = bg.get(0).getBaojia().getBuyInfo().getUsers().getFreezeMoney2();
                double f2=freeze2;

                for (int i = 0; i < bg.size(); i++) {

                    //归还报价保证金
                    double price = 0;
                    if (bg.get(i).getBaojia().getBuyInfo().getBaojiaPrice() != -1) {
                        price = bg.get(i).getBaojia().getNumber() * bg.get(i).getBaojia().getBuyInfo().getBaojiaPrice();
                    }
                    double pre = bg.get(i).getGys().getUsers().getMoney() + price;
                    double freeze = bg.get(i).getGys().getUsers().getFreezeMoney() - price;
                    us[i] = new Users();
                    us[i].setFreezeMoney(freeze);
                    us[i].setMoney(pre);
                    us[i].setId(bg.get(i).getGys().getUid());
                    userlist.add(us[i]);
                    double price1 = 0;

                  //计算履约保证金
                    if (bg.get(i).getBaojia().getBuyInfo().getAgreePrice() != -1) {
                        price1 = bg.get(i).getBaojia().getBuyInfo().getAgreePrice() * bg.get(i).getBaojia().getNumber();
                    }
                    money = money - price1;
                    freeze2 = freeze2 + price1;
                    //发送消息
                    mes[i] = new Mes();
                    mes[i].setReceieveid(bg.get(i).getGys().getUid());
                    mes[i].setState(0);
                    mes[i].setSendname(users.getName());
                    double a=freeze2-f2;
                    mes[i].setMessage("你报价的订单号为<span>" + bg.get(i).getBaojia().getBuyInfo().getSno() + "</span>已经中标!!系统已经自动退还冻结的报价保证金<span>"+a+"</span>元");
                    list.add(mes[i]);

                }

                usersMapper.updateSome(userlist);
                mesMapper.insertListMes(list);
                //系统自动解冻报价保证金，冻结履约保证金
                Mes m = new Mes();
                m.setReceieveid(bg.get(0).getBaojia().getBuyInfo().getUid());
                m.setState(0);
                m.setSendname(users.getName());
                m.setMessage("你的订单号为<span>" + bg.get(0).getBaojia().getBuyInfo().getSno() + "</span>已经有供应商报价成功!!系统已经自动缴纳履约保证金<span>"+freeze2+"</span>元");
                mesMapper.insert(m);//给创建采购需求的人发送信息
               //冻结履约保证金
                Users u = new Users();
                u.setId(bg.get(0).getBaojia().getBuyInfo().getUid());
                u.setMoney(money);
                u.setFreezeMoney2(freeze2);
                usersMapper.updateMoney2(u);
            }
        } catch (TradeException e) {
            throw e;
        } catch (Exception e1) {
            //所有编译异常转为运行异常
            throw new TradeException(e1.getMessage());
        }


    }

    @Override
    public List<Baojia_Users> findAllCheckPerson(int bid) {
        return baojia_usersMapper.findByBid(bid);
    }

    private void insertBaojia_users(String advice, Users users, int bid) {
        Baojia_Users bju = new Baojia_Users();
        bju.setBid(bid);
        bju.setCheckAdvice(advice);
        bju.setUid(users.getId());
        baojia_usersMapper.insert(bju);
    }
}
