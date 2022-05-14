package com.zsl.service.base.impl;

import com.zsl.mapper.base.MyBaseMapper;
import com.zsl.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Author zsl
 * @Date 2021/12/29 11:25
 */
public class BaseServiceImpl<T, M extends MyBaseMapper<T>> implements BaseService<T> {
    @Autowired
    protected M baseMapper;

    @Override
    public List<T> selectAll() {
        return baseMapper.selectList(null);
    }

    @Override
    public T selectById(long id) {
        return baseMapper.selectById(id);
    }

    @Override
    public int insert(T t) {
        return baseMapper.insert(t);
    }

    @Override
    public void deleteById(long id) {
        baseMapper.deleteById(id);
    }

    @Override
    public void deleteIds(Collection<? extends Serializable> idList) {
        baseMapper.deleteBatchIds(idList);
    }

    @Override
    public void updateById(T t) {
        baseMapper.updateById(t);
    }

    @Override
    public long count() {
        return baseMapper.selectCount(null);
    }
}
