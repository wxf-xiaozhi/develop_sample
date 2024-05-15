package com.sxnd.develop.sample.infrastructure.persistence.dao;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.*;

/**
 * @ClassName: MpServiceImpl
 * @description:
 * @author: xiaofang.wu
 * @create: 2024-05-15 15:11
 */
public class MpServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements MpService<T> {
    public MpServiceImpl() {
    }

    public T getOne(T model) {
        QueryWrapper<T> queryWrapper = Wrappers.query(model);
        return super.getOne(queryWrapper, true);
    }

    public T getOne(T model, boolean throwException) {
        QueryWrapper<T> queryWrapper = Wrappers.query(model);
        return super.getOne(queryWrapper, throwException);
    }

    public T getOneByList(T model) {
        QueryWrapper<T> queryWrapper = Wrappers.query(model);
        List<T> modelList = super.list(queryWrapper);
        return CollectionUtil.isEmpty(modelList) ? null : modelList.get(0);
    }

    public List<T> list(T model) {
        QueryWrapper<T> queryWrapper = Wrappers.query(model);
        return super.list(queryWrapper);
    }

    public List<T> listByIdList(Collection<? extends Serializable> idList) {
        if (CollectionUtil.isEmpty(idList)) {
            return Lists.newArrayList();
        } else {
            List<T> modelList = super.listByIds(new HashSet<>(idList));
            return modelList;
        }
    }





    public Long count(T model) {
        if (model == null) {
            return 0L;
        } else {
            QueryWrapper<T> queryWrapper = Wrappers.query(model);
            return super.count(queryWrapper);
        }
    }

    public boolean update(T toEntity, T whereEntity) {
        Wrapper<T> updateWrapper = Wrappers.update(whereEntity);
        return super.update(toEntity, updateWrapper);
    }
}
