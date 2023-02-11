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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartVo {

    private Film film;

    private Arrangement arrangement;

    private Cart cart;
}
