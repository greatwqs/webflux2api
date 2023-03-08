package com.greatwqs.app.controller;

import com.greatwqs.app.component.RequestComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author wang.qingsong
 * Create on 2023/03/08
 */
@Slf4j
@RequestMapping("")
@RestController
public class HomeController {

	@Autowired
	private RequestComponent requestComponent;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String home() {
		String msg = "success! uri: " + requestComponent.getRequestUri()
			+ ", clientIP: " + requestComponent.getClientIp();
		log.warn(msg);
		return msg;
	}

}
