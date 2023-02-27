package com.huahuo.huahuobook.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huahuo.huahuobook.common.PageResponseResult;
import com.huahuo.huahuobook.common.ResponseResult;
import com.huahuo.huahuobook.dto.BillDto;
import com.huahuo.huahuobook.dto.BillPageDto;
import com.huahuo.huahuobook.pojo.Bill;
import com.huahuo.huahuobook.service.BillService;
import com.huahuo.huahuobook.mapper.BillMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @description 针对表【bill】的数据库操作Service实现
 * @createDate 2023-01-25 17:59:33
 */
@Service
@Slf4j
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill>
        implements BillService {

    @Override
    public ResponseResult<String> add(BillDto bill) {
        log.info(bill.toString());
        save(bill);
        return ResponseResult.okResult(bill.getId());
    }

    @Override
    public ResponseResult listBillByBook(BillPageDto billPageDto) {
        billPageDto.checkParam();
        IPage page = new Page(billPageDto.getPage(), billPageDto.getSize());
        LambdaQueryWrapper<Bill> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Bill::getBookId, billPageDto.getBookId());
        lambdaQueryWrapper.orderByDesc(Bill::getCreateTime);
        if(billPageDto.getTypeTwo()!=0)
        {
            lambdaQueryWrapper.eq(Bill::getTypeTwo,billPageDto.getTypeTwo());
        }
        if(billPageDto.getTypeOne()!=0)
        {
            lambdaQueryWrapper.eq(Bill::getTypeOne,billPageDto.getTypeOne());
        }
        // 关键词
        if (StringUtils.isNotBlank(billPageDto.getKeyword())) {
            lambdaQueryWrapper.like(Bill::getText,billPageDto.getKeyword());
        }

        //俩时间之间
        if(StringUtils.isNotBlank(billPageDto.getBeginTime()))
        {
            lambdaQueryWrapper.between(Bill::getCreateTime,billPageDto.getBeginTime(),billPageDto.getEndTime());
        }
        //
        if(billPageDto.getIsCollect()==1)
        {
            lambdaQueryWrapper.eq(Bill::getIsCollect,1);
        }
        IPage pageResult = page(page, lambdaQueryWrapper);
        ResponseResult responseResult = new PageResponseResult(billPageDto.getPage(), billPageDto.getSize(), (int) page.getTotal());
        responseResult.setData(pageResult.getRecords());
        return responseResult;
    }
}




