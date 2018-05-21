package com.gsp.app.dao;


import com.gsp.app.model.GspRole;
import com.gsp.app.model.GspUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色
 */

public interface RoleDao extends BaseDao {

    List<GspUser> selectAllRole(@Param("pojo") GspRole pojo);

    GspUser findRoleById(@Param("id") Long id);

    void addRole(GspRole gspRole);

    void updateRole(GspRole gspRole);

    void updateEnabledById(@Param("id")long id, @Param("enabled")boolean enabled);
}
