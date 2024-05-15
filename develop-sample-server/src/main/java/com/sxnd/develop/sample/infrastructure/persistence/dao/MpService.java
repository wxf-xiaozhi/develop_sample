package com.sxnd.develop.sample.infrastructure.persistence.dao;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @ClassName: MpService
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-05-15 15:12
 */
public interface MpService<T> extends IService<T> {
    T getOne(T var1);

    List<T> list(T var1);
}
