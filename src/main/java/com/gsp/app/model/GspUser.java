package com.gsp.app.model;

import com.gsp.app.common.page.mybatis.Page;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 * Created by lcy on 2018/5/19.
 */
@Data
@ToString
public class GspUser implements Serializable {
    private Long id;
    private String user;
    private String password;
    private String email;
    private String name;
    private String mobile;
    private String org;
    private Boolean enable;
    private Date createTime;
    private Date updateTime;

}
