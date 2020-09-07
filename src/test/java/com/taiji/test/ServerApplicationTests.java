package com.taiji.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.taiji.ServerApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={ServerApplication.class})
@WebAppConfiguration
public class ServerApplicationTests {

	@Test
	public void test() {
		System.out.println(".");
	}
	@Before
	public void testBefore() {
		System.out.println("before");
	}
	@After
	public void testAfter() {
		System.out.println("after");
	}

}
