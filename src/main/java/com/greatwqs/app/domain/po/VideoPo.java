package com.greatwqs.app.domain.po;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by MyBatis Generator 2019/09/23
 */
@Getter
@Setter
@ToString
public class VideoPo {
	private Long id;

	private String title;

	private String videoSourceUrl;

	private String content;

	private Byte isValid;

	private Date createTime;

	private Date updateTime;
}