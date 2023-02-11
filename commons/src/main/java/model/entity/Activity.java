package model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 影院活动
 */

public class Activity {

    private Integer id;

    private String content;


    private Integer number;

    private String startTime;

    private String endTime;

    private  String createAt;

    public void  setId(Integer id){
        this.id=id;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", number=" + number +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", createAt='" + createAt + '\'' +
                '}';
    }
}