package com.huahuo.huahuobook.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huahuo.huahuobook.common.ResponseResult;
import com.huahuo.huahuobook.pojo.Bill;
import com.huahuo.huahuobook.service.BillService;
import com.huahuo.huahuobook.mapper.BillMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【bill】的数据库操作Service实现
* @createDate 2023-01-25 17:59:33
*/
@Service
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill>
    implements BillService{

    @Override
    public ResponseResult<String> add(Bill bill) {
        save(bill);
        return ResponseResult.okResult("保存成功");
    }
}




