package model.vo;

import lombok.Data;
import model.entity.User;
import model.entity.WorkerEvaluate;

/**
 * 客服评价前端展示
 */
@Data
public class WorkerEvaluateVo {

    private String id;

    private WorkerEvaluate workerEvaluate;

    private User user;
}
