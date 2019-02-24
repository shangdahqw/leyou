package com.leyou.auth.controller;

import com.leyou.auth.bean.UserInfo;
import com.leyou.auth.config.JwtProperties;
import com.leyou.auth.service.AuthService;
import com.leyou.auth.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chenyilei
 * @date 2018/11/22-12:50 hello everyone
 */
@RestController
@Slf4j
@EnableConfigurationProperties(JwtProperties.class)
public class AuthController {
  @Autowired private JwtProperties prop;

  @Autowired private AuthService authService;

  // 我们接收用户名和密码，校验成功后，写出token
  /**
   * 登录授权
   *
   * @param username
   * @param password
   * @return
   */
  @PostMapping("accredit")
  public ResponseEntity<String> authentication(
      @RequestParam("username") String username,
      @RequestParam("password") String password,
      HttpServletRequest request,
      HttpServletResponse response) {
    // 登录校验
    String token = this.authService.authentication(username, password);
    if (StringUtils.isBlank(token)) {
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    return ResponseEntity.ok(token);
  }

  // 检验用户登入状态, 并且刷新token
  @GetMapping("/verify")
  public ResponseEntity<UserInfo> verify(
      String token, HttpServletRequest request, HttpServletResponse response) {
    if (StringUtils.isBlank(token)) {
      log.error("无权访问 未登陆");
      return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
    }
    // 解析对错
    try {
      UserInfo info = JwtUtils.getInfoFromToken(token, prop.getPublicKey());
      // 刷新token
      String newToken = JwtUtils.generateToken(info, prop.getPrivateKey(), prop.getExpire());
      info.setToken(newToken);
      return ResponseEntity.ok(info);
    } catch (Exception e) {
      log.error("[verify]解析错误,过期或无效");
      return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
    }
  }
}
