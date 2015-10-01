package com.github.cnlinjie.infrastructure.dao.infrastructure.dao.hibernate;

import com.github.cnlinjie.infrastructure.dao.infrastructure.dao.Page;
import com.github.cnlinjie.infrastructure.dao.infrastructure.dao.PageParams;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Linjie
 * @date 2015/9/29.
 */
public interface IHibernateCriteriaDao<T, PK extends Serializable> {


    /**
     * 查询单个实体
     * @param criterion 查询条件
     * @return
     */
    public T unique(Criterion criterion);

    /**
     * 查询列表
     * @param criterion 查询条件
     * @return
     */
    public List<T> list(Criterion criterion);
    public List<T> list(Criterion criterion,Order order);
    public List<T> list(CriteriaParams params);

    /**
     * 查询分页 实体
     * @param criterion 查询条件
     * @param pageParams 分页条件
     * @return
     */

    public Page<T> page(Criterion criterion,PageParams pageParams);
    public Page<T> page(Criterion criterion,PageParams pageParams,Order order);
    public Page<T> page(CriteriaParams params,PageParams pageParams);
    public Page<T> page(PageParams pageParams);

    /**
     * 查询单个值
     * @param criterion  条件
     * @param projection 需要查询的字段
     * @return
     */
    public Object uniqueValue(Criterion criterion,Projection projection);

    /**
     * 查询单条记录，多个值
     * @param criterion
     * @param projection
     */
    public Object[] uniqueObject(Criterion criterion,Projection projection);

    /**
     * 查询多条记录
     * @param criterion 条件
     * @param projection 需要查询的字段
     * @return
     */
    public List<Object[]> listObjects(Criterion criterion,Projection projection);
    public List<Object[]> listObjects(Criterion criterion,Projection projection,Order order);
    public List<Object[]> listObjects(CriteriaParams params);


    /**
     * 分页查询多条记录 
     * @param criterion  查询条件
     * @param projection 需要查询的字段
     * @param pageParams 分页参数
     * @return
     */
    public List<Object[]> pageObjects(Criterion criterion,Projection projection,PageParams pageParams);
    public List<Object[]> pageObjects(Criterion criterion,Projection projection,Order order,PageParams pageParams);
    public List<Object[]> pageObjects(CriteriaParams params,PageParams pageParams);


    /**
     * 查询单挑记录，以Map的形式返回
     * @param criterion   查询条件
     * @param projection 需要查询的字段
     * @return
     */
    public Map<String,Object> uniqueMap(Criterion criterion,Projection projection);

    /**
     * 查询列表，以Map的形式
     * @param criterion  查询条件
     * @param projection 需要查询的字段
     * @return
     */
    public List<Map<String,Object>> listMaps(Criterion criterion,Projection projection);
    public List<Map<String,Object>> listMaps(Criterion criterion,Projection projection,Order order);
    public List<Map<String,Object>> listMaps(CriteriaParams params);

    /**
     * 查询分页，以Map的形式
     * @param criterion  查询条件
     * @param projection 需要查询的字段
     * @param pageParams 分页条件
     * @return
     */
    public Page<Map<String,Object>> pageMaps(Criterion criterion,Projection projection,PageParams pageParams);
    public Page<Map<String,Object>> pageMaps(Criterion criterion,Projection projection,Order order,PageParams pageParams);
    public Page<Map<String,Object>> pageMaps(CriteriaParams params,PageParams pageParams);



}
