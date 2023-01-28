package com.huahuo.huahuobook.controller;

import com.huahuo.huahuobook.common.ResponseResult;
import com.huahuo.huahuobook.dto.BookDto;
import com.huahuo.huahuobook.dto.ShareBookDto;
import com.huahuo.huahuobook.dto.TempBookDto;
import com.huahuo.huahuobook.service.BookService;
import com.huahuo.huahuobook.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @作者 花火
 * @创建日期 2023/1/27 17:10
 */
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private FriendService friendService;

    @PostMapping("/create")
    public ResponseResult<String> createNewBook(@RequestBody BookDto bookDto) {
        return bookService.createNewBook(bookDto);
    }

    @GetMapping("/list/{id}")
    public ResponseResult listBooks(@PathVariable Integer id) {
        return bookService.listBooks(id);
    }

    //与好友共享账本
    @PostMapping("/share/1")
    public ResponseResult<String> shareBookWithFriend(@RequestBody ShareBookDto dto) {

        return friendService.shareBookWithFriend(dto);
    }

    //取消共享
    @PostMapping("/share/0")
    public ResponseResult<String> cancelShareBookWithFriend(@RequestBody ShareBookDto dto) {

        return friendService.cancelShareBookWithFriend(dto);
    }

    //创建一个临时账本
    @PostMapping("/create/temp")
    public ResponseResult createTempBook(@RequestBody BookDto bookDto) {
        return bookService.createTempBook(bookDto);
    }
    //通过临时码加入账本
    @GetMapping("/add/temp")
    public ResponseResult addTempBook(@RequestBody TempBookDto dto){
        return null;
    }
}
