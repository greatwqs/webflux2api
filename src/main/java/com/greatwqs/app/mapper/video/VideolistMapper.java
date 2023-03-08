package com.greatwqs.app.mapper.video;

import org.apache.ibatis.annotations.Mapper;

import com.greatwqs.app.domain.po.VideoPo;

/**
 * Created by MyBatis Generator 2019/09/23
 */
@Mapper
public interface VideolistMapper {

	VideoPo selectByPrimaryKey(Long id);
}