package com.huahuo.huahuobook.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.huahuo.huahuobook.common.ResponseResult;
import com.huahuo.huahuobook.dto.BookDto;
import com.huahuo.huahuobook.dto.ListBookDto;
import com.huahuo.huahuobook.dto.ShareBookDto;
import com.huahuo.huahuobook.dto.TempBookDto;
import com.huahuo.huahuobook.pojo.Bill;
import com.huahuo.huahuobook.pojo.Book;
import com.huahuo.huahuobook.service.BookService;
import com.huahuo.huahuobook.service.FriendService;
import io.swagger.models.auth.In;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

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
    @GetMapping("/list")
    public ResponseResult listBooks(@RequestBody ListBookDto dto) {
        return bookService.listBooks(dto);
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
    public ResponseResult addTempBook(@RequestBody TempBookDto dto) {
        return null;
    }


    @GetMapping("/delete/{id}")
    public ResponseResult<String> deleteBook(@PathVariable Integer id) {
        return bookService.deleteBook(id);
    }


    @GetMapping("/create/excel/{id}")
    public void createExcel(@PathVariable Integer id, HttpServletResponse response) throws IOException {
          bookService.createExcel(id,response);
    }
}
