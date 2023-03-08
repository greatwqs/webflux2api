package com.greatwqs.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.greatwqs.app.component.RequestComponent;
import com.greatwqs.app.component.SmsComponent;

/**
 *
 * BaseServiceTest @MockBean for third part!
 *
 * @author wang.qingsong
 * Create on 2019/10/20
 */
public abstract class BaseSpringServiceTest extends BaseSpringTest {

	@MockBean
	@Autowired
	protected SmsComponent smsComponent;

	@MockBean
	@Autowired
	private RequestComponent requestComponent;

}
