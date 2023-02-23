package mapper;


import model.entity.User;

public interface UserMapper {

    void insert(User user);

    User selectByUserName(String userName);


}
