package model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.entity.User;

/**
 * 电影评分前端展示
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmEvaluateVo {

    private String id;

    private FilmEvaluateVo filmEvaluateVo;

    private User user;
}
