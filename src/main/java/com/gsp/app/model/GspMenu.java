package com.gsp.app.model;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 菜单表
 * Created by lcy on 2018/5/19.
 */
@Data
@ToString
public class GspMenu {
    private Long id;
    private String menuName;
    private String menuUrl;
    private String menuFather;
    private String menuDesc;
    private Boolean enable;
    private Date createTime;
    private Date updateTime;

}
