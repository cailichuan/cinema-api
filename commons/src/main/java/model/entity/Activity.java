package model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 影院活动
 */


public class Activity{

    private Long id;

    private String content;


    private Integer number;

    private String startTime;

    private String endTime;

    private  String createAt;

    public void  setId(Long id){
        this.id=id;
    }

    public Long getId() {
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
                "id:" + id +
                ", content:'" + content + '\'' +
                ", number:" + number +
                ", startTime:'" + startTime + '\'' +
                ", endTime:'" + endTime + '\'' +
                ", createAt:'" + createAt + '\'' +
                '}';
    }
}
