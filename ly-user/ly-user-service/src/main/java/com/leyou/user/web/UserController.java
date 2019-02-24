package com.leyou.user.web;

import com.leyou.user.pojo.User;
import com.leyou.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

/**
 * @author chenyilei
 * @date 2018/11/20-12:48 hello everyone
 */
@RestController
public class UserController {

  @Autowired UserService userService;
  /**
   * 校验数据是否可用
   *
   * @param data
   * @param type
   * @return
   */
  @GetMapping("/check/{data}/{type}")
  public ResponseEntity<Boolean> checkUserData(
      @PathVariable("data") String data, @PathVariable(value = "type") Integer type)
      throws UnsupportedEncodingException {
    Boolean boo = this.userService.checkData(data, type);
    if (boo == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    return ResponseEntity.ok(boo);
  }

  /**
   * 注册
   *
   * @param user
   * @param code 验证码
   * @return
   */
  @PostMapping("register")
  public ResponseEntity<Void> register(@Valid User user, @RequestParam("code") String code) {

    this.userService.register(user, code);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
  /**
   * 根据用户名和密码查询用户
   *
   * @param username
   * @param password
   * @return
   */
  @GetMapping("query")
  public ResponseEntity<User> queryUser(
      @RequestParam("username") String username, @RequestParam("password") String password) {
    User user = this.userService.queryUser(username, password);
    if (user == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    return ResponseEntity.ok(user);
  }
}
