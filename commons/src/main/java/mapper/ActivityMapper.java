package mapper;


import model.entity.Activity;

import java.util.List;

public interface ActivityMapper{


    void insert(Activity activity);

    Activity selectById(Integer id);

    List<Activity> selectList();

    void deleteById(Integer id);

}
