package com.leyou.upload;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.fdfs.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
// @SpringBootTest(classes = LyUploadService.class)
@SpringBootTest
public class FdfsTest {

  @Autowired private FastFileStorageClient storageClient;

  @Autowired private ThumbImageConfig thumbImageConfig;

  @Test
  public void testUpload() throws FileNotFoundException {
    File file = new File("/Users/huangqiangwen/Desktop/毕设/图片/WechatIMG6.jpeg");
    // 上传

    StorePath storePath =
        this.storageClient.uploadFile(new FileInputStream(file), file.length(), "jpeg", null);
    // 带分组的路径
    System.out.println(storePath.getFullPath());
    // 不带分组的路径
    System.out.println(storePath.getPath());

    File file1 = new File("/Users/huangqiangwen/Desktop/毕设/图片/WechatIMG3.jpeg");
    // 上传

    StorePath storePath1 =
        this.storageClient.uploadFile(new FileInputStream(file1), file1.length(), "jpeg", null);
    // 带分组的路径
    System.out.println(storePath1.getFullPath());
    // 不带分组的路径
    System.out.println(storePath1.getPath());
  }

  @Test
  public void testUploadAndCreateThumb() throws FileNotFoundException {
    File file = new File("/Users/huangqiangwen/黄强文文档资料/java项目/乐优商城/必看文件.png");
    // 上传并且生成缩略图
    StorePath storePath =
        this.storageClient.uploadImageAndCrtThumbImage(
            new FileInputStream(file), file.length(), "png", null);
    // 带分组的路径
    System.out.println(storePath.getFullPath());
    // 不带分组的路径
    System.out.println(storePath.getPath());
    // 获取缩略图路径
    String path = thumbImageConfig.getThumbImagePath(storePath.getPath());
    System.out.println(path);
  }
}
