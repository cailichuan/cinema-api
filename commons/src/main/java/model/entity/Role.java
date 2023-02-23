package model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 权限角色
 */

public class Role {

    private Long id;

    private Long wid;

    private String value;

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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id:" + id +
                ", wid:" + wid +
                ", value:'" + value + '\'' +
                ", createAt:'" + createAt + '\'' +
                '}';
    }


    public Role(){

    }
    public Role(Long id, Long wid, String value, String createAt) {
        this.id = id;
        this.wid = wid;
        this.value = value;
        this.createAt = createAt;
    }
}
