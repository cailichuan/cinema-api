package model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 电影的排片 播放场次 开放多少个座位
 */

public class Arrangement {

    private Long id;

    //电影id
    private Long fid;

    //电影名
    private String name;

    //开放多少座位
    private Integer seatNumber;

    //价格
    private Double price;

    //放映类型 2D 3D
    private  String type;

    //电影开始时间 2021-01-01
    private String date;

    //电影开始时间 18：30：00
    private String startTime;

    //票房统计
    private String boxOffice;

    //结束时间
    private String endTime;

    //创建人
    private String founder;

    //创建时间
    private  String createAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Arrangement{" +
                "id:" + id +
                ", fid:" + fid +
                ", name:'" + name + '\'' +
                ", seatNumber:" + seatNumber +
                ", price:" + price +
                ", type:'" + type + '\'' +
                ", date:'" + date + '\'' +
                ", startTime:'" + startTime + '\'' +
                ", boxOffice:'" + boxOffice + '\'' +
                ", endTime:'" + endTime + '\'' +
                ", founder:'" + founder + '\'' +
                ", createAt:'" + createAt + '\'' +
                '}';
    }
}
