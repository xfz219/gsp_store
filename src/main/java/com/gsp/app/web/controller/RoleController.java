package com.gsp.app.web.controller;

import com.gsp.app.model.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * fengzhix.xu on 2018/5/20.
 */
@Controller
@RequestMapping("/role")
@Slf4j
public class RoleController {

    @RequestMapping(value = "/selectRole", method = RequestMethod.GET)
    public Object selectRole(String id) {
        try {

        } catch (Exception e) {
            log.error("select role error", e);
        }

        return ResponseVo.ofErrorMessage();
    }
}
