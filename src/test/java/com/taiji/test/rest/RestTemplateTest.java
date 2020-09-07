/**
 * 
 */
package com.taiji.test.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.taiji.ServerApplication;
import com.taiji.admin.utils.http.RestTemplateUtil;

/**
 * sw-view com.example.sw-view.rest RestTemplateTest.java
 *
 * @author hsl
 *
 * 2020年4月22日 下午5:39:41
 *
 * desc:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes={ServerApplication.class})
@WebAppConfiguration
public class RestTemplateTest {
	
	@Test
    public void test() {
		RestTemplateUtil.test();
	}

}
