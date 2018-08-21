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
import org.trade.entity.BuyInfo;
import org.trade.entity.Supplier;
import org.trade.entity.Users;
import org.trade.exception.TradeException;
import org.trade.service.BuyInfoService;
import org.trade.service.Impl.SupplierService;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;


@Controller
public class BuyInfoController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BuyInfoService buyInfoService;
    @Autowired
    private SupplierService supplierService;
    //添加采购信息UI
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addUi(Model model,@RequestParam(value = "page",defaultValue = "1", required = true)Integer page1) {
        PageInfo<BuyInfo> p=buyInfoService.findByPage(page1);
        List<Supplier> suplist = supplierService.findAll();
        model.addAttribute("page",p);
        model.addAttribute("list",suplist);
        return "add";
     }

    //创建页面UI
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createUi(Model model, HttpSession session) {
        Users users = (Users) session.getAttribute("users");
        try {
            if (users != null && users.getRole().getAction().equals("电厂创建")) {
                BuyInfo buyInfo = buyInfoService.checkState(users.getId());
                if (buyInfo != null) {
                    model.addAttribute("buyinfo", buyInfo);
                }
                return "create";
            } else {
                logger.error("权限不足!");
                throw new TradeException("权限不足！非法操作");
            }
        } catch (TradeException e) {
            throw e;

        }catch (Exception e1){
            throw new TradeException(e1.getMessage());
        }


    }
    //添加采购信息到数据库
    @RequestMapping(value = "/createInfo", method = RequestMethod.POST)
    public String add(BuyInfo buyInfo,
                      @RequestParam("BJ") String bj,
                      @RequestParam("lj") String lj,
                      @RequestParam("endTimes") String endTimes,
                      @RequestParam("createTimes") String createTimes,
                      @RequestParam("stimes") String stimes,
                      @RequestParam(value = "agreePrices", defaultValue = "", required = true) String ap,
                      @RequestParam(value = "baojiaPrices", defaultValue = "", required = true) String bjp,
                      @RequestParam("etimes") String etimes, HttpSession session) throws ParseException {
        //double ,Date类型要用String接受然后转换
        try{
            buyInfoService.updateBuyInfo(bj,bjp,lj,ap,endTimes,createTimes,stimes,etimes,
                    buyInfo,session);
            return "redirect:/create";
        }
        catch (TradeException e){
            throw e;
        }catch (Exception e1){
            throw new TradeException(e1.getMessage());
        }
  }

    //提交采购信息
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(BuyInfo buyInfo,
                       @RequestParam("BJ") String bj,
                       @RequestParam("lj") String lj,
                       @RequestParam("endTimes") String endTimes,
                       @RequestParam("createTimes") String createTimes,
                       @RequestParam("stimes") String stimes,
                       @RequestParam(value = "agreePrices", defaultValue = "", required = true) String ap,
                       @RequestParam(value = "baojiaPrices", defaultValue = "", required = true) String bjp,
                       @RequestParam("etimes") String etimes, HttpSession session) throws ParseException {
        buyInfoService.updateBuyInfo(bj,bjp,lj,ap,endTimes,createTimes,stimes,etimes,
                buyInfo,session);
        buyInfoService.save(buyInfo);
        return "redirect:/add";
    }
   //展示采购信息
   @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String show(Model model,@RequestParam("id") int id){
        BuyInfo b=buyInfoService.findByIdAndCheckLevel(id);
        if(b!=null){
            model.addAttribute("buyInfo",b);
            if (b.getBaojiaPrice() != -1) {
                double price = b.getBaojiaPrice() * b.getNumber();//要缴纳的保证金计算
                model.addAttribute("price", price);
            } else {
                model.addAttribute("price", "不要求保证金。");
            }
        }

        //防止用户通过url随便传值
        else{
            return "forward:/add";
         }
           return "show";
    }
    //发布的采购信息
    @RequestMapping(value = "/findAll", method = RequestMethod.POST)
    @ResponseBody
    public List<BuyInfo> findAll() {
        List<BuyInfo> buyInfos = buyInfoService.findAll();

        return buyInfos;
    }

    //会员中心url
    @RequestMapping(value = "/member", method = RequestMethod.GET)
    public String member(Model model, HttpSession session) {
        Users users = (Users) session.getAttribute("users");

            if (users.getRole().getAction().equals("电厂审核"))
                //进入第一级审核页面
                {
                    List<BuyInfo> p = buyInfoService.findByCheckLevel0(users);
                    model.addAttribute("buyInfo", p);
                }
                //进入第二级审核页面
           else if(users.getRole().getAction().contains("分子公司")){
                List<BuyInfo> p = buyInfoService.findByCheckLevel1(users);
                model.addAttribute("buyInfo", p);
            }
            else {
                //防止用户直接通过url访问
                logger.error(users.getId() + "权限不足！");
                return "redirect:/";
                //throw new TradeException("权限不足！非法操作！");
            }


        return "check";

    }



}
