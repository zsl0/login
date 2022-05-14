package com.zsl.entity;

import com.baomidou.mybatisplus.annotation.TableName;


@TableName("t_role_url")
public class RoleUrl {

  private Long roleId;
  private Long urlId;


  public Long getRoleId() {
    return roleId;
  }

  public void setRoleId(Long roleId) {
    this.roleId = roleId;
  }


  public Long getUrlId() {
    return urlId;
  }

  public void setUrlId(Long urlId) {
    this.urlId = urlId;
  }

}
