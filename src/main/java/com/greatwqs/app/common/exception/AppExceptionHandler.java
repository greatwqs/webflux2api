package com.greatwqs.app.common.exception;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.greatwqs.app.component.RequestComponent;
import com.greatwqs.app.domain.vo.CommonErrorVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.greatwqs.app.utils.JsonUtil;
import com.greatwqs.app.utils.MessageUtils;
import com.greatwqs.app.utils.collection.Lists;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * AppExceptionHandler
 *
 * @author wang.qingsong
 * Create on 2023/03/08
 */
@Slf4j
@ResponseBody
@ControllerAdvice
public class AppExceptionHandler {

	@Autowired
	private RequestComponent requestComponent;

	/**
	 * getErrorMessage from AppException
	 * @param appException
	 * @return
	 */
	private static String getErrorMessage(AppException appException) {
		String errorMsg = appException.getErrorMsg();
		if (StringUtils.isNotEmpty(errorMsg)) {
			return errorMsg;
		}
		final String messageKey = appException.getErrorCode().getErrorMsgKey();
		final List<Object> errorMsgParams = appException.getErrorMsgParams();
		if (Lists.isNotEmpty(errorMsgParams)) {
			return MessageUtils.getMessage(messageKey, errorMsgParams.toArray());
		}
		return MessageUtils.getMessage(messageKey);
	}

	/****
	 * AppException
	 * @param appException
	 * @return
	 */
	@ExceptionHandler(AppException.class)
	public ResponseEntity<String> appException(AppException appException) {
		final ErrorCode errorCodeEnum = appException.getErrorCode();
		final Integer httpCode = errorCodeEnum.getHttpCode();
		final Integer errorCode = errorCodeEnum.getErrorCode();
		final String errorMsg = getErrorMessage(appException);
		final CommonErrorVo commonErrorVo = new CommonErrorVo(errorCode, errorMsg);
		log.warn(
			"URI: " + requestComponent.getRequestUri() + ", AppException: " + JsonUtil.toJson(commonErrorVo));
		return ResponseEntity.status(httpCode)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
			.body(JsonUtil.toJson(commonErrorVo));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public CommonErrorVo methodArgumentNotValidException(MethodArgumentNotValidException exp) {
		BindingResult bindingResult = exp.getBindingResult();
		List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
		if (Lists.isNotEmpty(fieldErrorList)) {
			for (FieldError fieldError : fieldErrorList) {
				// error notes one by one!
				if (fieldError != null) {
					return new CommonErrorVo(ErrorCode.BAD_REQUEST.getErrorCode(), fieldError.getDefaultMessage());
				}
			}
		}
		return new CommonErrorVo(ErrorCode.BAD_REQUEST.getErrorCode(),
			MessageUtils.getMessage(ErrorCode.BAD_REQUEST.getErrorMsgKey()));
	}

	@ExceptionHandler(BindException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public CommonErrorVo bindException(BindException exception) {
		log.error(getMessage(exception), exception);
		StringBuilder message = new StringBuilder();
		message.append(exception.getFieldErrors().get(0).getField()).append(":");
		message.append(exception.getFieldErrors().get(0).getDefaultMessage());
		return new CommonErrorVo(ErrorCode.BAD_REQUEST.getErrorCode(), message.toString());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public CommonErrorVo constraintViolationException(ConstraintViolationException exception) {
		Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
		StringBuilder message = new StringBuilder();
		for (ConstraintViolation<?> violation : violations) {
			message.append(violation.getMessage());
		}
		return new CommonErrorVo(ErrorCode.BAD_REQUEST.getErrorCode(), message.toString());
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public CommonErrorVo httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
		log.error(getMessage(exception), exception);
		return new CommonErrorVo(ErrorCode.METHOD_NOT_ALLOWED.getErrorCode(), exception.getMessage());
	}

	@ExceptionHandler({HttpMediaTypeNotSupportedException.class, HttpMediaTypeNotAcceptableException.class})
	@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
	public CommonErrorVo httpMediaTypeException(HttpMediaTypeException exception) {
		log.error(getMessage(exception), exception);
		return new CommonErrorVo(ErrorCode.UNSUPPORTED_MEDIA_TYPE.getErrorCode(), exception.getMessage());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public CommonErrorVo exception(Exception exception) {
		log.error(getMessage(exception), exception);
		return new CommonErrorVo(ErrorCode.BAD_REQUEST.getErrorCode(),
			MessageUtils.getMessage(ErrorCode.BAD_REQUEST.getErrorMsgKey()));
	}

	/***
	 * message log
	 * @param exception
	 * @return
	 */
	private String getMessage(Exception exception) {
		if (exception == null || StringUtils.isEmpty(exception.getMessage())) {
			return "Exception caught.";
		}
		return exception.getMessage();
	}
}
