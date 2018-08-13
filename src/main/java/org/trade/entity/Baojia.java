package org.trade.entity;


import java.util.Date;

public class Baojia {
    private Integer id;



    private String price;

    private Integer number;

    private Integer bid;

    private Integer checkLevel;

    private String unitPrice;

    private String transportPrice;

    private String createArea;

    private String sendArea;

    private BuyInfo buyInfo;
    private Date create_timer;

    public Date getCreate_timer() {
        return create_timer;
    }

    public void setCreate_timer(Date create_timer) {
        this.create_timer = create_timer;
    }

    public BuyInfo getBuyInfo() {
        return buyInfo;
    }

    public void setBuyInfo(BuyInfo buyInfo) {
        this.buyInfo = buyInfo;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getCheckLevel() {
        return checkLevel;
    }

    public void setCheckLevel(Integer checkLevel) {
        this.checkLevel = checkLevel;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getTransportPrice() {
        return transportPrice;
    }

    public void setTransportPrice(String transportPrice) {
        this.transportPrice = transportPrice;
    }

    public String getCreateArea() {
        return createArea;
    }

    public void setCreateArea(String createArea) {
        this.createArea = createArea == null ? null : createArea.trim();
    }

    public String getSendArea() {
        return sendArea;
    }

    public void setSendArea(String sendArea) {
        this.sendArea = sendArea == null ? null : sendArea.trim();
    }

    @Override
    public String toString() {
        return "Baojia{" +
                "id=" + id +
                ", price='" + price + '\'' +
                ", number=" + number +
                ", bid=" + bid +
                ", checkLevel=" + checkLevel +
                ", unitPrice=" + unitPrice +
                ", transportPrice=" + transportPrice +
                ", createArea='" + createArea + '\'' +
                ", sendArea='" + sendArea + '\'' +
                ", buyInfo=" + buyInfo +
                '}';
    }
}