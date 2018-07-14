package cn.pojo;

import java.util.Date;
import java.util.List;

public class User {

  private int id;
  private String username;
  private Date birthday;
  private String sex;
  private String address;

  private List<Orders> orderList;

  public User() {
  }

  public User(int id, String username, Date birthday, String sex, String address) {
    this.id = id;
    this.username = username;
    this.birthday = birthday;
    this.sex = sex;
    this.address = address;
  }

  public User(String username, Date birthday, String sex, String address) {
    this.username = username;
    this.birthday = birthday;
    this.sex = sex;
    this.address = address;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public List<Orders> getOrderList() {
    return orderList;
  }

  public void setOrderList(List<Orders> orderList) {
    this.orderList = orderList;
  }

  @Override
  public String toString() {
    return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", birthday=" + birthday +
            ", sex='" + sex + '\'' +
            ", address='" + address + '\'' +
            ", orderList=" + orderList +
            '}';
  }
}
