package com.leyou.auth.utils;

import com.leyou.auth.bean.UserAuth;
import org.junit.Before;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author chenyilei
 * @date 2018/11/21-19:10 hello everyone
 */
public class JwtUtilsTest {
  private static final String pubKeyPath = "/Users/huangqiangwen/Desktop/未命名文件夹/rsa.pub";

  private static final String priKeyPath = "/Users/huangqiangwen/Desktop/未命名文件夹/rsa.pri";

  private PublicKey publicKey;

  private PrivateKey privateKey;

  @Before
  public void testGetRsa() throws Exception {
    this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
    this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
  }

  @Test
  public void testRsa() throws Exception {
    RsaUtils.generateKey(pubKeyPath, priKeyPath, "234");
  }

  // 加密
  @Test
  public void testGenerateToken() throws Exception {
    // 生成token
    String token = JwtUtils.generateToken(new UserAuth(20L, "jack"), privateKey, 5);
    System.out.println("token = " + token);
  }

  @Test
  public void testParseToken() throws Exception {
    String token =
        "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoiamFjayIsImV4cCI6MTU1MDk5NjA1OH0.FcdJ1HYwVstnYA0mL7sqh0xXdCc4yamlRjQ6A7g-PrJw2CAe-vJ4e4ZqJjaNxg8nAVn5hkS1Okw8r6-BISqiTmSjrlXrq11sdbg80EK42cTCE9-Z2UfE5pu4CrGeId8_iovX_eeJ_aHsH1vemwrtMj1-OOmU91u2GpPVAqaA1nE"
            .trim();

    // 解析token
    UserAuth user = JwtUtils.getInfoFromToken(token, publicKey);
    System.out.println("id: " + user.getId());
    System.out.println("userName: " + user.getUsername());
  }
}
