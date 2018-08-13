package org.trade.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.trade.entity.Supplier;
import org.trade.service.Impl.SupplierService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@Controller
public class SupplierAction {
    @Autowired
    private SupplierService supplierService;
     @RequestMapping(value = "/supplier",method = RequestMethod.GET)
     public String addSupplier(){
         return "addSupplier";
     }
    @RequestMapping(value = "/findAllList",method = RequestMethod.POST)
    @ResponseBody
    public  List<Supplier> selectByPrimaryKey(
            Model model) {
            List<Supplier> suplist = supplierService.findAll();

        return suplist;
    }

    @RequestMapping(value = "/insert12",method = RequestMethod.POST)
    public String add(Supplier suplier, Model model,
                      @RequestParam("a") String a,
                      @RequestParam("b") String b,
                      @RequestParam("c") String c,
                      @RequestParam("d") String d,
                      @RequestParam("e") Double e,
                      @RequestParam("f") Integer f,
                      @RequestParam("g") String g,
                      @RequestParam("h") String h,
                      @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
                      HttpServletRequest request
    ) {

        suplier.setCoalType(a);

        suplier.setBaseLowCalorificValue(b);
        suplier.setAirDryBasisMoisture(c);
        suplier.setDryBaseHighCalorificValue(d);
        suplier.setPrice(e);
        suplier.setNumber(f);
        suplier.setDeliveryMethod(g);
        suplier.setTradingLocations(h);
        suplier.setReleaseEndTime(date);
        supplierService.add(suplier);
        return "redirect:/add";
    }

}
