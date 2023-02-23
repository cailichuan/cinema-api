package model.dto;


import org.springframework.web.multipart.MultipartFile;

/**
 * 用户注册信息
 */
public class RegisterDto {

    //用户名
    private String username;

    //密码
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



    //接口幂等性的uuid
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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


    public RegisterDto(String username, String password, Integer gender, String nickname, String email, String birthday, String info) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.nickname = nickname;
        this.email = email;
        this.birthday = birthday;
        this.info = info;
    }

    public RegisterDto() {
    }


    @Override
    public String toString() {
        return "RegisterDto{" +
                "username:'" + username + '\'' +
                ", password:'" + password + '\'' +
                ", gender:" + gender +
                ", nickname:'" + nickname + '\'' +
                ", email:'" + email + '\'' +
                ", birthday:'" + birthday + '\'' +
                ", info:'" + info + '\'' +
                ", uuid:'" + uuid + '\'' +
                '}';
    }
}
