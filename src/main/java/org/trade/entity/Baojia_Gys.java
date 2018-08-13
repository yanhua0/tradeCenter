package org.trade.entity;

public class Baojia_Gys {
    private Integer id;

    private Integer bjid;

    private Integer gid;
    private Baojia baojia;
    private Gys gys;

    public Baojia getBaojia() {
        return baojia;
    }

    public void setBaojia(Baojia baojia) {
        this.baojia = baojia;
    }

    public Gys getGys() {
        return gys;
    }

    public void setGys(Gys gys) {
        this.gys = gys;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBjid() {
        return bjid;
    }

    public void setBjid(Integer bjid) {
        this.bjid = bjid;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    @Override
    public String toString() {
        return "Baojia_Gys{" +
                "id=" + id +
                ", bjid=" + bjid +
                ", gid=" + gid +
                ", baojia=" + baojia +
                ", gys=" + gys +
                '}';
    }
}