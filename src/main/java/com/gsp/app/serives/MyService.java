package com.gsp.app.serives;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.gsp.app.constant.MenuEnum;
import com.gsp.app.dao.UserDao;
import com.gsp.app.model.GspMenu;
import com.gsp.app.model.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by finup on 2018/5/19.
 */
@Service
public class MyService {

    @Autowired
    private UserDao userDao;


    public List<User> userList() {
//        return userDao.selectAllUser();
        return new ArrayList<>();
    }


    public boolean isLoginSUc(User user) {
//        User u = userDao.selectUserByName(user.getUserName());
        User u = null;
        return (u != null
                && StringUtils.equals(u.getPassWord(), user.getPassWord()));
    }


    public List<GspMenu> getMenuById(String userId) {
        List<GspMenu> result = Lists.newArrayList();
        Map<Integer, GspMenu> gspMenus = Maps.newHashMap();

        List<GspMenu> gspMenu = userDao.selectMenuById(userId);
        if (CollectionUtils.isNotEmpty(gspMenu)) {
            gspMenu.forEach(menu -> {
                int parentId = menu.getMenuFatherId();
                MenuEnum menuEnum = MenuEnum.getMenuById(parentId);
                gspMenus.put(menuEnum.getId(), new GspMenu(NumberUtils.toLong(menuEnum.getId() + ""), menuEnum.getId(), menuEnum.getName()));

            });
            result.addAll(gspMenu);
            gspMenus.forEach((k, v) -> result.add(v));
        }

        return result;
    }


}
