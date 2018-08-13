package org.trade.util;

import java.text.NumberFormat;

public class DoubleToString {
    public static String dToS(double d){
        Double dou_obj = new Double(d);
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        return  nf.format(dou_obj);
    }
  public static void main(String[] args) {
  dToS(1.2);
  }
}
