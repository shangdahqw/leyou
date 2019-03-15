package com.leyou.common.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JsonUtilsTest {

  @Test
  public void serialize() {

    //    UserInfo userInfo = new UserInfo();
    //    userInfo.setId(1000L);
    //    userInfo.setUsername("张三");
    //    userInfo.setImageurl("http://www.hqw.com");
    //
    //    UserInfo userInfo2 = new UserInfo();
    //    userInfo2.setId(1000L);
    //    userInfo2.setUsername("李四");
    //    userInfo2.setImageurl("http://www.hqw.com");
    //    List list = new ArrayList<UserInfo>();
    //    list.add(userInfo);
    //    list.add(userInfo2);
    //
    //    System.out.println(JsonUtils.serialize(list));

    List list = new ArrayList<String>();
    //    list.add(1000L);
    //    list.add(2000L);
    list.add("httsjdf/afe325/ds.png");
    list.add("httsjdf/afe3223455/ds.png");

    System.out.println(JsonUtils.serialize(list));
    // ["httsjdf/afe325/ds.png","httsjdf/afe3223455/ds.png"]
  }

  @Test
  public void parseList() {

    //    String jsonStr =
    //        "
    // [{\"id\":1000,\"username\":\"张三\",\"imageurl\":\"http://www.hqw.com\"},{\"id\":1000,\"username\":\"李四\",\"imageurl\":\"http://www.hqw.com\"}] ";
    //    List list = JsonUtils.parseList(jsonStr, UserInfo.class);
    //    System.out.println(list.get((0)));
    //    System.out.println(list.get((1)));

    //    String jsonStr = "[1000,2000]";
    String jsonStr = "[\"httsjdf/afe325/ds.png\",\"httsjdf/afe3223455/ds.png\"]";
    List list = JsonUtils.parseList(jsonStr, String.class);
    System.out.println(list.get((0)));
    System.out.println(list.get((1)));
  }

  @Test
  public void serialize2() {

    GroupMqMsg groupMqMsg = new GroupMqMsg();
    groupMqMsg.setGroupId(1L);
    groupMqMsg.setGroupName("好好");
    groupMqMsg.setJionUserId(100000L);

    MqMsg mqMsg = new MqMsg<GroupMqMsg>();

    mqMsg.setType(1L);
    mqMsg.setT(groupMqMsg);

    System.out.println(JsonUtils.serialize(mqMsg));
  }

  @Test
  public void parse() {

    MqMsg mqMsg =
        JsonUtils.parse(
            "{\"type\":1,\"t\":{\"groupId\":1,\"groupName\":\"好好\",\"jionUserId\":100000}}",
            MqMsg.class);

    System.out.println(mqMsg.getType());
    System.out.println(mqMsg.getT());

    //    GroupMqMsg groupMqMsg =JsonUtils.parse(mqMsg.getT(),GroupMqMsg.class);

    Object object = mqMsg.getT();
    GroupMqMsg groupMqmsg = (GroupMqMsg) mqMsg.getT();
    System.out.println(groupMqmsg.getGroupName());
  }
}
