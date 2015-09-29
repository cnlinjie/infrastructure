package com.github.cnlinjie.infrastructure.dao.hibernate;

import com.github.cnlinjie.infrastructure.dao.IDao;
import com.github.cnlinjie.infrastructure.dao.Page;
import com.github.cnlinjie.infrastructure.dao.PageParams;
import com.github.cnlinjie.infrastructure.dao.QueryParams;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Linjie
 * @date 2015/9/21.
 */
public abstract class HibernateSupportDao<T, PK extends Serializable> implements IHibernateCriteriaDao<T, PK> , IHibernateHqlDao<T, PK>  {



    @Override
    public T unique(Criterion criterion) {
        return null;
    }

    @Override
    public List<T> list(Criterion criterion) {
        return null;
    }

    @Override
    public List<T> list(Criterion criterion, Order order) {
        return null;
    }

    @Override
    public Page<T> page(Criterion criterion, PageParams pageParams) {
        return null;
    }

    @Override
    public Page<T> page(Criterion criterion, PageParams pageParams, Order order) {
        return null;
    }

    @Override
    public Object uniqueValue(Criterion criterion, Projection projection) {
        return null;
    }

    @Override
    public Object[] uniqueObject(Criterion criterion, Projection projection) {
        return new Object[0];
    }

    @Override
    public List<Object[]> listObjects(Criterion criterion, Projection projection) {
        return null;
    }

    @Override
    public List<Object[]> listObjects(Criterion criterion, Projection projection, Order order) {
        return null;
    }

    @Override
    public List<Object[]> pageObjects(Criterion criterion, Projection projection, PageParams pageParams) {
        return null;
    }

    @Override
    public List<Object[]> pageObjects(Criterion criterion, Projection projection, Order order, PageParams pageParams) {
        return null;
    }

    @Override
    public Map<String, Object> uniqueMap(Criterion criterion, Projection projection) {
        return null;
    }

    @Override
    public List<Map<String, Object>> listMaps(Criterion criterion, Projection projection) {
        return null;
    }

    @Override
    public List<Map<String, Object>> listMaps(Criterion criterion, Projection projection, Order order) {
        return null;
    }

    @Override
    public Page<Map<String, Object>> pageMaps(Criterion criterion, Projection projection, PageParams pageParams) {
        return null;
    }

    @Override
    public Page<Map<String, Object>> pageMaps(Criterion criterion, Projection projection, Order order, PageParams pageParams) {
        return null;
    }

    @Override
    public T unique(String hql, Object... args) {
        return null;
    }

    @Override
    public T unique(String hql, Map<String, Object> args) {
        return null;
    }

    @Override
    public List<T> list(String hql, Object... args) {
        return null;
    }

    @Override
    public List<T> list(String hql, Map<String, Object> args) {
        return null;
    }

    @Override
    public Page<T> page(String hql, PageParams pageParams, Object... args) {
        return null;
    }

    @Override
    public Page<T> page(String hql, PageParams pageParams, Map<String, Object> args) {
        return null;
    }

    @Override
    public Object uniqueValue(String hql, Object... args) {
        return null;
    }

    @Override
    public Object uniqueValue(String hql, Map<String, Object> args) {
        return null;
    }

    @Override
    public Object[] uniqueObject(String hql, Object... args) {
        return new Object[0];
    }

    @Override
    public Object[] uniqueObject(String hql, Map<String, Object> args) {
        return new Object[0];
    }

    @Override
    public List<Object[]> listObjects(String hql, Object... args) {
        return null;
    }

    @Override
    public List<Object[]> listObjects(String hql, Map<String, Object> args) {
        return null;
    }

    @Override
    public Page<Object[]> pageObjects(String hql, PageParams pageParams, Object... args) {
        return null;
    }

    @Override
    public Page<Object[]> pageObjects(String hql, PageParams pageParams, Map<String, Object> args) {
        return null;
    }

    @Override
    public Map<String, Object> uniqueMap(String hql, Object... args) {
        return null;
    }

    @Override
    public Map<String, Object> uniqueMap(String hql, Map<String, Object> args) {
        return null;
    }

    @Override
    public List<Map<String, Object>> listMaps(String hql, Object... args) {
        return null;
    }

    @Override
    public List<Map<String, Object>> listMaps(String hql, Map<String, Object> args) {
        return null;
    }

    @Override
    public Page<Map<String, Object>> pageMaps(String hql, Object... args) {
        return null;
    }

    @Override
    public Page<Map<String, Object>> pageMaps(String hql, Map<String, Object> args) {
        return null;
    }
}
