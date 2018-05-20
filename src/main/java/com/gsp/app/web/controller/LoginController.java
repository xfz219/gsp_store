package com.gsp.app.web.controller;

import com.gsp.app.constant.ErrorEnum;
import com.gsp.app.constant.MenuEnum;
import com.gsp.app.context.AppContext;
import com.gsp.app.model.GspMenu;
import com.gsp.app.model.Response;
import com.gsp.app.model.User;
import com.gsp.app.serives.MyService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * fengzhix.xu on 2018/5/19.
 */

@Controller
public class LoginController {
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private MyService myService;


    /**
     * 验证登录
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(User user) {
        try {

            if (!checkUser(user)) {
                return Response.fail(ErrorEnum.USER_EMPTY);
            }

            if (!myService.isLoginSUc(user)) {
                logger.error("login error {}:", user.toString());
                return Response.fail(ErrorEnum.PWD_ERROR);
            }

            return Response.suc(user);

        } catch (Exception e) {
            logger.error("login error", e);
            return Response.fail(ErrorEnum.FAIL);
        }
    }


    @RequestMapping(value = "/treeView", method = RequestMethod.GET)
    @ResponseBody
    public String index(@RequestParam(value = "id") String id) {
        try {

            return "[{\"id\":\"938\",\"text\":\"个贷APP监控\",\"iconCls\":\"\",\"checked\":false,\"attributes\":null,\"children\":[],\"state\":\"closed\",\"pid\":\"7602\",\"sort\":1.0},{\"id\":\"949\",\"text\":\"业务管理\",\"iconCls\":\"\",\"checked\":false,\"attributes\":null,\"children\":[],\"state\":\"closed\",\"pid\":\"7602\",\"sort\":1.0},{\"id\":\"2311\",\"text\":\"奖品管理\",\"iconCls\":null,\"checked\":false,\"attributes\":null,\"children\":[],\"state\":\"closed\",\"pid\":\"7602\",\"sort\":3.0}]";
//            if (StringUtils.isNotBlank(id)) {
//                Map<String, Collection<List<GspMenu>>> menuEnumCollectionMap = myService.getMenuById(id);
//                return MapUtils.isEmpty(menuEnumCollectionMap) ?
//                        Response.fail(ErrorEnum.FAIL) : Response.suc(menuEnumCollectionMap);
//            }

        } catch (Exception e) {
            logger.error("query index error", e);
        }
        return Response.fail(ErrorEnum.FAIL);
    }


    private boolean checkUser(User user) {
        return user == null
                || StringUtils.isBlank(user.getUserName())
                || StringUtils.isBlank(user.getPassWord());
    }


}
