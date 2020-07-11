package com.atguigu.bean;

import java.sql.Date;

/**
 * @author Yu HaiFeng
 * @Description
 * @create 2020-05-24 20:16
 */
public class OrderBean {
    private String ordername;
    private String orderemail;
    private Date orderDate;

    public OrderBean(String ordername, String orderemail, Date orderDate) {
        this.ordername = ordername;
        this.orderemail = orderemail;
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "AliasTest{" +
                "ordername='" + ordername + '\'' +
                ", orderemail='" + orderemail + '\'' +
                ", orderDate=" + orderDate +
                '}';
    }

    public String getOrdername() {
        return ordername;
    }

    public void setOrdername(String ordername) {
        this.ordername = ordername;
    }

    public String getOrderemail() {
        return orderemail;
    }

    public void setOrderemail(String orderemail) {
        this.orderemail = orderemail;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderBean() {
    }
}
