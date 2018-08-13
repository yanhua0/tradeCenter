package org.trade.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trade.dao.BuyInfoMapper;
import org.trade.dao.BuyInfo_UsersMapper;
import org.trade.dao.MesMapper;
import org.trade.entity.BuyInfo;
import org.trade.entity.BuyInfo_Users;
import org.trade.entity.Mes;
import org.trade.entity.Users;
import org.trade.exception.TradeException;
import org.trade.service.BuyInfo_UsersService;

import java.util.List;

@Service
public class BuyInfo_UsersServiceImpl implements BuyInfo_UsersService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BuyInfoMapper buyInfoMapper;
    @Autowired
    private MesMapper mesMapper;
    @Autowired
    private BuyInfo_UsersMapper buyInfo_usersMapper;

    @Override
    public void checkBuyInfo1(BuyInfo buyInfo, Users users) throws TradeException {
        BuyInfo b = buyInfoMapper.selectByPrimaryKey(buyInfo.getId());
        //判断权限
        try {
            if (b.getCheckLevel() == 0) {
                if (buyInfo.getOpo().equals("yes")) {
                    b.setCheckLevel(1);
                    buyInfoMapper.updateByPrimaryKey(b);
                    BuyInfo_Users bu = new BuyInfo_Users();
                    bu.setCheckAdvice(buyInfo.getAdvice());
                    bu.setUid(users.getId());
                    bu.setBid(buyInfo.getId());
                    buyInfo_usersMapper.insert(bu);
                    logger.info("第" + 1 + "级审批通过：审批采购单Id=" + buyInfo.getId() + "审批人Id：" + users.getName());
                } else if (buyInfo.getOpo().equals("no")) {
                    no(b);
                    insertMes(buyInfo, users, b);
                }
            }
            //防止用户重复提交审核采购订单
            else {
                logger.error("非法操作");
                throw new TradeException("非法操作！");
            }
        } catch (TradeException e) {
            throw e;
        } catch (Exception e1) {
            throw new TradeException(e1.getMessage());
        }
    }

    @Override
    public void checkBuyInfo2(BuyInfo buyInfo, Users users) {
        BuyInfo b = buyInfoMapper.selectByPrimaryKey(buyInfo.getId());
        //判断权限
        try {
            if (b.getCheckLevel() == 1) {
                if (buyInfo.getOpo().equals("yes")) {
                    b.setCheckLevel(2);
                    buyInfoMapper.updateByPrimaryKey(b);
                    logger.info("第" + 2 + "级审批通过：审批采购单Id=" + buyInfo.getId() + "审批人Id：" + users.getName());
                    //审批全部完成删除审批通过的审核意见表，对外发布采购信息
                } else if (buyInfo.getOpo().equals("no")) {
                    no(b);
                    insertMes(buyInfo, users, b);
                }

            } else {
                logger.error("非法操作");
                throw new TradeException("非法操作!");
            }
        } catch (TradeException e) {
            throw e;
        } catch (Exception e1) {
            throw new TradeException(e1.getMessage());
        }

    }

    @Override
    public List<BuyInfo_Users> findAllCheckPerson(int bid) {
        return buyInfo_usersMapper.findAllCheckPerson(bid);
    }

    private void no(BuyInfo b) {
        b.setCheckLevel(-2);
        buyInfoMapper.updateByPrimaryKey(b);
        logger.info("驳回"+b.getSno()+"成功！");
    }

    private void insertMes(BuyInfo buyInfo, Users users, BuyInfo b) {
        Mes mes = new Mes();
        mes.setMessage("你创建的订单号为<span>"+b.getSno() + "</span>的采购信息已经被上级驳回,驳回理由：" + buyInfo.getAdvice());
        mes.setSendname(users.getName());
        mes.setState(0);
        mes.setReceieveid(b.getUid());
        mesMapper.insert(mes);
    }
}
