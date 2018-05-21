package com.gsp.app.serives;

import com.google.common.collect.Lists;
import com.gsp.app.dao.UserDao;
import com.gsp.app.exception.OperationException;
import com.gsp.app.model.GspUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * fengzhix.xu on 2018/5/20.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;


    public List<GspUser> selectAllUser(GspUser pojo) {
        return userDao.selectAllUser(pojo);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean updateUser(GspUser user) {
        return false;
    }


    public GspUser selectOne(String id) {
        return null;
    }


    @Transactional(rollbackFor = Exception.class)
    public boolean addUser(GspUser user) {
        return false;
    }

}
