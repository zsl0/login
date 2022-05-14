package com.zsl.service.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Author zsl
 * @Date 2021/12/29 11:25
 */
public interface BaseService<T> {
    /**
     * 查询所有
     */
    List<T> selectAll();

    /**
     * 通过主键查询
     */
    T selectById(long id);

    /**
     * 添加
     */
    int insert(T t);

    /**
     * 通过主键删除
     */
    void deleteById(long id);

    /**
     * 删除多个
     */
    void deleteIds(Collection<? extends Serializable> idList);

    /**
     * 更新
     */
    void updateById(T t);

    /**
     * 获取个数
     */
    long count();
}
