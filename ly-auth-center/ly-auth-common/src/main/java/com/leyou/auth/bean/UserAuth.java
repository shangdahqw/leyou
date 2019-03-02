package com.leyou.auth.bean;

/**
 * @author chenyilei
 * @date 2018/11/21-19:07 hello everyone
 */
public class UserAuth {

  private Long id;

  private String username;

  public UserAuth() {}

  public UserAuth(Long id, String username) {
    this.id = id;
    this.username = username;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
