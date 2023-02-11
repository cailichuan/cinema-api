package model.vo;

import lombok.Data;
import model.entity.Arrangement;
import model.entity.Film;
import model.entity.Order;
import model.entity.User;

/**
 * 订单前端展示
 */
@Data
public class OrderVo {
    private Order order;

    private User user;

    private Film film;

    private Arrangement arrangement;
}
