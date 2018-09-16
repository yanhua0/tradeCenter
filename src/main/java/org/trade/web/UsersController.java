package org.trade.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.trade.entity.Users;
import org.trade.service.UsersServcie;
import org.trade.util.RandomValidateCode;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UsersController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UsersServcie usersServcie;

    //获得随机验证码
    @RequestMapping(value = "/getVerifyCode",method = RequestMethod.GET)
    public void getVerifyCode(
            HttpServletResponse response,
            HttpServletRequest request

    ) {
        System.out.println("获取验证码");
        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //登陆页面地址
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpSession session) {
        session.invalidate();
        return "login";
    }
//    //退出
//    @RequestMapping(value = "/logout", method = RequestMethod.GET)
//    public String logout() {
//
//        return "redirect:/login";
//    }
    //登陆验证
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public String check(Users users, RedirectAttributes attr, HttpSession session, HttpServletResponse response, @RequestParam("verifyCode") String user_verifyCode) {
        Users u = usersServcie.login(users);
        if (u != null) {
            attr.addFlashAttribute("users",users);
            attr.addFlashAttribute("yzm",user_verifyCode);
            String u_verifyCode=user_verifyCode.toUpperCase();
            String verifyCode=(String) session.getAttribute("RANDOMVALIDATECODEKEY");//系统随机产生的验证码从session中取出
            String s_verifyCode="";
            if(verifyCode==null){
                attr.addFlashAttribute("error", "验证码已经失效!");
                return "redirect:/login";
            }
            s_verifyCode=verifyCode.toUpperCase();
            if(u_verifyCode.equals(s_verifyCode)) {
                session.setMaxInactiveInterval(3600);
                session.setAttribute("users", u);
                //放入cookies关闭浏览器用户没有退出
                logger.info("session.getId()="+session.getId());
                Cookie cookieSId = new Cookie("JSESSIONID", session.getId());
                cookieSId.setMaxAge(60 * 60);
                cookieSId.setPath("/");
                response.addCookie(cookieSId);
                return "redirect:/";
            }else{
                attr.addFlashAttribute("error", "验证码错误!");
                return "redirect:/login";
            }
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
