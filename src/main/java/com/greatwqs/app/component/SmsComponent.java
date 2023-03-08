package com.greatwqs.app.component;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * SmsComponent
 *
 * @author wang.qingsong
 * Create on 2023/03/08
 */
@Slf4j
@Component
public class SmsComponent {

	@Async
	public void sendSms(String phoneNo, String smsCode) {
		log.warn("phoneNo: " + phoneNo + ", smsCode: " + smsCode);
	}
}
