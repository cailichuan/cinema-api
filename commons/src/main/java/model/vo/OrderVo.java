package model.vo;

import lombok.Data;
import model.entity.Arrangement;
import model.entity.Film;
import model.entity.Order;
import model.entity.User;

/**
 * 订单前端展示
 */

public class OrderVo {
    private Order order;

    private User user;

    private Film film;

    private Arrangement arrangement;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Arrangement getArrangement() {
        return arrangement;
    }

    public void setArrangement(Arrangement arrangement) {
        this.arrangement = arrangement;
    }
}
