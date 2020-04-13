package com.msunsoft.spt.business.service.common;

import com.msunsoft.spt.business.dto.AbstractBaseDomain;
import com.github.pagehelper.PageInfo;

/**
 * 通用的业务逻辑
 * <p>Title: BaseCrudService</p>
 * <p>Description: </p>
 *
 * @author zxl
 * @version 1.0.0
 * @date 2020/4/1 9:43
 */
public interface BaseCrudService<T extends AbstractBaseDomain> {

    /**
     * 查询属性值是否唯一
     *
     * @param property
     * @param value
     * @return true/唯一，false/不唯一
     */
    default boolean unique(String property, String value) {
        return false;
    }

    /**
     * 保存
     *
     * @param domain
     * @return
     */
    default T save(T domain) {
        return null;
    }

    /**
     * 分页查询
     * @param domain
     * @param pageNum
     * @param pageSize
     * @return
     */
    default PageInfo<T> page(T domain, int pageNum, int pageSize) {
        return null;
    }

    /**
     * 局部更新
     *
     * @param domain
     * @return
     */
    default int updateSelective(T domain) {
        return 0;
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    default int deleteById(Object id) {
        return 0;
    }
}
