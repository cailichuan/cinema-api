package model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import model.entity.Arrangement;
import model.entity.Film;

import java.util.List;


public class ArrangementVo {

    public List<Arrangement> arrangements;

    private Film film;

    public ArrangementVo(List<Arrangement> arrangements, Film film) {
        this.arrangements = arrangements;
        this.film = film;
    }

    public List<Arrangement> getArrangements() {
        return arrangements;
    }

    public void setArrangements(List<Arrangement> arrangements) {
        this.arrangements = arrangements;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
