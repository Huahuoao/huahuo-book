package com.huahuo.huahuobook.controller;


import com.huahuo.huahuobook.common.ResponseResult;
import com.huahuo.huahuobook.common.aop.LogAnnotation;
import com.huahuo.huahuobook.dto.BookDto;
import com.huahuo.huahuobook.dto.ListBookDto;
import com.huahuo.huahuobook.dto.ShareBookDto;
import com.huahuo.huahuobook.dto.TempBookDto;

import com.huahuo.huahuobook.pojo.Book;
import com.huahuo.huahuobook.service.BookService;
import com.huahuo.huahuobook.service.FriendService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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

    /**
     * 创建新账本
     * @param bookDto
     * @return
     */
    @PostMapping("/create")
    @LogAnnotation(module="book",operator="新建账本")
    public ResponseResult<String> createNewBook(@RequestBody BookDto bookDto) {
        return bookService.createNewBook(bookDto);
    }

    /**
     * 按照类型查看账本
     * 1 普通账本
     * 2 共享账本
     * 3 临时账本（活动账本）
     * @param dto
     * @return
     */
    @PostMapping("/list")
    @LogAnnotation(module="book",operator="查询账本列表")
    public ResponseResult listBooks(@RequestBody ListBookDto dto) {
        return bookService.listBooks(dto);
    }

    /**
     *  分享账本
     * @param dto
     * @return
     */
    @PostMapping("/share/1")
    @LogAnnotation(module="book",operator="共享账本")
    public ResponseResult<String> shareBookWithFriend(@RequestBody ShareBookDto dto) {

        return friendService.shareBookWithFriend(dto);
    }

    //取消共享
    @PostMapping("/share/0")
    @LogAnnotation(module="book",operator="取消共享账本")
    public ResponseResult<String> cancelShareBookWithFriend(@RequestBody ShareBookDto dto) {

        return friendService.cancelShareBookWithFriend(dto);
    }

    //创建一个临时账本
    @PostMapping("/create/temp")
    @LogAnnotation(module="book",operator="创建临时账本")
    public ResponseResult createTempBook(@RequestBody BookDto bookDto) {
        return bookService.createTempBook(bookDto);
    }

    //通过临时码加入账本
    @PostMapping("/add/temp")
    @LogAnnotation(module="book",operator="通过临时码加入账本")
    public ResponseResult addTempBook(@RequestBody TempBookDto dto) {
        return bookService.addTempBook(dto);
    }

//删除账本
    @GetMapping("/delete/{id}")
    @LogAnnotation(module="book",operator="删除账本")
    public ResponseResult<String> deleteBook(@PathVariable Integer id) {
        return bookService.deleteBook(id);
    }

//导出账本excel
    @GetMapping("/create/excel/{id}")
    @LogAnnotation(module="book",operator="导出excel")
    public void createExcel(@PathVariable Integer id, HttpServletResponse response) throws IOException {
          bookService.createExcel(id,response);
    }

    @PostMapping("/update")
    @LogAnnotation(module="book",operator="修改账本")
    public ResponseResult updateBook(@RequestBody BookDto dto){
       return bookService.updateBook(dto);

    }
}
