package model.vo;

import model.entity.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

public class ActiveUserVo {
    private User user;

    private  Integer Number;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getNumber() {
        return Number;
    }

    public void setNumber(Integer number) {
        Number = number;
    }

    public ActiveUserVo(User user, Integer number) {
        this.user = user;
        Number = number;
    }

    public ActiveUserVo() {

    }
}
