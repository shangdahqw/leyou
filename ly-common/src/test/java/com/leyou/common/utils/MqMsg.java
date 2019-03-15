package com.leyou.common.utils;

import lombok.Data;

/**
 * @Author huangqiangwen
 *
 * @create 2019/3/3 下午2:25 @Description
 */
@Data
public class MqMsg<T> {
  private Long type; // 1代表群组

  private T t;
}
