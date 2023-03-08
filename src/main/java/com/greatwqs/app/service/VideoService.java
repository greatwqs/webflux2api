package com.greatwqs.app.service;

import com.greatwqs.app.domain.vo.VideoInfoVo;

/**
 *
 * VideoService
 *
 * @author wang.qingsong
 * Create on 2019/9/23
 */
public interface VideoService {

    /***
     * getVideoInfo
     * @param videoId
     * @return
     */
    VideoInfoVo getVideoInfo(Long videoId);
}
