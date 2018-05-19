package com.gsp.app.serives;

import com.gsp.app.context.AppContext;
import com.gsp.app.dao.UserDao;
import com.gsp.app.model.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by finup on 2018/5/19.
 */
@Service
public class MyService {

    @Autowired
    private UserDao userDao;


    public List<User> userList() {
        return userDao.selectAllUser();
    }


    public boolean isLoginSUc(User user) {
        User u = userDao.selectUserByName(user.getUserName());
        if (u != null
                && StringUtils.equals(u.getPassWord(), user.getPassWord())) {
            AppContext.setUser(u);
            return true;
        }
        return false;
    }


}
