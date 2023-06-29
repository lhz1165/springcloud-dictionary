package com.whq.springtest_client.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whq.springtest_client.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
