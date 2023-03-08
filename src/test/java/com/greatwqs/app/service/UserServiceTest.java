package com.greatwqs.app.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.greatwqs.app.TestValues;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.greatwqs.app.BaseSpringServiceTest;
import com.greatwqs.app.domain.po.UserPo;
import com.greatwqs.app.mapper.user.UserlistMapper;

/**
 * UserServiceTest
 *
 * @author wang.qingsong
 * Create on 2019/9/10
 */
public class UserServiceTest extends BaseSpringServiceTest {

	@MockBean
	@Autowired
	private UserlistMapper userMapper;

	@Autowired
	private UserService userService;

	private UserPo user = TestValues.user("greatwqs");

	@Before
	public void init() {
		when(userMapper.findByUserId(any())).thenReturn(user);
	}

	@Test
	public void findByName() {
		final Long userId = user.getId();
		final UserPo user = userService.findByUserId(userId);
		Assert.assertTrue(user != null);
		Assert.assertEquals(user.getUserName(), userId);
	}

}
