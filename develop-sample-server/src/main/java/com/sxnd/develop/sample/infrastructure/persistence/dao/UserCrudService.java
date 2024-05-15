package com.sxnd.develop.sample.infrastructure.persistence.dao;

import com.sxnd.develop.sample.infrastructure.persistence.entity.User;
import com.sxnd.develop.sample.infrastructure.persistence.repository.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @ClassName: UserCrudService
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-05-15 15:10
 */
@Service
public class UserCrudService extends MpServiceImpl<UserMapper, User> {
}
