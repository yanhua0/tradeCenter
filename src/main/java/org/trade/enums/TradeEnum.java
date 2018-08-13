package org.trade.enums;
//支付结果枚举
public enum TradeEnum {
     FAIL(0,"支付失败！"),
    NOPAY(-1,"请先交纳保证金!"),
    NOMONEY(3,"余额不足,请充值!"),
    SUCCESS(1,"支付成功！"),
    UPDATA(2,"提交报价信息成功！"),
     NOTIME(4,"该订单有效报价时间已过!");
    private int state;
    private String stateinfo;

    public int getState() {
        return state;
    }

    public String getStateinfo() {
        return stateinfo;
    }

    TradeEnum(int state, String stateinfo) {
        this.state = state;
        this.stateinfo = stateinfo;
    }
}
