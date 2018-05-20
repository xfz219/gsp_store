package com.gsp.app.dao;


import com.gsp.app.model.GspMenu;
import com.gsp.app.model.GspUser;
import com.gsp.app.model.User;

import java.util.List;

/**
 * Created by finup on 2018/5/19.
 */

public interface UserDao extends BaseDao {

    List<User> selectAllUser();

    User selectUserByName(String name);

    List<GspMenu> selectMenuById(String userId);

    GspMenu selectUserById(String id);

    int updateUser(GspUser user);

    int addUser(GspUser user);
}
