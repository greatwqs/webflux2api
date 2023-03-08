package com.greatwqs.app.config;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.greatwqs.app.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * AsyncConfig
 *
 * @author wang.qingsong
 * Create on 2023/03/08
 */
@Slf4j
@Configuration
public class AsyncConfig implements AsyncConfigurer {

	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(1);
		executor.setMaxPoolSize(20);
		executor.setQueueCapacity(200);
		executor.setThreadNamePrefix("AppAsyncExecutor-");
		executor.setDaemon(true);
		executor.initialize();
		return executor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return (throwable, method, params) -> {
			log.error("AsyncConfig, AsyncUncaughtExceptionHandler"
				+ ", class: " + method.getDeclaringClass().getName()
				+ ", method: " + method.getName()
				+ ", params: " + JsonUtil.toJson(params), throwable);
		};
	}
}
