package org.trade.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.trade.entity.Users;
import org.trade.service.UsersServcie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UsersController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UsersServcie usersServcie;


    //登陆页面地址
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpSession session) {
        session.invalidate();
        return "login";
    }

    //登陆验证
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public String check(Users users, RedirectAttributes attr, HttpSession session, HttpServletResponse response) {
        Users u = usersServcie.login(users);
        if (u != null) {
            session.setMaxInactiveInterval(3600);
            session.setAttribute("users", u);
            //放入cookies关闭浏览器用户没有退出
            Cookie cookieSId = new Cookie("JSESSIONID",session.getId());
            cookieSId.setMaxAge(60*60);
            cookieSId.setPath("/");
            response.addCookie(cookieSId);
            return "redirect:/";
        } else {
            attr.addFlashAttribute("error", "用户名或密码错误！");
            return "redirect:/login";
        }

    }
    @RequestMapping(value = "/money1", method = RequestMethod.GET)
    public String goLookMoney(HttpSession session,Model model){
        Users users= (Users) session.getAttribute("users");
        //session里面的数据不会时时更新!!!
        Users u=usersServcie.findById(users.getId());
        double can=u.getMoney()+u.getFreezeMoney()+u.getFreezeMoney2();
        model.addAttribute("can",can);
        model.addAttribute("u",u);
        return "money1";
    }


}
