package org.trade.entity;

import java.util.Date;

public class Supplier {
    private Integer id;

    private String coalType;

    private String baseLowCalorificValue;

    private String airDryBasisMoisture;

    private String dryBaseHighCalorificValue;

    private Double price;

    private Integer number;

    private String deliveryMethod;

    private String tradingLocations;

    private Date releaseEndTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCoalType() {
        return coalType;
    }

    public void setCoalType(String coalType) {
        this.coalType = coalType == null ? null : coalType.trim();
    }

    public String getBaseLowCalorificValue() {
        return baseLowCalorificValue;
    }

    public void setBaseLowCalorificValue(String baseLowCalorificValue) {
        this.baseLowCalorificValue = baseLowCalorificValue == null ? null : baseLowCalorificValue.trim();
    }

    public String getAirDryBasisMoisture() {
        return airDryBasisMoisture;
    }

    public void setAirDryBasisMoisture(String airDryBasisMoisture) {
        this.airDryBasisMoisture = airDryBasisMoisture == null ? null : airDryBasisMoisture.trim();
    }

    public String getDryBaseHighCalorificValue() {
        return dryBaseHighCalorificValue;
    }

    public void setDryBaseHighCalorificValue(String dryBaseHighCalorificValue) {
        this.dryBaseHighCalorificValue = dryBaseHighCalorificValue == null ? null : dryBaseHighCalorificValue.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod == null ? null : deliveryMethod.trim();
    }

    public String getTradingLocations() {
        return tradingLocations;
    }

    public void setTradingLocations(String tradingLocations) {
        this.tradingLocations = tradingLocations == null ? null : tradingLocations.trim();
    }

    public Date getReleaseEndTime() {
        return releaseEndTime;
    }

    public void setReleaseEndTime(Date releaseEndTime) {
        this.releaseEndTime = releaseEndTime;
    }
}