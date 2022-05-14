package com.zsl.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zsl.entity.base.BaseBean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@TableName("t_user")
public class User extends BaseBean implements Serializable {
  private String userAccount;
  private String userUsername;
  private String userPassword;
  private String userEmail;
  private String userPhone;
  private Long userGender;
  private String userAddr;
  private Long isLock;
  private Long isAdmin;


  private Role role;

  private List<Article> articles;

  public List<Article> getArticles() {
    return articles;
  }

  public void setArticles(List<Article> articles) {
    this.articles = articles;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public String getUserAccount() {
    return userAccount;
  }

  public void setUserAccount(String userAccount) {
    this.userAccount = userAccount;
  }


  public String getUserUsername() {
    return userUsername;
  }

  public void setUserUsername(String userUsername) {
    this.userUsername = userUsername;
  }


  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }


  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }


  public String getUserPhone() {
    return userPhone;
  }

  public void setUserPhone(String userPhone) {
    this.userPhone = userPhone;
  }


  public Long getUserGender() {
    return userGender;
  }

  public void setUserGender(Long userGender) {
    this.userGender = userGender;
  }


  public String getUserAddr() {
    return userAddr;
  }

  public void setUserAddr(String userAddr) {
    this.userAddr = userAddr;
  }


  public Long getIsLock() {
    return isLock;
  }

  public void setIsLock(Long isLock) {
    this.isLock = isLock;
  }


  public Long getIsAdmin() {
    return isAdmin;
  }

  public void setIsAdmin(Long isAdmin) {
    this.isAdmin = isAdmin;
  }
}
