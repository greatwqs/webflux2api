package com.greatwqs.app;

import com.greatwqs.app.domain.po.UserPo;

/**
 *
 * TestValues Constants
 */
public class TestValues {

	public static final String BASE_FILE_PATH = "src/test/resources/";

	public static final UserPo user(String username){
		UserPo user = new UserPo();
		user.setId(1L);
		user.setUserName(username);
		return user;
	}
}
