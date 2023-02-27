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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.apache.commons.lang3.RandomUtils.nextDouble;
import static org.apache.commons.lang3.RandomUtils.nextInt;

@SpringBootTest
public class HuahuoBookApplicationTests {
    @Autowired
    BillService billService;

    @Test
    public void createData() {
        int num = 500;
        Bill bill = new Bill();
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
            Integer num2 = nextInt(0,200);
            Double num3= num2.doubleValue();
            int type = nextInt(1, 6);
            int type2 = nextInt(1,3);
            bill.setTypeOne(type2);
            bill.setNum(num3);
            bill.setCreateTime(date);
            bill.setTypeTwo(type);
            billService.save(bill);
        }
    }
}