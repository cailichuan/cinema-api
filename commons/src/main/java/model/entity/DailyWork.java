package model.entity;

import lombok.Data;

/**
 * 员工的每日工作
 */

public class DailyWork {

    private Long id;

    //重要 一般 非常重要
    private Integer type;

    //工作内容
    private String content;

    //上传时间
    private  String creatAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(String creatAt) {
        this.creatAt = creatAt;
    }
}
