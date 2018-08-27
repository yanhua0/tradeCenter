package org.trade.entity;

import java.util.Date;

public class BuyInfo {
    private Integer id;

    private Integer uid;

    private Integer checkLevel;

    private Date endTime;

    private String createCompany;

    private String sno;

    private String createPerson;

    private String signPerson;

    private Date createTime;

    private String type1;

    private String type2;

    private Integer number;

    private String transport;

    private double highPrice;

    private double lowPrice;

    private String explains;

    private String payType;

    private double baojiaPrice;

    private double agreePrice;

    private Date stime;

    private Date etime;

    private String applicant;

    private String place;

    private String calculateType;

    private String checkgoodsType;

    private String hot1;

    private String air;

    private String hot2;

    private String remark;
    private String opo;
    private String advice;
    private Users users;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public String getOpo() {
        return opo;
    }

    public void setOpo(String opo) {
        this.opo = opo;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getCheckLevel() {
        return checkLevel;
    }

    public void setCheckLevel(Integer checkLevel) {
        this.checkLevel = checkLevel;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCreateCompany() {
        return createCompany;
    }

    public void setCreateCompany(String createCompany) {
        this.createCompany = createCompany == null ? null : createCompany.trim();
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno == null ? null : sno.trim();
    }

    public String getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(String createPerson) {
        this.createPerson = createPerson == null ? null : createPerson.trim();
    }

    public String getSignPerson() {
        return signPerson;
    }

    public void setSignPerson(String signPerson) {
        this.signPerson = signPerson == null ? null : signPerson.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1 == null ? null : type1.trim();
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2 == null ? null : type2.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport == null ? null : transport.trim();
    }

    public double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(double highPrice) {
        this.highPrice = highPrice;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public String getExplains() {
        return explains;
    }

    public void setExplains(String explains) {
        this.explains = explains == null ? null : explains.trim();
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public double getBaojiaPrice() {
        return baojiaPrice;
    }

    public void setBaojiaPrice(double baojiaPrice) {
        this.baojiaPrice = baojiaPrice;
    }

    public double getAgreePrice() {
        return agreePrice;
    }

    public void setAgreePrice(double agreePrice) {
        this.agreePrice = agreePrice;
    }

    public Date getStime() {
        return stime;
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }

    public Date getEtime() {
        return etime;
    }

    public void setEtime(Date etime) {
        this.etime = etime;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant == null ? null : applicant.trim();
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place == null ? null : place.trim();
    }

    public String getCalculateType() {
        return calculateType;
    }

    public void setCalculateType(String calculateType) {
        this.calculateType = calculateType == null ? null : calculateType.trim();
    }

    public String getCheckgoodsType() {
        return checkgoodsType;
    }

    public void setCheckgoodsType(String checkgoodsType) {
        this.checkgoodsType = checkgoodsType == null ? null : checkgoodsType.trim();
    }

    public String getHot1() {
        return hot1;
    }

    public void setHot1(String hot1) {
        this.hot1 = hot1 == null ? null : hot1.trim();
    }

    public String getAir() {
        return air;
    }

    public void setAir(String air) {
        this.air = air == null ? null : air.trim();
    }

    public String getHot2() {
        return hot2;
    }

    public void setHot2(String hot2) {
        this.hot2 = hot2 == null ? null : hot2.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public String toString() {
        return "BuyInfo{" +
                "id=" + id +
                ", uid=" + uid +
                ", checkLevel=" + checkLevel +
                ", endTime=" + endTime +
                ", createCompany='" + createCompany + '\'' +
                ", sno='" + sno + '\'' +
                ", advice='" + advice + '\'' +
                ", users=" + users +
                '}';
    }
}