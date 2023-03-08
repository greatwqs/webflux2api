package com.greatwqs.app.common.exception;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import com.greatwqs.app.common.AppConstants;

/**
 * ErrorCode, define for return code.
 *
 * @author wang.qingsong
 * Create on 2023/03/08
 */
public enum ErrorCode {

	/** HTTP STATUS CODE [0-1000) **/
	NORMAL_SUCCESS(200, HttpStatus.OK.value()),
	BAD_REQUEST(400, HttpStatus.BAD_REQUEST.value()),
	UNAUTHORIZED(401, HttpStatus.UNAUTHORIZED.value()),
	FORBIDDEN(403, HttpStatus.FORBIDDEN.value()),
	NOT_FOUND(404, HttpStatus.NOT_FOUND.value()),
	METHOD_NOT_ALLOWED(405, HttpStatus.METHOD_NOT_ALLOWED.value()),
	CONFLICT(409, HttpStatus.CONFLICT.value()),
	UNSUPPORTED_MEDIA_TYPE(415, HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()),
	INTERNAL_SERVER_ERROR(500, HttpStatus.INTERNAL_SERVER_ERROR.value()),

	/** Bisiness ERROR [1000-2000) **/
	BIZ_UNKNOWN_ERROR(1000, HttpStatus.INTERNAL_SERVER_ERROR.value()),
	PRODUCT_NOT_FOUND_DEFAULT(1001, HttpStatus.NOT_FOUND.value(), "error.code.1001"),
	PRODUCT_NOT_FOUND_P1(1001, HttpStatus.NOT_FOUND.value(), "error.code.1001.p1"),
	PRODUCT_NOT_FOUND_P2(1001, HttpStatus.NOT_FOUND.value(), "error.code.1001.p2"),
	USER_LOGIN_TOKEN_EXPIRED(1002, HttpStatus.UNAUTHORIZED.value()),

	/** Outer Call ERROR [2000+)  **/
	OUT_UNKNOWN_ERROR(3000, HttpStatus.INTERNAL_SERVER_ERROR.value());

	private static Map<Integer, ErrorCode> statusNameMap = new HashMap<>();

	static {
		ErrorCode[] codeMapArray = ErrorCode.values();
		for (ErrorCode codeMap : codeMapArray) {
			statusNameMap.put(codeMap.getErrorCode(), codeMap);
		}
	}

	private Integer errorCode;
	private Integer httpCode;
	private String errorMsgKey;

	ErrorCode(Integer errorCode, Integer httpCode) {
		this.errorCode = errorCode;
		this.httpCode = httpCode;
	}

	ErrorCode(Integer errorCode, Integer httpCode, String errorMsgKey) {
		this.errorCode = errorCode;
		this.httpCode = httpCode;
		this.errorMsgKey = errorMsgKey;
	}

	/***
	 * get error message key.
	 * @return example `error.code.2000`
	 */
	public String getErrorMsgKey() {
		if (StringUtils.isNotEmpty(errorMsgKey)) {
			return errorMsgKey;
		}
		return AppConstants.ERROR_CODE_PREFIX_KEY + this.errorCode;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public Integer getHttpCode() {
		return httpCode;
	}
}
