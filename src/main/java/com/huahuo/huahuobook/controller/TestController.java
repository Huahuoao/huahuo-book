package com.huahuo.huahuobook.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataUnit;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.huahuo.huahuobook.pojo.Bill;
import com.huahuo.huahuobook.pojo.Book;
import com.huahuo.huahuobook.service.BillService;
import com.huahuo.huahuobook.service.BookService;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import javax.xml.ws.Response;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @作者 花火
 * @创建日期 2023/1/29 16:09
 */
@RestController
@RequestMapping("/test")
public class TestController {
@Autowired
    BillService billService;
@Autowired
    BookService bookService;

}
