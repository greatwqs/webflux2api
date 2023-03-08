package com.greatwqs.app.service.impl;

import com.greatwqs.app.domain.po.UserPo;
import com.greatwqs.app.mapper.user.UserlistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatwqs.app.service.UserService;

/**
 *
 * @author wang.qingsong
 * Create on 2019/9/23
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserlistMapper userlistMapper;

	@Override
	public UserPo findByUserId(Long userId) {
		UserPo user = userlistMapper.findByUserId(userId);
		return user;
	}
}
