package com.greatwqs.app.domain.po;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * create on 2019/09/21
 *
 * @author wang.qingsong
 */
@Getter
@Setter
@ToString
public class UserPo {

	private Long id;

	private String userName;

	private String address;

	private Byte isValid;

	private Date createTime;

	private Date updateTime;
}
