package org.trade.entity;

public class Gys {
    private Integer id;

    private String name;

    private String type;

    private String hot;

    private Integer uid;
    private Users users;

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot == null ? null : hot.trim();
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Gys{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", hot='" + hot + '\'' +
                ", uid=" + uid +
                ", users=" + users +
                '}';
    }
}