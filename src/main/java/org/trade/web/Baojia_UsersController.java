package org.trade.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.trade.entity.Baojia_Gys;
import org.trade.entity.Baojia_Users;
import org.trade.entity.BuyInfo;
import org.trade.entity.Users;
import org.trade.exception.TradeException;
import org.trade.service.Baojia_GysService;
import org.trade.service.Baojia_UsersService;
import org.trade.service.BuyInfoService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class Baojia_UsersController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private Baojia_GysService baojia_gysService;
    @Autowired
    private Baojia_UsersService baojia_usersService;
    @Autowired
    private BuyInfoService buyInfoService;
    //查询需要第(1,2,3)级筛选供应商的采购订单
    @RequestMapping(value = "/gys", method = RequestMethod.GET)
    public String selectSuppliers1(Model model, HttpSession session) {
        Users users = (Users) session.getAttribute("users");
        try {
            List<BuyInfo> buyInfos = null;
            if (users.getRole().getAction().equals("分子公司审核")) {
                buyInfos = buyInfoService.selectSuppliers1(users);

            } else if (users.getRole().getAction().equals("电厂审核")) {
                buyInfos = buyInfoService.selectSuppliers2(users);
            } else if (users.getRole().getAction().equals("分子公司审批")) {
                buyInfos = buyInfoService.selectSuppliers3(users);
            } else {
                logger.error("权限不足！---用户id：" + users.getId());
                throw new TradeException("权限不足！非法操作!");
            }
            model.addAttribute("buyInfo", buyInfos);
            return "gys";
        } catch (Exception e) {
            throw new TradeException(e.getMessage());
        }
    }

    //供应商报价完毕第一级筛选供应商ui
    @RequestMapping(value = "/gys1", method = RequestMethod.GET)
    public String gysCheckUi1(Model model, HttpSession session, @RequestParam("id") int id) {
        try {
            Users users = (Users) session.getAttribute("users");
            if (users.getRole().getAction().equals("分子公司审核")) {
                BuyInfo buyInfo = buyInfoService.findById(id);
                if (buyInfo == null) {
                    return "redirect:/gys";

                } else {
                    boolean flag=buyInfoService.checkSno(id,users);
                    if(!flag){
                        throw new TradeException("非法操作!");
                    }
                    List<Baojia_Gys> baojia_gys = baojia_gysService.findAllChecklevel1(id);
                    model.addAttribute("buyInfo", buyInfo);
                    model.addAttribute("baojia_gys", baojia_gys);
                    if (buyInfo.getBaojiaPrice() != -1) {
                        double price = buyInfo.getBaojiaPrice() * buyInfo.getNumber();//要缴纳的保证金计算
                        model.addAttribute("price", price);
                    } else {
                        model.addAttribute("price", "不要求保证金。");
                    }
                    return "gys1";
                }
            } else {
                throw new TradeException("权限不足!");
            }
        } catch (TradeException e) {
            throw e;
        } catch (Exception e1) {
            throw new TradeException(e1.getMessage());
        }
    }

    //供应商报价完毕第二级筛选供应商ui
    @RequestMapping(value = "/gys2", method = RequestMethod.GET)
    public String gysCheckUi2(Model model, HttpSession session, @RequestParam("id") int id) {
        try {
            Users users = (Users) session.getAttribute("users");
            if (users.getRole().getAction().equals("电厂审核")) {
                BuyInfo buyInfo = buyInfoService.findById(id);
                if (buyInfo == null) {
                    return "redirect:/gys";
                } else {
                    boolean flag=buyInfoService.checkSno(id,users);
                    if(!flag){
                        throw new TradeException("非法操作!");
                    }
                    List<Baojia_Gys> baojia_gys = baojia_gysService.findAllChecklevel2(id);
                    model.addAttribute("buyInfo", buyInfo);
                    model.addAttribute("baojia_gys", baojia_gys);
                    if (buyInfo.getBaojiaPrice() != -1) {
                        double price = buyInfo.getBaojiaPrice() * buyInfo.getNumber();//要缴纳的保证金计算
                        model.addAttribute("price", price);
                    } else {
                        model.addAttribute("price", "不要求保证金。");
                    }
                    return "gys2";
                }
            } else {
                throw new TradeException("权限不足!");
            }
        } catch (TradeException e) {
            throw e;
        } catch (Exception e1) {
            throw new TradeException(e1.getMessage());
        }
    }

    //供应商报价完毕第三级筛选供应商ui
    @RequestMapping(value = "/gys3", method = RequestMethod.GET)
    public String gysCheckUi3(Model model, HttpSession session, @RequestParam("id") int id) {
        try {
            Users users = (Users) session.getAttribute("users");
            if (users.getRole().getAction().equals("分子公司审批")) {
                BuyInfo buyInfo = buyInfoService.findById(id);
                if (buyInfo == null) {
                    return "redirect:/gys";
                } else {
                    boolean flag=buyInfoService.checkSno(id,users);
                    if(!flag){
                        throw new TradeException("非法操作!");
                    }
                    List<Baojia_Gys> baojia_gys = baojia_gysService.findAllChecklevel3(id);
                    List<Baojia_Users> baojia_users = baojia_usersService.findAllCheckPerson(id);
                    model.addAttribute("buyInfo", buyInfo);
                    model.addAttribute("baojia_gys", baojia_gys);
                    model.addAttribute("bu", baojia_users);
                    if (buyInfo.getBaojiaPrice() != -1) {
                        double price = buyInfo.getBaojiaPrice() * buyInfo.getNumber();//要缴纳的保证金计算
                        model.addAttribute("price", price);
                    } else {
                        model.addAttribute("price", "不要求保证金。");
                    }
                    return "gys3";
                }
            } else {
                throw new TradeException("权限不足!");
            }
        } catch (TradeException e) {
            throw e;
        } catch (Exception e1) {
            throw new TradeException(e1.getMessage());
        }
    }

    //作废操作
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public String del(HttpSession session, @RequestParam("bjid") int bjid[],
                      @RequestParam("advice") String advice, @RequestParam("id") int id[], @RequestParam("bid") int bid) {
        Users users = (Users) session.getAttribute("users");

            if (users.getRole().getAction().equals("分子公司审核")) {
                baojia_gysService.refuse(bjid, advice, users.getName(), id);//第一个id是报价信息的id，第二个是关联表的id
                return "redirect:gys1?id=" + bid;
            } else if (users.getRole().getAction().equals("分子公司审批")) {
                baojia_gysService.refuse(bjid, advice, users.getName(), id);//第一个id是报价信息的id，第二个是关联表的id
                return "redirect:gys3?id=" + bid;
            } else if (users.getRole().getAction().equals("电厂审核")) {
                baojia_gysService.refuse(bjid, advice, users.getName(), id);//第一个id是报价信息的id，第二个是关联表的id
                return "redirect:gys2?id=" + bid;
            } else {
                throw new TradeException("权限不足!");
            }

        //第二个id是关联表的Id
    }

    //第一级审核通过
    @RequestMapping(value = "/checkLevel1", method = RequestMethod.POST)
    public String checkLevel1(HttpSession session, @RequestParam("bjid") int id[],
                              @RequestParam("advice") String advice, @RequestParam("bid") int bid) {

            Users users = (Users) session.getAttribute("users");
            if (users.getRole().getAction().equals("分子公司审核")) {
                baojia_usersService.check1(id, advice, users, bid);
                return "redirect:gys1?id=" + bid;
            } else {
                throw new TradeException("权限不足!" + users.getUsername());
            }


    }

    //第二级审核通过
    @RequestMapping(value = "/checkLevel2", method = RequestMethod.POST)
    public String checkLevel2(HttpSession session, @RequestParam("bjid") int id[],
                              @RequestParam("advice") String advice, @RequestParam("bid") int bid) {

            Users users = (Users) session.getAttribute("users");
            if (users.getRole().getAction().equals("电厂审核")) {
                baojia_usersService.check2(id, advice, users, bid);
                return "redirect:gys2?id=" + bid;
            } else {
                throw new TradeException("权限不足!" + users.getUsername());
            }


    }
    //第三级审核通过

    @RequestMapping(value = "/checkLevel3", method = RequestMethod.POST)
    public String checkLevel3(HttpSession session, @RequestParam("bjid") int id[], @RequestParam("id") int bgid[],
                              @RequestParam("bid") int bid) {

            Users users = (Users) session.getAttribute("users");
            if (users.getRole().getAction().equals("分子公司审批")) {
                baojia_usersService.check3(id, users, bid, bgid);
                return "redirect:gys3?id=" + bid;
            } else {
                throw new TradeException("权限不足!" + users.getUsername());
            }


    }
}
