package com.greatwqs.app.domain.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VideoInfoVo {
	private Integer id;

	private String title;

	private String videoSourceUrl;

	private String content;

	private Byte isValid;

	private Date createTime;

	private Date updateTime;
}