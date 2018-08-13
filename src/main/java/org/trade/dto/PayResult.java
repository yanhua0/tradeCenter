package org.trade.dto;

import org.trade.enums.TradeEnum;

public class PayResult<T> {
    private int state;
    private String stateinfo;
    private T data;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateinfo() {
        return stateinfo;
    }

    public void setStateinfo(String stateinfo) {
        this.stateinfo = stateinfo;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public PayResult(TradeEnum tradeEnum, T data) {
        this.state = tradeEnum.getState();
        this.stateinfo = tradeEnum.getStateinfo();
        this.data = data;
    }

    public PayResult(TradeEnum tradeEnum) {
        this.state = tradeEnum.getState();
        this.stateinfo = tradeEnum.getStateinfo();
    }
}
