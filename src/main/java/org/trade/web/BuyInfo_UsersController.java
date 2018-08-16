package org.trade.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.trade.entity.BuyInfo;
import org.trade.entity.Users;
import org.trade.service.BuyInfo_UsersService;

import javax.servlet.http.HttpSession;

@Controller
public class BuyInfo_UsersController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BuyInfo_UsersService buyInfo_usersService;
    @RequestMapping(value = "/checkInfo1", method = RequestMethod.POST)
    @ResponseBody
    public int checkInfo1(HttpSession session,BuyInfo buyInfo){
        //防止用户传其他值
        if(!buyInfo.getOpo().equals("yes")&&!buyInfo.getOpo().equals("no")){
            return 0;
        }
        else{
            Users users= (Users) session.getAttribute("users");
            buyInfo_usersService.checkBuyInfo1(buyInfo,users);
            return 1;
        }
  }

    @RequestMapping(value = "/checkInfo2", method = RequestMethod.POST)
    @ResponseBody
    public int checkInfo2(HttpSession session,BuyInfo b){
        if(!b.getOpo().equals("yes")&& !b.getOpo().equals("no")){
            return 0;
        }
        else{
            Users users= (Users) session.getAttribute("users");
            buyInfo_usersService.checkBuyInfo2(b,users);
            return 1;
        }
    }

}
