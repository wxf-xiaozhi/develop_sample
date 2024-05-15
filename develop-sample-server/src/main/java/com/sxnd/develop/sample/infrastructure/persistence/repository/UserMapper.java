package com.sxnd.develop.sample.infrastructure.persistence.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sxnd.develop.sample.infrastructure.persistence.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName: UserMapper
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-05-15 15:04
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
