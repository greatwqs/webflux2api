package com.greatwqs.app.service.impl;

import com.greatwqs.app.domain.po.VideoPo;
import com.greatwqs.app.domain.vo.VideoInfoVo;
import com.greatwqs.app.mapper.video.VideolistMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatwqs.app.common.exception.AppException;
import com.greatwqs.app.common.exception.ErrorCode;
import com.greatwqs.app.service.VideoService;

/**
 *
 * VideoService implementation
 *
 * @author wang.qingsong
 * Create on 2019/9/23
 */
@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VideolistMapper videolistMapper;

    private VideoPo getPo(Long videoId) {
        return videolistMapper.selectByPrimaryKey(videoId);
    }

    @Override
    public VideoInfoVo getVideoInfo(Long videoId) {
        VideoPo video = this.getPo(videoId);
        if (video == null) {
            throw new AppException(ErrorCode.NOT_FOUND, "视频未找到!");
        }
        VideoInfoVo videoInfoVo = modelMapper.map(video, VideoInfoVo.class);
        return videoInfoVo;
    }
}
