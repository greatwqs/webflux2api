package com.greatwqs.app.service;

import com.greatwqs.app.domain.po.UserPo;

/**
 *
 * @author wang.qingsong
 * Create on 2019/9/23
 */
public interface UserService {

	UserPo findByUserId(Long userId);
}
