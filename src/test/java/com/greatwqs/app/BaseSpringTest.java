package com.greatwqs.app;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * base spring test
 *
 * @author wang.qingsong
 * Create on 2019/09/30
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public abstract class BaseSpringTest {
}
