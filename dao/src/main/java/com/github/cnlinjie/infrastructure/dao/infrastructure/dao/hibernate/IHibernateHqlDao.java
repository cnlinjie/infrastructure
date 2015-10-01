package com.github.cnlinjie.infrastructure.dao.infrastructure.dao.hibernate;

import com.github.cnlinjie.infrastructure.dao.infrastructure.dao.Page;
import com.github.cnlinjie.infrastructure.dao.infrastructure.dao.PageParams;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Linjie
 * @date 2015/9/29.
 */
public interface IHibernateHqlDao<T, PK extends Serializable> {


    public T unique(String hql, Object... args); // 以?的形式，顺序

    public T unique(String hql, Map<String, Object> args);  // 以 :params 的形式

    public List<T> list(String hql, Object... args);

    public List<T> list(String hql, Map<String, Object> args);

    public Page<T> page(String hql, PageParams pageParams, Object... args);

    public Page<T> page(String hql, PageParams pageParams, Map<String, Object> args);


    public Object uniqueValue(String hql, Object... args);

    public Object uniqueValue(String hql, Map<String, Object> args);

    public Object[] uniqueObject(String hql, Object... args);

    public Object[] uniqueObject(String hql, Map<String, Object> args);


    public List<Object[]> listObjects(String hql, Object... args);

    public List<Object[]> listObjects(String hql, Map<String, Object> args);


    public Page<Object[]> pageObjects(String hql, PageParams pageParams, Object... args);

    public Page<Object[]> pageObjects(String hql, PageParams pageParams, Map<String, Object> args);

    public Map<String, Object> uniqueMap(String hql, Object... args);

    public Map<String, Object> uniqueMap(String hql, Map<String, Object> args);


    public List<Map<String, Object>> listMaps(String hql, Object... args);

    public List<Map<String, Object>> listMaps(String hql, Map<String, Object> args);


    public Page<Map<String, Object>> pageMaps(String hql, Object... args);

    public Page<Map<String, Object>> pageMaps(String hql, Map<String, Object> args);


}
