package com.huahuo.huahuobook.controller;

import com.huahuo.huahuobook.common.ResponseResult;
import com.huahuo.huahuobook.dto.AddFriendDto;
import com.huahuo.huahuobook.pojo.User;
import com.huahuo.huahuobook.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @作者 花火
 * @创建日期 2023/1/25 17:43
 */
@RestController
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    private FriendService friendService;

    @PostMapping("/add")
    public ResponseResult<String> addFriend(@RequestBody AddFriendDto dto) {
        return friendService.addFriend(dto);
    }

    @GetMapping("/list/{id}")
    public ResponseResult<User> listFriends(@PathVariable Integer id) {
        return friendService.listFriends(id);
    }
}
