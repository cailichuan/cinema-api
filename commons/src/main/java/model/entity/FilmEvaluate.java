package model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 电影评价
 */


public class FilmEvaluate {

    private Long id;

    //电影id
    private Long fid;

    //用户id
    private Long uid;

    //星级
    private Integer star;

    //评语
    private String comment;

    private String createAt;

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

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }


    @Override
    public String toString() {
        return "FilmEvaluate{" +
                "id:" + id +
                ", fid:" + fid +
                ", uid:" + uid +
                ", star:" + star +
                ", comment:'" + comment + '\'' +
                ", createAt:'" + createAt + '\'' +
                '}';
    }
}
