package com.huahuo.huahuobook.controller;


import com.huahuo.huahuobook.common.ResponseResult;
import com.huahuo.huahuobook.common.aop.LogAnnotation;
import com.huahuo.huahuobook.dto.LoginDto;
import com.huahuo.huahuobook.dto.RegisterDto;
import com.huahuo.huahuobook.pojo.User;
import com.huahuo.huahuobook.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.ini4j.Reg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @作者 花火
 * @创建日期 2023/1/23 22:12
 */
@RequestMapping("/user")
@RestController
@Slf4j
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/login")
    @LogAnnotation(module="user",operator="登录")
    public ResponseResult<String> login(@RequestBody LoginDto dto) {
        return userService.login(dto);
    }


    @PostMapping("/register")
    @LogAnnotation(module="user",operator="注册")
    public ResponseResult<String> register(@RequestBody RegisterDto dto) {

        return userService.register(dto);
    }
}