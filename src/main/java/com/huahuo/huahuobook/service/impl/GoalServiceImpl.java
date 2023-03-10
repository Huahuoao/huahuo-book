package com.huahuo.huahuobook.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huahuo.huahuobook.pojo.Goal;
import com.huahuo.huahuobook.service.GoalService;
import com.huahuo.huahuobook.mapper.GoalMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【goal】的数据库操作Service实现
* @createDate 2023-03-09 23:41:13
*/
@Service
public class GoalServiceImpl extends ServiceImpl<GoalMapper, Goal>
    implements GoalService{

}




