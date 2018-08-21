package org.trade.entity;



public class Users {
    private Integer id;

    private Integer rid;

    private String username;

    private String password;

    private String name;

    private double money;

    private double freezeMoney;

    private double freezeMoney2;
   private int sno;

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getFreezeMoney() {
        return freezeMoney;
    }

    public void setFreezeMoney(double freezeMoney) {
        this.freezeMoney = freezeMoney;
    }
    public double getFreezeMoney2() {
        return freezeMoney2;
    }

    public void setFreezeMoney2(double freezeMoney2) {
        this.freezeMoney2 = freezeMoney2;
    }

    public Users(double money, double freezeMoney) {
        this.money = money;
        this.freezeMoney = freezeMoney;
    }
    public Users() {

    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", rid=" + rid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", money=" + money +
                ", freezeMoney=" + freezeMoney +
                ", freezeMoney2=" + freezeMoney2 +
                ", sno=" + sno +
                ", role=" + role +
                '}';
    }
}