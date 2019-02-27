package com.leyou.common.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JsonUtilsTest {

  @Test
  public void serialize() {

    UserInfo userInfo = new UserInfo();
    userInfo.setId(1000L);
    userInfo.setUsername("张三");
    userInfo.setImageurl("http://www.hqw.com");

    UserInfo userInfo2 = new UserInfo();
    userInfo2.setId(1000L);
    userInfo2.setUsername("李四");
    userInfo2.setImageurl("http://www.hqw.com");
    List list = new ArrayList<UserInfo>();
    list.add(userInfo);
    list.add(userInfo2);

    System.out.println(JsonUtils.serialize(list));
  }

  @Test
  public void parseList() {

    String jsonStr =
        " [{\"id\":1000,\"username\":\"张三\",\"imageurl\":\"http://www.hqw.com\"},{\"id\":1000,\"username\":\"李四\",\"imageurl\":\"http://www.hqw.com\"}] ";
    List list = JsonUtils.parseList(jsonStr, UserInfo.class);
    System.out.println(list.get((0)));
    System.out.println(list.get((1)));
  }
}
