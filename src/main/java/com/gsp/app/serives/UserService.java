package com.gsp.app.serives;

import com.google.common.collect.Lists;
import com.gsp.app.dao.GspUserRoleDao;
import com.gsp.app.dao.RoleDao;
import com.gsp.app.dao.UserDao;
import com.gsp.app.exception.OperationException;
import com.gsp.app.model.GspRole;
import com.gsp.app.model.GspUser;
import com.gsp.app.model.GspUserRole;
import com.gsp.app.model.GspUserRoleVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * fengzhix.xu on 2018/5/20.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private GspUserRoleDao gspUserRoleDao;
    @Autowired
    private RoleDao roleDao;


    public GspUser selectUserByName(String userName){
        return userDao.findUserByName(userName);
    }


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

    /**
     * 启用、禁用
     */
    public void updateEnabledById(long id, boolean enable) {
        userDao.updateEnabledById(id, enable);
    }

    public List<GspUserRoleVo> queryUserRole(Long userId) {
        List<GspUserRoleVo> list = new ArrayList<>();

        List<GspUserRole> gspUserRoles = gspUserRoleDao.findUserRoleByUserId(userId);
        if (!gspUserRoles.isEmpty()){
            for (GspUserRole gspUserRole : gspUserRoles){
                GspUserRoleVo gspUserRoleVo = new GspUserRoleVo();
                BeanUtils.copyProperties(gspUserRole, gspUserRoleVo);

                GspRole gspRole = roleDao.findRoleById(gspUserRoleVo.getRoleId());

                gspUserRoleVo.setRoleName(gspRole.getRoleName());
                gspUserRoleVo.setRoleDesc(gspRole.getRoleDesc());

                list.add(gspUserRoleVo);
            }
        }
        return list;
    }
}
