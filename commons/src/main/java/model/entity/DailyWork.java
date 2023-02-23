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
    private  String createAt;

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

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String creatAt) {
        this.createAt = creatAt;
    }

    @Override
    public String toString() {
        return "DailyWork{" +
                "id:" + id +
                ", type:" + type +
                ", content:'" + content + '\'' +
                ", creatAt:'" + createAt + '\'' +
                '}';
    }
}
