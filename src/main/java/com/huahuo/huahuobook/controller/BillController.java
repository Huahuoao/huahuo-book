package com.huahuo.huahuobook.controller;

import com.huahuo.huahuobook.common.ResponseResult;
import com.huahuo.huahuobook.dto.BillDto;
import com.huahuo.huahuobook.dto.BillPageDto;
import com.huahuo.huahuobook.pojo.Bill;
import com.huahuo.huahuobook.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @作者 花火
 * @创建日期 2023/1/25 17:43
 */
@RestController
@RequestMapping("/bill")
public class BillController {
    @Autowired
    BillService billService;

    @PostMapping("/add")
    public ResponseResult<String> add(@RequestBody BillDto bill) {
        return billService.add(bill);
    }

    @PostMapping("/list/book")
    public ResponseResult listBillByBook(@RequestBody BillPageDto billPageDto) {
        return billService.listBillByBook(billPageDto);
    }


    //条件返回（俩时间之内）
}
