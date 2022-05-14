package com.zsl.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.zsl.entity.base.BaseBean;

import java.io.Serializable;

@TableName("t_authority")
public class Authority extends BaseBean implements Serializable {
  // 可访问的路径
  private String url;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }
}