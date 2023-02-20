package model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.entity.Arrangement;
import model.entity.Cart;
import model.entity.Film;

/**
 * 购物车前端展示
 */

public class CartVo {

    private Film film;

    private Arrangement arrangement;

    private Cart cart;

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

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public CartVo(Film film, Arrangement arrangement, Cart cart) {
        this.film = film;
        this.arrangement = arrangement;
        this.cart = cart;
    }

    public CartVo(){

    }
}
