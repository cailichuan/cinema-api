package model.vo;

import model.entity.Arrangement;
import model.entity.Film;

public class OneArrangementVo {

    private Film film;

    private Arrangement arrangement;


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


    public OneArrangementVo(Film film, Arrangement arrangement) {
        this.film = film;
        this.arrangement = arrangement;
    }
}
