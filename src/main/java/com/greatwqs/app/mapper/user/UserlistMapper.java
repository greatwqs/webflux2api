package com.greatwqs.app.mapper.user;

import com.greatwqs.app.domain.po.UserPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * create on 2019/09/21
 *
 * @author wang.qingsong
 */
@Mapper
public interface UserlistMapper {

    /**
     * @param userId
     * @return
     */
    UserPo findByUserId(@Param("userId") Long userId);
}
