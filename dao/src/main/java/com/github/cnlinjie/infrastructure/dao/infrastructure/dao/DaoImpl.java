package com.github.cnlinjie.infrastructure.dao.infrastructure.dao;

import com.github.cnlinjie.infrastructure.dao.infrastructure.dao.hibernate.HibernateSupportDao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Linjie
 * @date 2015/10/1.
 */
public class DaoImpl<T,PK extends Serializable> extends HibernateSupportDao<T,PK> implements IDao<T,PK> {

    @Override
    public void save(T t) {
        this.getSession().save(t);
    }

    @Override
    public void update(T t) {
        this.getSession().update(t);
    }

    @Override
    public void delete(PK pk) {
        this.getSession().delete(pk);
    }

    @Override
    public void delete(T t) {
        this.getSession().delete(t);
    }

    @Override
    public void saveOrUpdate(T t) {
        this.getSession().saveOrUpdate(t);
    }

    @Override
    public int executeUpdate(String queryString, Map<String, Object> args) {
        return 0;
    }

    @Override
    public int executeUpdate(String queryString, Object... args) {
        return 0;
    }

    @Override
    public int delete(List<PK> ids) {
        return 0;
    }

    @Override
    public T get(PK pk) {
        return null;
    }

    @Override
    public T find(String key, String value) {
        return null;
    }

    @Override
    public T find(QueryParams params) {
        return null;
    }

    @Override
    public Page<T> findPage(QueryParams params) {
        return null;
    }

    @Override
    public List<T> findlist(QueryParams params) {
        return null;
    }

    @Override
    public List<T> findAll() {
        return null;
    }
}
