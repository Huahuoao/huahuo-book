package com.huahuo.huahuobook;

import com.alibaba.excel.EasyExcel;
import com.huahuo.huahuobook.pojo.Bill;
import com.huahuo.huahuobook.service.BillService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.apache.commons.lang3.RandomUtils.nextDouble;
import static org.apache.commons.lang3.RandomUtils.nextInt;

@SpringBootTest
public class HuahuoBookApplicationTests {
    @Autowired
    BillService billService;

    @Test
    public void createData() {
        int num = 50;
        Bill bill = new Bill();
        ArrayList<Bill> bills = new ArrayList<>();
        while (num != 0) {
            bill.setBookId(1);
            bill.setText("测试数据");
            num--;
            Random random = new Random();
            int minDay = (int) LocalDate.of(2022, 1, 1).toEpochDay();
            int maxDay = (int) LocalDate.of(2023, 3, 1).toEpochDay();
            long randomDay = minDay + random.nextInt(maxDay - minDay);
            LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);
            String date = randomBirthDate.toString();
            Integer num2 = nextInt(0, 200);
            Double num3 = num2.doubleValue();
            int type = nextInt(1, 3);
            int type2 = nextInt(0, 6);
            String type3[] = new String[]{"餐饮", "出行", "娱乐", "学习", "日用品", "其他"};
            String type4 = type3[type2];
            bill.setTypeOne(type);
            bill.setNum(num3);
            bill.setCreateTime(date);
            bill.setTypeTwo(type4);
            if(bill.getTypeOne()==2)
                bill.setTypeTwo("收入");
            bills.add(bill);
            billService.add(bill);
        }

//        billService.saveBatch(bills);
    }
}