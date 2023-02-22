package model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import model.entity.LeavingMessage;
import model.entity.User;

/**
 * 留言前端展示
 */

public class LeavingMessageVo {

    private Long id;

    private LeavingMessage leavingMessage;

    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LeavingMessage getLeavingMessage() {
        return leavingMessage;
    }

    public void setLeavingMessage(LeavingMessage leavingMessage) {
        this.leavingMessage = leavingMessage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LeavingMessageVo(Long id, LeavingMessage leavingMessage, User user) {
        this.id = id;
        this.leavingMessage = leavingMessage;
        this.user = user;
    }
}
