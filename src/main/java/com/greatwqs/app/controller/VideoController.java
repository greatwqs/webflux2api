package com.greatwqs.app.controller;

import com.greatwqs.app.domain.vo.VideoInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greatwqs.app.component.RequestComponent;
import com.greatwqs.app.service.VideoService;
import lombok.extern.slf4j.Slf4j;

/**
 * 返回 Json 中的视图
 *
 * @author wang.qingsong
 * Create on 2023/03/08
 */
@Slf4j
@RequestMapping("video")
@RestController
public class VideoController {

	@Autowired
	private RequestComponent requestComponent;

	@Autowired
	private VideoService videoService;

	/**
	 * 用JSON呈现试图
	 * http://localhost:8080/video
	 * http://localhost:8080/video?videoId=2
	 * @param videoId
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public VideoInfoVo getVideoInfo(
		@RequestParam(value = "videoId", defaultValue = "1") Long videoId) {
		log.warn("uri: " + requestComponent.getRequestUri() + ", videoId: " + videoId);
		return videoService.getVideoInfo(videoId);
	}

}
