package org.trade.web;

import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.trade.dto.PayResult;
import org.trade.entity.Baojia;
import org.trade.entity.BuyInfo;
import org.trade.entity.Users;
import org.trade.enums.TradeEnum;
import org.trade.exception.TradeException;
import org.trade.service.*;

import javax.servlet.http.HttpSession;

@Controller
public class BaojiaController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BaojiaService baojiaService;
    @Autowired
    private BuyInfoService buyInfoService;
    @Autowired
    private UsersServcie usersServcie;


    //UI查询所有可以报价的采购信息
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String baojiaUi(Model model, HttpSession session,@RequestParam(value = "page",defaultValue = "1",required = true)int page) {
        Users users = (Users) session.getAttribute("users");

            if (users.getRole().getAction().equals("阳光用户")) {
                PageInfo<BuyInfo> p = buyInfoService.findAllInEffectiveTime(page);
                model.addAttribute("page", p);
            } else {
                throw new TradeException("权限不足！！username=" + users.getUsername());

            }
            return "list";

    }

    //查看采购信息并且报价
    @RequestMapping(value = "/baojia", method = RequestMethod.GET)
    public String baojiaShow(Model model, HttpSession session, @RequestParam("id") int id) {
        Users users = (Users) session.getAttribute("users");

            if (users.getRole().getAction().equals("阳光用户")) {
                BuyInfo buyInfo = buyInfoService.findByIdAndCheckLevel(id);
                if (buyInfo == null) {
                    return "redirect:/list";
                }
                Baojia baojia = baojiaService.findCheckInfo(id, users.getId());
                if (buyInfo.getBaojiaPrice() != -1) {
                    double price = buyInfo.getBaojiaPrice() * buyInfo.getNumber();//要缴纳的保证金计算
                    model.addAttribute("price", price);
                } else {
                    model.addAttribute("price", "不要求保证金。");
                }
                model.addAttribute("baojia", baojia);
                model.addAttribute("buyInfo", buyInfo);
            } else {
                logger.error("权限不足！！username=" + users.getUsername());
                throw new TradeException("权限不足！！username=" + users.getUsername());
            }
            return "baojia";

    }

    //点击缴纳保证金按钮,报价信息
    @RequestMapping(value = "/savebaojia", method = RequestMethod.POST)
    public String saveBaojia(Baojia baojia, @RequestParam("bid") int bid,
                             @RequestParam("transportPrices") String transportPrices,
                             @RequestParam("prices") String prices,
                             HttpSession session, Model model) {

        Users users = (Users) session.getAttribute("users");
        BuyInfo buyInfo = buyInfoService.findById(bid);

            if (users.getRole().getAction().equals("阳光用户")) {
                //类型转换
                if (buyInfo.getBaojiaPrice() == -1) {
                    model.addAttribute("price", "不要求保证金。");
                } else {
                    double total = buyInfo.getBaojiaPrice() * baojia.getNumber();
                    model.addAttribute("price", total);//将保证金放入Model
                }


                baojiaService.insert(baojia, bid, users.getId(), transportPrices, prices);

                model.addAttribute("number", baojia.getNumber());//数量放入model

                model.addAttribute("id", bid);//报价id
            } else {
                throw new TradeException("权限不足-非法操作！！username=" + users.getUsername());
            }

        //使用转发保存数据
        return "forward:/money";
    }

    //缴纳保证金页面
    @RequestMapping(value = "/money", method = RequestMethod.POST)
    public String money(HttpSession session, Model model) {
        Users users = (Users) session.getAttribute("users");
        //session里面的数据不会时时更新!!!
        Users u = usersServcie.findById(users.getId());
        double can = u.getMoney() + u.getFreezeMoney() + u.getFreezeMoney2();
        model.addAttribute("can", can);
        model.addAttribute("u", u);
        return "money";
    }

    //点击确认缴纳
    // int id（"采购"）, String price,int uid
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    @ResponseBody
    public PayResult<BuyInfo> pay(HttpSession session, @RequestParam("number") int number,
                                  @RequestParam("id") int id) {
        Users users = (Users) session.getAttribute("users");
        return baojiaService.payMoney(id, users.getId(), number);
    }

    //提交报价单
    @RequestMapping(value = "/submit", method = RequestMethod.POST)
    @ResponseBody
    public PayResult<String> baojia(Baojia baojia, @RequestParam("bid") int bid,
                                    @RequestParam("transportPrices") String transportPrice,
                                    @RequestParam("unitPrices") String unitPrice, HttpSession session) throws Exception {
        Users user = (Users) session.getAttribute("users");
        BuyInfo buyInfo = buyInfoService.findById(bid);
        Baojia baojia1 = baojiaService.findCheckInfo(bid, user.getId());

        if (buyInfo.getBaojiaPrice() == -1 && baojia1 == null) {
            //不要求报价直接提交表单
            baojiaService.insert(baojia, bid, user.getId(), transportPrice, unitPrice);
            return new PayResult<String>(TradeEnum.UPDATA);
        }
        return baojiaService.updateBaojia(baojia, bid, user.getId(), transportPrice, unitPrice);
    }



}
