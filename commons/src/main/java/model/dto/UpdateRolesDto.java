package model.dto;


import lombok.Data;
import model.entity.Role;

import java.util.List;


public class UpdateRolesDto {

    private Integer uid;

    private List<Role> roles;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
