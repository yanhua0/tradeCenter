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
import org.trade.entity.Mes;
import org.trade.entity.Users;
import org.trade.service.MesService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MesController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MesService mesService;
    @RequestMapping(value = "mesCheck",method = RequestMethod.POST)
    @ResponseBody
    public int mesCheck(Model model, HttpSession session) {
        Users users= (Users) session.getAttribute("users");
        if(users==null){
            logger.info("请登录!");
            return 0;
        }
        else {
            logger.info(users.getUsername()+"用户有未知消息!");
            List<Mes> mes =mesService.findByState(users.getId());
            return mes.size();
        }

    }
    @RequestMapping(value = "/mes",method = RequestMethod.GET)
    public String mes(Model model,@RequestParam(value = "page",defaultValue = "1", required = true)Integer page,HttpSession session){
        Users users= (Users) session.getAttribute("users");
        PageInfo<Mes> p=mesService.findByPage(page,users.getId());
        model.addAttribute("page",p);
        return "mes";
    }
    @RequestMapping(value = "deleteMes",method = RequestMethod.GET)
      public String del(@RequestParam("id")int id){
        mesService.delete(id);
        return "redirect:/mes";
    }
}
