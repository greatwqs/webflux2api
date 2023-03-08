package com.greatwqs.app.controller;

import com.greatwqs.app.domain.po.UserPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greatwqs.app.service.UserService;

/**
 * 返回 json 中的视图
 *
 * @author wang.qingsong
 * Create on 2023/03/08
 */
@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 用JSON呈现试图
	 * http://localhost:8080/user
	 * http://localhost:8080/user?userId=2
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public UserPo getUserInfo(Model mode,
                              @RequestParam(value = "userId", defaultValue = "1") Long userId) {
		return userService.findByUserId(userId);
	}

}
