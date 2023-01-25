package com.huahuo.huahuobook.service;

import com.huahuo.huahuobook.common.ResponseResult;
import com.huahuo.huahuobook.pojo.Bill;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Administrator
* @description 针对表【bill】的数据库操作Service
* @createDate 2023-01-25 17:59:33
*/
public interface BillService extends IService<Bill> {
    public ResponseResult<String> add(Bill bill);
}
