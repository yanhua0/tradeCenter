package org.trade.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.trade.entity.BuyInfo;
import org.trade.entity.BuyInfo_Users;
import org.trade.entity.Users;
import org.trade.exception.TradeException;
import org.trade.service.BuyInfoService;
import org.trade.service.BuyInfo_UsersService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BuyInfo_UsersController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BuyInfo_UsersService buyInfo_usersService;
    @Autowired
    private BuyInfoService buyInfoService;

    //第一级电厂审核页面url
    @RequestMapping(value = "/check1", method = RequestMethod.GET)
    public String check1(@RequestParam("id") int id, Model model, HttpSession session) {
        Users users = (Users) session.getAttribute("users");

        if (users.getRole().getAction().equals("电厂审核")) {
            BuyInfo buyInfo = buyInfoService.findById(id);
            if (buyInfo == null) {
                logger.error("采购信息不存在!");
                return "redirect:/member";
            }
            boolean flag = buyInfoService.checkSno(id, users);
            if (!flag) {
                throw new TradeException("非法操作!");
            }
            double i = buyInfo.getNumber() * buyInfo.getBaojiaPrice();
            if (i >= 0) {
                model.addAttribute("price", i);
            }
            if (buyInfo.getCheckLevel() == 0) {
                model.addAttribute("buyInfo", buyInfo);
            }
            //防止用户通过url直接访问未创建的采购信息
            if (buyInfo.getCheckLevel() != 0) {
                throw new TradeException("非法操作!!");
            }
        } else {
            //防止用户直接通过url访问
            logger.error(users.getId() + "权限不足！");
            throw new TradeException("权限不足！非法操作！");
        }

        return "check1";
    }

    //第二级电厂审核页面url
    @RequestMapping(value = "/check2", method = RequestMethod.GET)
    public String check2(@RequestParam("id") int id, Model model, HttpSession session) {
        Users users = (Users) session.getAttribute("users");

        if (users.getRole().getAction().contains("分子公司")) {
            BuyInfo buyInfo = buyInfoService.findById(id);
            if (buyInfo == null) {
                return "redirect:/member";
            }
            boolean flag = buyInfoService.checkSno(id, users);
            if (!flag) {
                throw new TradeException("非法操作!");
            }
            double i = buyInfo.getNumber() * buyInfo.getBaojiaPrice();
            if (i >= 0) {
                model.addAttribute("price", i);
            }
            if (buyInfo.getCheckLevel() == 1) {
                List<BuyInfo_Users> buyInfo_users = buyInfo_usersService.findAllCheckPerson(id);
                model.addAttribute("buyInfo", buyInfo);
                model.addAttribute("BuyInfo_Users", buyInfo_users);
            }
            //防止用户通过url直接访问未创建的采购信息
            if (buyInfo.getCheckLevel() != 1) {
                throw new TradeException("非法操作!!");
            }
        } else {
            //防止用户直接通过url访问
            logger.error(users.getId() + "权限不足！");
            throw new TradeException("权限不足！非法操作！");
        }

        return "check2";
    }

    @RequestMapping(value = "/checkInfo1", method = RequestMethod.POST)
    @ResponseBody
    public int checkInfo1(HttpSession session, BuyInfo buyInfo) {
        //防止用户传其他值
        if (!buyInfo.getOpo().equals("yes") && !buyInfo.getOpo().equals("no")) {
            return 0;
        } else {
            Users users = (Users) session.getAttribute("users");
            buyInfo_usersService.checkBuyInfo1(buyInfo, users);
            return 1;
        }
    }

    @RequestMapping(value = "/checkInfo2", method = RequestMethod.POST)
    @ResponseBody
    public int checkInfo2(HttpSession session, BuyInfo b) {
        if (!b.getOpo().equals("yes") && !b.getOpo().equals("no")) {
            return 0;
        } else {
            Users users = (Users) session.getAttribute("users");
            buyInfo_usersService.checkBuyInfo2(b, users);
            return 1;
        }
    }

}
