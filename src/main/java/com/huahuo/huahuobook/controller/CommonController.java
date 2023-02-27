package com.huahuo.huahuobook.controller;

import cn.hutool.core.util.ZipUtil;
import com.huahuo.huahuobook.common.ResponseResult;
import com.huahuo.huahuobook.pojo.Img;
import com.huahuo.huahuobook.service.ImgService;
import com.huahuo.huahuobook.service.QiniuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @作者 花火
 * @创建日期 2023/1/27 14:14
 */
@RestController
@RequestMapping
public class CommonController {
    @Autowired
    private QiniuService qiniuService;
    @Autowired
    private ImgService imgService;
    @PostMapping("/upload/img") //先调用新建账单接口 获得id，再调用这个。
     public ResponseResult uploadImg(@RequestParam("file") MultipartFile file, @RequestParam Integer id ){
        String url =  qiniuService.saveImage(file);
        Img img  = new Img();
        img.setUrl(url);
        img.setBillId(id);
        imgService.save(img);
        return ResponseResult.okResult("上传图片成功");
    }

    private static InputStream getImageStream(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                return inputStream;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/download")
    public void downloadMultiFileToMinIO(HttpServletResponse response) throws IOException {
        //被压缩文件InputStream
        InputStream[] srcFiles = new InputStream[10];
        //被压缩文件名称
        String[] srcFileNames = new String[10];
        for (int i = 0; i < 10; i++) {
            InputStream inputStream = getImageStream("http://image.fzuhuahuo.cn/012224118284414197929ce072ebd0e0.png");
            if (inputStream == null) {
                continue;
            }
            srcFiles[i] = inputStream;
            srcFileNames[i] = "text"+i;
        }
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("下载测试.zip", "UTF-8"));
        //多个文件压缩成压缩包返回
        ZipUtil.zip(response.getOutputStream(), srcFileNames, srcFiles);
    }
}
