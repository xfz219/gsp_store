package com.gsp.app.dao;


import com.gsp.app.model.GspMenu;
import com.gsp.app.model.User;
import org.codehaus.jackson.map.Serializers;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by finup on 2018/5/19.
 */

public interface UserDao extends BaseDao {

    List<User> selectAllUser();

    User selectUserByName(String name);

    List<GspMenu> selectMenuById(String userId);
}
