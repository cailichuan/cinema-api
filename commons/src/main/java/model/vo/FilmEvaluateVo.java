package model.vo;


import model.entity.FilmEvaluate;
import model.entity.User;

/**
 * 电影评分前端展示
 */


public class FilmEvaluateVo {

    private Long id;

    private FilmEvaluate filmEvaluate;

    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FilmEvaluate getFilmEvaluate() {
        return filmEvaluate;
    }

    public void setFilmEvaluate(FilmEvaluate filmEvaluate) {
        this.filmEvaluate = filmEvaluate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public FilmEvaluateVo(Long id, FilmEvaluate filmEvaluate, User user) {
        this.id = id;
        this.filmEvaluate = filmEvaluate;
        this.user = user;
    }
}
