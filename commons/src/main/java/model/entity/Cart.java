package model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 购物车
 */

public class Cart {

    private Long id;

    //用户id
    private Long uid;

    //场次id
    private Long aid;

    //座位号
    private String seats;

    private String phone;

    private Integer status;

    //金额
    private Double price;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id:" + id +
                ", uid:" + uid +
                ", aid:" + aid +
                ", seats:'" + seats + '\'' +
                ", phone:'" + phone + '\'' +
                ", status:" + status +
                ", price:" + price +
                '}';
    }
}
