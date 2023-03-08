package com.greatwqs.app.common;

import java.util.Locale;

/**
 * AppConstants
 *
 * @author wang.qingsong
 * Create on 2023/03/08
 */
public class AppConstants {

	// default locale
	public static final Locale LOCALE = new Locale("zh", "CN");

	/**
	 * ErrorCode i18n properties prefix key.
	 */
	public static final String ERROR_CODE_PREFIX_KEY = "error.code.";

	/**
	 * login token from header
	 */
	public static final String APP_LOGIN_TOKEN = "app_token_login";

	/**
	 * request set attribute names
	 * RequestContextHolder.currentRequestAttributes().setAttribute(name, value)
	 */
	public static final String REQUEST_USER_TOKEN = "requestUserToken";
	public static final String REQUEST_USER = "requestUser";
}
