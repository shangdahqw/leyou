package com.leyou.user.service;

import com.leyou.user.mapper.UserMapper;
import com.leyou.user.pojo.User;
import com.leyou.user.utils.CodecUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * @author chenyilei
 * @date 2018/11/20-12:47 hello everyone
 */
@Service
public class UserService {
  @Autowired private UserMapper userMapper;

  static final String KEY_PREFIX = "sms:prefix:"; // 加上phone

  public Boolean checkData(String data, Integer type) throws UnsupportedEncodingException {
    User user = new User();
    switch (type) {
      case 1:
        user.setUsername(data);
        break;
      case 2:
        user.setPhone(data);
        break;
      default:
        throw new RuntimeException("checkData error");
    }
    return userMapper.selectCount(user) == 0;
  }

  public void register(User user, String code) {

    String salt = CodecUtils.generateSalt();
    String pwd = CodecUtils.md5Hex(user.getPassword(), salt);

    user.setPassword(pwd);
    user.setSalt(salt);
    user.setCreated(new Date());
    userMapper.insert(user);
  }

  public User queryUser(String username, String password) {
    // 查询
    User record = new User();
    record.setUsername(username);
    User user = this.userMapper.selectOne(record);
    // 校验用户名
    if (user == null) {
      return null;
    }
    // 校验密码
    if (!user.getPassword().equals(CodecUtils.md5Hex(password, user.getSalt()))) {
      return null;
    }
    // 用户名密码都正确
    return user;
  }
}
