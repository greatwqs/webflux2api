package com.greatwqs.app.task;

import com.greatwqs.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * SamplesTask
 *
 * @author wang.qingsong
 * Create on 2019/10/21
 */
@Component
@Slf4j
public class SamplesTask {

	@Autowired
	private UserService userService;

	@Scheduled(fixedDelay = 1000 * 60 * 10)
	public void userTask() {
		// userService.findByName("");
		log.info("SamplesTask userTask run.");
	}
}
