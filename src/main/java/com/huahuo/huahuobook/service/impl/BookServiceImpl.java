package com.huahuo.huahuobook.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.hash.Hash;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huahuo.huahuobook.common.ResponseResult;
import com.huahuo.huahuobook.dto.BookDto;
import com.huahuo.huahuobook.dto.BookPageDto;
import com.huahuo.huahuobook.dto.ListBookDto;
import com.huahuo.huahuobook.dto.TempBookDto;
import com.huahuo.huahuobook.mapper.RelationMapper;
import com.huahuo.huahuobook.pojo.Bill;
import com.huahuo.huahuobook.pojo.Book;
import com.huahuo.huahuobook.pojo.Relation;
import com.huahuo.huahuobook.service.BillService;
import com.huahuo.huahuobook.service.BookService;
import com.huahuo.huahuobook.mapper.BookMapper;
import com.huahuo.huahuobook.service.RelationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
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
@Slf4j
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
    @Autowired
    private BillService billService;

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
    public ResponseResult listBooks(ListBookDto dto) {
        List<Integer> ids = new ArrayList<>();
        List<Relation> relations = relationMapper.listBookIdByUserId(dto.getUserId());
        log.info(relations.toString());
        for (Relation relation : relations) {
            ids.add(relation.getBookId());
        }
        log.info(ids.toString());
        LambdaQueryWrapper<Book> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(Book::getId, ids)
                .eq(Book::getType, dto.getType());
        List<Book> list = list(queryWrapper);
        return ResponseResult.okResult(list);
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

    @Override
    public ResponseResult updateBook(BookDto dto) {
        Integer id = dto.getId();
        Book book = getById(id);
        if (dto.getBudget() != null) {
            if (book.getBalance() != null) {
                Double cost = book.getBudget() - book.getBalance();
                book.setBalance(dto.getBudget() - cost);
                book.setBudget(dto.getBudget());


            } else {
                book.setBudget(dto.getBudget());
                book.setBalance(dto.getBudget());
                LambdaQueryWrapper<Bill> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(Bill::getBookId, book.getId());
                List<Bill> list = billService.list(queryWrapper);
                for (Bill bill : list) {
                    if (bill.getTypeOne() == 1) {
                        book.setBalance(book.getBalance() - bill.getNum());
                    } else if (bill.getTypeOne() == 2) {
                        book.setBalance(book.getBalance() + bill.getNum());
                    }
                }
            }
        }
        if (StringUtils.isNotBlank(dto.getImg()))
            book.setImg(dto.getImg());
        if (StringUtils.isNotBlank(dto.getName()))
            book.setName(dto.getName());
        updateById(book);
        return ResponseResult.okResult("修改账本信息成功");
    }

    @Override
    public ResponseResult<String> deleteBook(Integer id) {
        LambdaQueryWrapper<Relation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Relation::getBookId, id);
        relationService.remove(queryWrapper);
        bookService.removeById(id);
        return ResponseResult.okResult("账本删除成功");
    }

    @Override
    public void createExcel(Integer id, HttpServletResponse response) throws IOException {
        LambdaQueryWrapper<Bill> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Bill::getBookId, id)
                .orderByDesc(Bill::getCreateTime);
        Book book = bookService.getById(id);
        List<Bill> list = billService.list(queryWrapper);
        for (int i = 1; i <= list.size(); i++) {
            Bill bill = list.get(i-1);
            bill.setId(i);
        }
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short) 13);
        headWriteFont.setBold(true);
        headWriteCellStyle.setWriteFont(headWriteFont);
        //设置头居中
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);

        //内容策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        //设置 水平居中
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);

        HorizontalCellStyleStrategy horizontalCellStyleStrategy = new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码
        String excelName = URLEncoder.encode(book.getName(), "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + excelName + ExcelTypeEnum.XLSX.getValue());
        EasyExcel.write(response.getOutputStream(), Bill.class).registerWriteHandler(horizontalCellStyleStrategy)
                .sheet(book.getName())
                .doWrite(list);  //list就是存储的数据
    }
}




