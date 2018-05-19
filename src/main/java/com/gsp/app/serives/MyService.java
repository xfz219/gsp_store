package com.gsp.app.serives;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.gsp.app.constant.MenuEnum;
import com.gsp.app.context.AppContext;
import com.gsp.app.dao.UserDao;
import com.gsp.app.model.GspMenu;
import com.gsp.app.model.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
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


    public Map<MenuEnum, Collection<List<GspMenu>>> getMenuById(String userId) {
        List<GspMenu> gspMenu = userDao.selectMenuById(userId);
        Multimap<MenuEnum, List<GspMenu>> enumListMap = HashMultimap.create();
        if (gspMenu != null) {
            for (GspMenu menu : gspMenu) {
                if (menu != null
                        && MenuEnum.getParentMenu().contains(menu.getId())) {
                    enumListMap.put(MenuEnum.getMenuById(menu.getMenuFatherId()), gspMenu);
                }

            }
        }
        return enumListMap.asMap();
    }


}
