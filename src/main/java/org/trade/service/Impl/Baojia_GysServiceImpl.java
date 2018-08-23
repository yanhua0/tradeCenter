package org.trade.service.Impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trade.dao.BaojiaMapper;
import org.trade.dao.Baojia_GysMapper;
import org.trade.dao.MesMapper;
import org.trade.dao.UsersMapper;
import org.trade.entity.Baojia_Gys;
import org.trade.entity.Mes;
import org.trade.entity.Users;
import org.trade.service.Baojia_GysService;
import org.trade.util.Base64;

import java.util.ArrayList;
import java.util.List;

@Service
public class Baojia_GysServiceImpl implements Baojia_GysService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private Baojia_GysMapper baojia_gysMapper;
    @Autowired
    private MesMapper mesMapper;
    @Autowired
    private BaojiaMapper baojiaMapper;
    @Autowired
    private UsersMapper usersMapper;

    //第一级审核查看供应商和报价信息
    @Override
    public List<Baojia_Gys> findAllChecklevel1(int id) throws Exception {
        //取出时候解密价格
        List<Baojia_Gys> list = baojia_gysMapper.findAllChecklevel1(id);
       list = showPrice(list);
        return list;
    }

    //第二级审核查看供应商和报价信息
    @Override
    public List<Baojia_Gys> findAllChecklevel2(int id) throws Exception {
        List<Baojia_Gys> list = baojia_gysMapper.findAllChecklevel2(id);
        list = showPrice(list);
        return list;
    }

    //第三级审核查看供应商和报价信息
    @Override
    public List<Baojia_Gys> findAllChecklevel3(int id) throws Exception {
        List<Baojia_Gys> list = baojia_gysMapper.findAllChecklevel3(id);
        list = showPrice(list);
        return list;
    }

    //点击驳回按钮
    @Override
    public void refuse(int bjid[], String advice, String name, int id[]) {
        //如果未选中供应商再点击
        if (bjid.length != 0) {
            List<Baojia_Gys> bg = baojia_gysMapper.findByArray(id);
            if (bg.size() > 0) {
                List<Mes> list = new ArrayList<Mes>();//保存信息
                Mes[] mes = new Mes[bg.size()];
                //将驳回的人员信息放入集合
                for (int i = 0; i < bg.size(); i++) {
                    //返钱
                    double price = 0;
                    if (bg.get(i).getBaojia().getBuyInfo().getBaojiaPrice() != -1) {
                        price = bg.get(i).getBaojia().getBuyInfo().getBaojiaPrice() * bg.get(i).getBaojia().getNumber();//之前冻结花费的钱
                    }
                    double freeze = bg.get(i).getGys().getUsers().getFreezeMoney() - price;
                    double money = bg.get(i).getGys().getUsers().getMoney() + price;
                    Users users = new Users(money, freeze);
                    users.setId(bg.get(i).getGys().getUid());
                    usersMapper.updateMoney(users);//还钱结束
                    //发送消息
                    mes[i] = new Mes();
                    mes[i].setReceieveid(bg.get(i).getGys().getUid());
                    mes[i].setState(0);
                    mes[i].setSendname(name);
                    mes[i].setMessage("你对订单号为<span>" + bg.get(i).getBaojia().getBuyInfo().getSno() + "</span>的报价信息已经作废,保证金<span>" + price + "元</span>已自动返还账户！作废理由：" + advice);
                    list.add(mes[i]);
                }

                mesMapper.insertListMes(list);
                baojiaMapper.deleteByIdArray(bjid);
            }

        }

    }

    private List<Baojia_Gys> showPrice(List<Baojia_Gys> list) throws Exception {
        for (int i = 0; i < list.size(); i++) {
            String price = list.get(i).getBaojia().getPrice();
            String unit = list.get(i).getBaojia().getUnitPrice();
            String tr = list.get(i).getBaojia().getTransportPrice();
            //解密
            String jiemiunit = Base64.getFromBase64(unit);
            String tran = Base64.getFromBase64(tr);
            String s = Base64.getFromBase64(price);
            logger.info("----------解密-------总价格="+price+"煤单价="+unit+"运输费用单价="+tr);
            list.get(i).getBaojia().setPrice(s);
            list.get(i).getBaojia().setTransportPrice(tran);
            list.get(i).getBaojia().setUnitPrice(jiemiunit);
        }
        return list;
    }
}
