package com.huahuo.huahuobook;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HuahuoBookApplicationTests {

	@Test
	void contextLoads() {
		System.out.println( String.valueOf((int)((Math.random()*9+1)*100000)));
	}

}
