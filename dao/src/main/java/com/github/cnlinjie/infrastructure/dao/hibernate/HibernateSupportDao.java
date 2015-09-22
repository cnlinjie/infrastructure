package com.github.cnlinjie.infrastructure.dao.hibernate;

import com.github.cnlinjie.infrastructure.dao.IDao;
import com.github.cnlinjie.infrastructure.dao.Page;
import com.github.cnlinjie.infrastructure.dao.QueryParams;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Linjie
 * @date 2015/9/21.
 */
public class HibernateSupportDao implements IDao {




    @Override
    public Object get(Serializable serializable) {
        return null;
    }

    @Override
    public Object find(String key, String value) {
        return null;
    }

    @Override
    public Object find(QueryParams params) {
        return null;
    }

    @Override
    public Page findPage(QueryParams params) {
        return null;
    }

    @Override
    public List findlist(QueryParams params) {
        return null;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Serializable save(Object o) {
        return null;
    }

    @Override
    public void update(Object o) {

    }

    @Override
    public void delete(Serializable serializable) {

    }

    @Override
    public void delete(Object o) {

    }

    @Override
    public void saveOrUpdate(Object o) {

    }

    @Override
    public int delete(List list) {
        return 0;
    }

    @Override
    public int executeUpdate(String queryString, Map params) {
        return 0;
    }
}
