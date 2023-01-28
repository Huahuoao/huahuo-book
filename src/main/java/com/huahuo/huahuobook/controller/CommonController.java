package com.huahuo.huahuobook.controller;

import com.huahuo.huahuobook.service.QiniuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @作者 花火
 * @创建日期 2023/1/27 14:14
 */
@RestController
@RequestMapping
public class CommonController {
    @Autowired
    private QiniuService qiniuService;
    @PostMapping("/upload/img")
    public String uploadImg(@RequestParam("file") MultipartFile file ){
        return qiniuService.saveImage(file);
    }
}
