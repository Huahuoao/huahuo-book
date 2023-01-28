package com.huahuo.huahuobook.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.hash.Hash;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huahuo.huahuobook.common.ResponseResult;
import com.huahuo.huahuobook.dto.BookDto;
import com.huahuo.huahuobook.dto.BookPageDto;
import com.huahuo.huahuobook.dto.TempBookDto;
import com.huahuo.huahuobook.mapper.RelationMapper;
import com.huahuo.huahuobook.pojo.Bill;
import com.huahuo.huahuobook.pojo.Book;
import com.huahuo.huahuobook.pojo.Relation;
import com.huahuo.huahuobook.service.BookService;
import com.huahuo.huahuobook.mapper.BookMapper;
import com.huahuo.huahuobook.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * @description 针对表【book】的数据库操作Service实现
 * @createDate 2023-01-27 17:35:12
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book>
        implements BookService {
    @Autowired
    private RelationService relationService;
    @Autowired
    private RelationMapper relationMapper;
    @Autowired
    private BookService bookService;
    @Autowired
    private BookMapper bookMapper;

    @Override
    public ResponseResult<String> createNewBook(BookDto bookDto) {
        Book book = new Book();
        Relation relation = new Relation();
        book.setType(book.getType());
        book.setCreateTime(DateUtil.now());
        book.setName(bookDto.getName());
        save(book);
        Integer id = book.getId();
        relation.setBookId(id);
        relation.setUserId(bookDto.getUserId());
        relationService.save(relation);
        return ResponseResult.okResult("新建成功");
    }

    @Override
    public ResponseResult listBooks(Integer userId) {
        List<Integer> ids = new ArrayList<>();
        List<Relation> relations = relationMapper.listBook_idByUser_id(userId);
        for (Relation relation : relations) {
            ids.add(relation.getBookId());
        }
        List<Book> books = listByIds(ids);
        return ResponseResult.okResult(books);
    }

    @Override
    public ResponseResult createTempBook(BookDto dto) {
        String code = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
        Book book = new Book();
        book.setName(dto.getName());
        book.setTempCode(code);
        book.setCreateTime(DateUtil.now());
        book.setType(dto.getType());
        bookService.save(book);
        return ResponseResult.okResult(book);
    }

    @Override
    public ResponseResult addTempBook(TempBookDto dto) {
        Book book = bookMapper.selectByTempCode(dto.getTempCode());
        Relation relation = new Relation();
        relation.setBookId(book.getId());
        relation.setUserId(dto.getUserId());
        relationService.save(relation);
        return ResponseResult.okResult("添加成功");
    }
}




