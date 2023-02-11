package model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import model.entity.LeavingMessage;
import model.entity.User;

/**
 * 留言前端展示
 */
@Data
@AllArgsConstructor
public class LeavingMessageVo {

    private String id;

    private LeavingMessage leavingMessage;

    private User user;
}
