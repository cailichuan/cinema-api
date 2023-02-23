package model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户
 */

public class User {

    private Long id;

    private String username;

    private String password;

    //性别
    private Integer gender;

    //昵称
    private String nickname;

    //邮箱
    private String email;

    //生日
    private String birthday;

    //个人简介
    private String info;

    //用户头像id
    private Long upid;

    private String createAt;

    private String updateAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Long getUpid() {
        return upid;
    }

    public void setUpid(Long upid) {
        this.upid = upid;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }


    public User(Long id, String username, String password, Integer gender, String nickname, String email, String birthday, String info, Long upid, String createAt, String updateAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.nickname = nickname;
        this.email = email;
        this.birthday = birthday;
        this.info = info;
        this.upid = upid;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id:" + id +
                ", username:'" + username + '\'' +
                ", password:'" + password + '\'' +
                ", gender:" + gender +
                ", nickname:'" + nickname + '\'' +
                ", email:'" + email + '\'' +
                ", birthday:'" + birthday + '\'' +
                ", info:'" + info + '\'' +
                ", upid:" + upid +
                ", createAt:'" + createAt + '\'' +
                ", updateAt:'" + updateAt + '\'' +
                '}';
    }
}
