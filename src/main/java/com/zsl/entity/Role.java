package com.zsl.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.zsl.entity.base.BaseBean;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("t_role")
public class Role extends BaseBean implements Serializable {
  private String roleName;
  private String roleDesc;

  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }


  public String getRoleDesc() {
    return roleDesc;
  }

  public void setRoleDesc(String roleDesc) {
    this.roleDesc = roleDesc;
  }
}
