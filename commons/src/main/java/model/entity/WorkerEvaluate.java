package model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 客服评价
 */

public class WorkerEvaluate {

    private Long id;

    //客服id
    private Long wid;

    //评价的用户id
    private Long uid;

    //评价的内容
    private String content;

    //满意 非常满意
    private Integer type;

    private String createAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWid() {
        return wid;
    }

    public void setWid(Long wid) {
        this.wid = wid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "WorkerEvaluate{" +
                "id:" + id +
                ", wid:" + wid +
                ", uid:" + uid +
                ", content:'" + content + '\'' +
                ", type:" + type +
                ", createAt:'" + createAt + '\'' +
                '}';
    }
}
