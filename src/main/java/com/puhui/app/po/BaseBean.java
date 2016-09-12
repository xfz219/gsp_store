package com.puhui.app.po;

import java.io.Serializable;

/**
 * 基础类
 * @author xiaobowen
 *
 */
public abstract class BaseBean implements Serializable{
    
    private static final long serialVersionUID = -3283481444757010682L;

    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        boolean flag;
        if(this == obj){
            flag = true;
        }else if(null == obj){
            flag = false;
        }else if(this.getClass() != obj.getClass()){
            flag = false;
        }else{
            BaseBean other = (BaseBean) obj;
            if(this.id == null && other.id != null){
                flag = false;
            }else if(this.id != null && !this.id.equals(other.id)){
                flag = false;
            }else{
                flag = true;
            }
        }
        return flag;
    }
}
