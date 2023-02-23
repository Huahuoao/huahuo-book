package com.huahuo.huahuobook.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataUnit;
import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huahuo.huahuobook.common.ResponseResult;
import com.huahuo.huahuobook.pojo.User;
import com.huahuo.huahuobook.service.UserService;
import com.huahuo.huahuobook.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-01-23 23:54:55
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
@Autowired
UserMapper userMapper;
    @Override
    public ResponseResult<String> login(User user) {
        User realUser = userMapper.selectByUsername(user.getUsername());
        if(realUser==null)
            return ResponseResult.errorResult(301,"用户未注册");
           Integer id = realUser.getId();
          if(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()).equals(realUser.getPassword())) //密码正确。生成token 返回
          {
              Map<String,Object> map = new HashMap<String,Object>();
              map.put("id",Integer.parseInt(realUser.getId().toString()));
              map.put("expire_time",System.currentTimeMillis()+1000*60*60*24);
              String token = JWTUtil.createToken(map,"huahuo".getBytes());
              if(JWTUtil.verify(token,"huahuo".getBytes())){
                  Map result = new HashMap();
                  result.put("token",token);
                  result.put("id",id);
                  return ResponseResult.okResult(result);

              }
              return ResponseResult.errorResult(401,"token不存在");
          }
          return ResponseResult.errorResult(302,"密码错误");



    }

    @Override
    public ResponseResult<String> register(User user) {
        User realUser = new User();
        User user1 = userMapper.selectByUsername(user.getUsername());
        if(userMapper.selectByUsername(user.getUsername())==null)
       {
        realUser.setUsername(user.getUsername());
        String pwd = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
        realUser.setPassword(pwd);
        realUser.setCreateTime(DateUtil.now());
        realUser.setPhone(user.getPhone());
        save(realUser);
        return ResponseResult.okResult(200,"注册成功");
       }
       return ResponseResult.errorResult(301,"用户已存在");
    }
}




