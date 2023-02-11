package model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 客服评价
 */

public class WorkerEvaluate {

    private Integer id;

    //客服id
    private Integer wid;

    //评价的用户id
    private Integer uid;

    //评价的内容
    private String content;

    //满意 非常满意
    private String type;

    private String creatAt;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWid() {
        return wid;
    }

    public void setWid(Integer wid) {
        this.wid = wid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(String creatAt) {
        this.creatAt = creatAt;
    }
}
