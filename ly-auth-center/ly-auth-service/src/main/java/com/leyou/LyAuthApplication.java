package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author chenyilei
 * @date 2018/11/21-17:02 hello everyone
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class LyAuthApplication {
  public static void main(String[] args) {
    SpringApplication.run(LyAuthApplication.class, args);
  }
}
