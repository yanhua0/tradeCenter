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
import org.trade.entity.*;
import org.trade.enums.TradeEnum;
import org.trade.exception.TradeException;
import org.trade.service.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BaojiaController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BaojiaService baojiaService;
    @Autowired
    private BuyInfoService buyInfoService;
    @Autowired
    private UsersServcie usersServcie;
    @Autowired
    private Baojia_GysService baojia_gysService;
    @Autowired
    private Baojia_UsersService baojia_usersService;

    //UI查询所有可以报价的采购信息
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String baojiaUi(Model model, HttpSession session, @RequestParam(value = "page", defaultValue = "1", required = true) Integer page1) {
        Users users = (Users) session.getAttribute("users");
        try {
            if (users.getRole().getAction().equals("阳光用户")) {
                PageInfo<BuyInfo> p = buyInfoService.findAllInEffectiveTime(page1);
                model.addAttribute("page", p);
            } else {
                throw new TradeException("权限不足！！username=" + users.getUsername());

            }
            return "list";
        } catch (TradeException e1) {
            throw e1;
        } catch (Exception e2) {
            throw new TradeException(e2.getMessage());
        }
    }

    //查看采购信息并且报价
    @RequestMapping(value = "/baojia", method = RequestMethod.GET)
    public String baojiaShow(Model model, HttpSession session, @RequestParam("id") int id) {
        Users users = (Users) session.getAttribute("users");
        try {
            if (users.getRole().getAction().equals("阳光用户")) {
                BuyInfo buyInfo = buyInfoService.findById(id);
                Baojia baojia = baojiaService.findCheckInfo(id, users.getId());

                if (baojia != null) {
                    if (buyInfo.getBaojiaPrice() != -1) {
                        double price = buyInfo.getBaojiaPrice() * buyInfo.getNumber();//要缴纳的保证金计算
                        model.addAttribute("price", price);
                    } else {
                        model.addAttribute("price", "不要求保证金。");
                    }
                    model.addAttribute("baojia", baojia);
                }
                model.addAttribute("buyInfo", buyInfo);
            } else {
                logger.error("权限不足！！username=" + users.getUsername());
                throw new TradeException("权限不足！！username=" + users.getUsername());
            }
            return "baojia";
        } catch (TradeException e1) {
            throw e1;
        } catch (Exception e2) {
            throw new TradeException(e2.getMessage());
        }
    }

    //点击缴纳保证金按钮,报价信息
    @RequestMapping(value = "/savebaojia", method = RequestMethod.POST)
    public String saveBaojia(Baojia baojia, @RequestParam("bid") int bid,
                             @RequestParam("transportPrices") String transportPrices,
                             @RequestParam("prices") String prices,
                             HttpSession session, Model model) {

        Users users = (Users) session.getAttribute("users");
        BuyInfo buyInfo = buyInfoService.findById(bid);
        try {
            if (users.getRole().getAction().equals("阳光用户")) {
                //类型转换
                if (buyInfo.getBaojiaPrice() == -1) {
                    model.addAttribute("price", "不要求保证金。");
                } else {
                    double total = buyInfo.getBaojiaPrice() * baojia.getNumber();
                    model.addAttribute("price", total);//将保证金放入Model
                }

//                double t = Double.parseDouble(transportPrices);//运费单价
//
//                double unitprice = Double.parseDouble(prices);//单价
//                  double price=(t+unitprice)*baojia.getNumber();//总价
//                baojia.setTransportPrice();
//                baojia.setUnitPrice(unitprice);
                baojiaService.insert(baojia, bid, users.getId(),transportPrices,prices);

                model.addAttribute("number", baojia.getNumber());//数量放入model

                model.addAttribute("id", bid);//报价id
            } else {
                throw new TradeException("权限不足-非法操作！！username=" + users.getUsername());
            }
        } catch (TradeException e) {
            throw e;
        } catch (Exception e1) {
            throw new TradeException(e1.getMessage());
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
            baojiaService.insert(baojia, bid, user.getId(),transportPrice,unitPrice);
            return new PayResult<String>(TradeEnum.UPDATA);
        }
        return baojiaService.updateBaojia(baojia, bid, user.getId(),transportPrice,unitPrice);
    }

    //查询需要第(1,2,3)级筛选供应商的采购订单
    @RequestMapping(value = "/gys", method = RequestMethod.GET)
    public String selectSuppliers1(Model model, HttpSession session) {
        Users users = (Users) session.getAttribute("users");
        try {
            List<BuyInfo> buyInfos = null;
            if (users.getRole().getAction().equals("分子公司审核")) {
                buyInfos = buyInfoService.selectSuppliers1();

            } else if (users.getRole().getAction().equals("电厂审核")) {
                buyInfos = buyInfoService.selectSuppliers2();
            } else if (users.getRole().getAction().equals("分子公司审批")) {
                buyInfos = buyInfoService.selectSuppliers3();
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
                    List<Baojia_Gys> baojia_gys = baojia_gysService.findAllChecklevel1(id);
                    model.addAttribute("buyInfo", buyInfo);
                    model.addAttribute("baojia_gys", baojia_gys);
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
                    List<Baojia_Gys> baojia_gys = baojia_gysService.findAllChecklevel2(id);
                    model.addAttribute("buyInfo", buyInfo);
                    model.addAttribute("baojia_gys", baojia_gys);
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
                    List<Baojia_Gys> baojia_gys = baojia_gysService.findAllChecklevel3(id);
                    List<Baojia_Users> baojia_users = baojia_usersService.findAllCheckPerson(id);
                    model.addAttribute("buyInfo", buyInfo);
                    model.addAttribute("baojia_gys", baojia_gys);
                    model.addAttribute("bu", baojia_users);
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
                      @RequestParam("advice") String advice, @RequestParam("id") int id[],@RequestParam("bid") int bid) {
        Users users = (Users) session.getAttribute("users");
        try {
            if (users.getRole().getAction().equals("分子公司审核")) {
                baojia_gysService.refuse(bjid, advice, users.getName(), id);//第一个id是报价信息的id，第二个是关联表的id
                return "redirect:gys1?id="+bid;
            } else if (users.getRole().getAction().equals("分子公司审批")) {
                baojia_gysService.refuse(bjid, advice, users.getName(), id);//第一个id是报价信息的id，第二个是关联表的id
                return "redirect:gys3?id="+bid;
            } else {
                throw new TradeException("权限不足!");
            }
        } catch (TradeException e) {
            throw e;
        } catch (Exception e1) {
            logger.error("系统出错!"+e1.getMessage());
            throw new TradeException(e1.getMessage()+"----系统错误");
        }
        //第二个id是关联表的Id
    }

    //第一级审核通过
    @RequestMapping(value = "/checkLevel1", method = RequestMethod.POST)
    public String checkLevel1(HttpSession session, @RequestParam("bjid") int id[],
                              @RequestParam("advice") String advice, @RequestParam("bid") int bid) {
        try {
            Users users = (Users) session.getAttribute("users");
            if (users.getRole().getAction().equals("分子公司审核")) {
                baojia_usersService.check1(id, advice, users, bid);
                return "redirect:gys1?id="+bid;
            } else {
                throw new TradeException("权限不足!" + users.getUsername());
            }
        } catch (TradeException e) {
            throw e;
        } catch (Exception e1) {
            throw new TradeException(e1.getMessage());
        }

    }
    //第二级审核通过
    @RequestMapping(value = "/checkLevel2", method = RequestMethod.POST)
    public String checkLevel2(HttpSession session, @RequestParam("bjid") int id[],
                              @RequestParam("advice") String advice, @RequestParam("bid") int bid) {
        try {
            Users users = (Users) session.getAttribute("users");
            if (users.getRole().getAction().equals("电厂审核")) {
                baojia_usersService.check2(id, advice, users, bid);
                return "redirect:gys2?id="+bid;
            } else {
                throw new TradeException("权限不足!" + users.getUsername());
            }
        } catch (TradeException e) {
            throw e;
        } catch (Exception e1) {
            throw new TradeException(e1.getMessage());
        }

    }
    //第三级审核通过

    @RequestMapping(value = "/checkLevel3", method = RequestMethod.POST)
    public String checkLevel3(HttpSession session, @RequestParam("bjid") int id[],@RequestParam("id") int bgid[],
                              @RequestParam("bid") int bid) {
        try {
            Users users = (Users) session.getAttribute("users");
            if (users.getRole().getAction().equals("分子公司审批")) {
                baojia_usersService.check3(id, users, bid,bgid);
                return "redirect:gys3?id="+bid;
            } else {
                throw new TradeException("权限不足!" + users.getUsername());
            }
        } catch (TradeException e) {
            throw e;
        } catch (Exception e1) {
            throw new TradeException(e1.getMessage());
        }

    }

}
