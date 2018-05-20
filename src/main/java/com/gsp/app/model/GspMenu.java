package com.gsp.app.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 菜单表
 * Created by lcy on 2018/5/19.
 */
@Data
@ToString
@NoArgsConstructor
public class GspMenu {
    private Long id;
    private String menuName;
    private String menuUrl;
    private Integer menuFatherId;
    private String menuFather;
    private String menuDesc;
    private Boolean enable;
    private Date createTime;
    private Date updateTime;

    public GspMenu(Long id, String menuName) {
        this.id = id;
        this.menuName = menuName;
    }
}
