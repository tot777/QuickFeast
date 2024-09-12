package com.xx.reggie.controller;

import com.xx.reggie.common.R;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@RestController
@RequestMapping("/common")
public class CommonController{
    @Value("${raggie.path}")
    private String BasePath;


    //文件上传
    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){
        //file是临时文件，需要转存到指定位置，否则本次请求完成后临时文件会删除

        //原始文件名
        String originalFilename = file.getOriginalFilename();
        //获取原文件名后缀
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        //使用uuid重新命名，防止名称重复覆盖
        String FileName = UUID.randomUUID().toString() + suffix;

        //创建一个目录对象
        File dir = new File(BasePath);
        //如果目录不存在，则创建对应目录
        if(!dir.exists()){
            dir.mkdirs();
        }




        //将临时文件转存到指定位置
        try {
            file.transferTo(new File(BasePath+FileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return R.success(FileName);
    }

    //文件下载
    @GetMapping("/download")
    public void download(String name , HttpServletResponse response){
        try {
            //输入流，通过输入流读取文件内容
            FileInputStream fileInputStream = new FileInputStream(new File(BasePath+name));

            //输出流，通过输出流将文件写会浏览器，在浏览器展示图片
            ServletOutputStream outputStream = response.getOutputStream();

            response.setContentType("image/jpeg");

            int len = 0;
            byte[] bytes = new byte[1024];
            while((len = fileInputStream.read(bytes))!= -1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }

            outputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
