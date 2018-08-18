package org.trade.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trade.dao.*;
import org.trade.dto.PayResult;
import org.trade.entity.*;
import org.trade.enums.TradeEnum;
import org.trade.exception.TradeException;
import org.trade.service.BaojiaService;

import org.trade.util.Base64;
import org.trade.util.DoubleToString;

import java.util.Date;

@Service
public class BaojiaServiceImpl implements BaojiaService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BaojiaMapper baojiaMapper;
    @Autowired
    private BuyInfoMapper buyInfoMapper;
    @Autowired
    private UsersMapper usersMapper;
    @Autowired
    private GysMapper gysMapper;
    @Autowired
    private Baojia_GysMapper baojia_gysMapper;

    @Override
    public Baojia findById(int id) {
        return baojiaMapper.selectByPrimaryKey(id);
    }



    @Override
    public Baojia findCheckInfo(int bid, int uid) {
        Baojia baojia=baojiaMapper.findByCheckInfo(bid, uid);
        if(baojia!=null){
            baojia.setUnitPrice(Base64.getFromBase64(baojia.getUnitPrice()));
            baojia.setTransportPrice(Base64.getFromBase64(baojia.getTransportPrice()));
        }
        return baojia;
    }


    @Override
    public void insert(Baojia baojia, int bid, int uid,String transportPrices,String prices) {
        Baojia baojia1 = baojiaMapper.findByCheckInfo(bid, uid);
        try {
            BuyInfo buyInfo = buyInfoMapper.selectByPrimaryKey(bid);
            double unitPrice=Double.parseDouble(prices);
            double tran=Double.parseDouble(transportPrices);
            double price1 = (unitPrice+ tran) * baojia.getNumber();
            //加密报价价格
            String s = DoubleToString.dToS(price1);
            String price = Base64.getBase64(s);
            if (baojia1 == null) {
                if (checkTime(bid)) {
                    //整数部分超7位精度丢失
                    baojia.setPrice(price);
                    baojia.setTransportPrice(Base64.getBase64(transportPrices));
                    baojia.setUnitPrice(Base64.getBase64(prices));
                    if (buyInfo.getBaojiaPrice() == -1) {//不要求保证金。
                        baojia.setCheckLevel(0);
                    } else {
                        baojia.setCheckLevel(-1);
                    }
                    int i = baojiaMapper.insert(baojia);
                    Baojia_Gys bg = new Baojia_Gys();
                    bg.setBjid(baojia.getId());
                    Gys gys = gysMapper.findByUid(uid);
                    bg.setGid(gys.getId());
                    baojia_gysMapper.insert(bg);
                } else {
                    logger.error("该订单有效报价时间已过!");
                    throw new TradeException("该订单有效报价时间已过!");
                }
            }
            //重复点击缴纳保证金按钮和点击提交按钮修改报价信息
            else if (baojia1.getCheckLevel() == -1 || baojia1.getCheckLevel() == 0) {
                if (checkTime(bid)) {
                    //整数部分超7位精度丢失
                    baojia.setPrice(price);
                    baojia.setCheckLevel(-1);
                    baojia.setTransportPrice(Base64.getBase64(transportPrices));
                    baojia.setUnitPrice(Base64.getBase64(prices));
                    baojiaMapper.updateByPrimaryKey(baojia);
                }
            } else {
                logger.error("非法操作-重复提交报价表--");
                throw new TradeException("非法操作-重复提交报价表--");//用户通过连接直接访问抛出异常
            }
        } catch (TradeException e) {
            throw e;
        } catch (Exception e1) {//编译异常转为运行异常
            throw new TradeException(e1.getMessage());
        }

    }
//再次提交报价信息
    @Override
    public PayResult<String> updateBaojia(Baojia baojia, int bid, int uid,String transportPrice,String unitPrice) throws Exception {
        Baojia baojia1 = baojiaMapper.findByCheckInfo(bid, uid);
        if (baojia1 == null || baojia1.getCheckLevel() == -1) {
            return new PayResult<String>(TradeEnum.NOPAY);
        } else {
            if (checkTime(bid)) {
                double unit=Double.parseDouble(unitPrice);
                double tran=Double.parseDouble(transportPrice);
                double price1 = (unit+tran) * baojia.getNumber();
                //加密报价价格
                String s = DoubleToString.dToS(price1);
                String price = Base64.getBase64(s);
                baojia.setCheckLevel(0);
                baojia.setPrice(price);
                baojia.setBid(bid);
                baojia.setTransportPrice(Base64.getBase64(transportPrice));
                baojia.setUnitPrice(Base64.getBase64(unitPrice));
                baojiaMapper.updateByPrimaryKey(baojia);
                return new PayResult<String>(TradeEnum.UPDATA);
            } else {
                String error = "该订单有效报价时间已过!";
                return new PayResult<String>(TradeEnum.NOTIME, error);
            }
        }
    }

    //支付钱
    @Override
    public PayResult<BuyInfo> payMoney(int id, int uid, int number) {
        BuyInfo buyInfo = buyInfoMapper.selectByPrimaryKey(id);
        if(!checkTime(id)){
            return new PayResult<BuyInfo>(TradeEnum.NOTIME,buyInfo);
        }
        Baojia baojia1 = baojiaMapper.findByCheckInfo(buyInfo.getId(), uid);
        double totalprice = buyInfo.getBaojiaPrice() * number;//
        double unit=Double.parseDouble(Base64.getFromBase64(baojia1.getUnitPrice()));//计算修改的报价价格
        double tran=Double.parseDouble(Base64.getFromBase64(baojia1.getTransportPrice()));
        double price1 = (unit+tran) * number;
        String price=DoubleToString.dToS(price1);
        //用户付钱
        Users u = usersMapper.selectByPrimaryKey(uid);
        if (u.getMoney() - totalprice < 0) {
            return new PayResult<BuyInfo>(TradeEnum.NOMONEY, buyInfo);
        }
        u.setMoney(u.getMoney() - totalprice);
        u.setFreezeMoney(totalprice + u.getFreezeMoney());
        usersMapper.updateByPrimaryKey(u);
        baojia1.setCheckLevel(0);//已经缴纳保证金
        baojia1.setNumber(number);
        baojia1.setPrice(Base64.getBase64(price));
        baojiaMapper.updateByPrimaryKey(baojia1);
        return new PayResult<BuyInfo>(TradeEnum.SUCCESS, buyInfo);

    }
    private boolean checkTime(int id) {
        BuyInfo buyInfo = buyInfoMapper.selectByPrimaryKey(id);
        Date startTime = buyInfo.getCreateTime();
        Date endTime = buyInfo.getEndTime();
        Date nowTime = new Date();
        if (nowTime.getTime() > startTime.getTime() && nowTime.getTime() < endTime.getTime()) {
            return true;
        }
        return false;
    }
}
