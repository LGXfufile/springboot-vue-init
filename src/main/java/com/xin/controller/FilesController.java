package com.xin.controller;

/*
@data 2021/8/14 22:50
@PACKAGE_NAME com.xin.controller
*/

import cn.hutool.core.io.FileUtil;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.xin.common.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FilesController {

    @Value("${server.port}")
    private String port;
    private static final String ip = "http://localhost";


    //上传接口
    @PostMapping("/upload")
    public Result<?> upload(MultipartFile file) throws IOException {
        //获取文件名称；
        String originalFilename = file.getOriginalFilename();
        //获取上传的路径
        String flag = IdUtil.fastSimpleUUID();
        String rootPath = "E:\\javaLearn\\springboot-vue-learning\\src\\main\\resources\\files\\"+flag+"_"+originalFilename;

        //把文件写入到要上传的路径；
        FileUtil.writeBytes(file.getBytes(), rootPath);
        return Result.success(ip+":"+port+"/file/"+flag);//返回结果url,可以用于下载用；
    }

    //下载接口
    @GetMapping("/{flag}")
    public void download(HttpServletResponse response, @PathVariable String flag){
        OutputStream os;//新建一个输出流对象
        String basePath = "E:\\javaLearn\\springboot-vue-learning\\src\\main\\resources\\files\\";//定义文件上传的根路径
        List<String> fileNames = FileUtil.listFileNames(basePath);//获取到根目录下的所有的文件；
        String fileName = fileNames.stream().filter(name -> name.contains(flag)).findAny().orElse("");//找到跟参数一致的参数文件；
        try {
            if (StrUtil.isNotEmpty(fileName)){
                response.addHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));
                response.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(basePath + fileName);//通过文件路径读取文件字节流
                os = response.getOutputStream();//通过输出流返回文件；
                os.write(bytes);
                os.flush();
                os.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
