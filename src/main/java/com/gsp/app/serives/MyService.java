package com.gsp.app.serives;

import com.gsp.app.dao.UserDao;
import com.gsp.app.model.User;
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
//        System.out.println("1111");
//        return null;
        return userDao.selectAllUser();
    }

}
