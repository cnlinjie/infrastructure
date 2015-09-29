package com.github.cnlinjie.infrastructure.dao;

import com.github.cnlinjie.infrastructure.dao.hibernate.HibernateSupportDao;
import com.github.cnlinjie.infrastructure.dao.nativesql.INativeSqlDao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Linjie
 * @date 2015/9/30.
 */
public class DaoImpl<T, PK extends Serializable>
        extends HibernateSupportDao<T,PK>
        implements INativeSqlDao,IDao {


    @Override
    public Object sqlUniqueValue(String sql, Object... args) {
        return null;
    }

    @Override
    public Object sqlUniqueValue(String sql, Map<String, Object> args) {
        return null;
    }

    @Override
    public Object[] sqlUniqueObject(String sql, Object... args) {
        return new Object[0];
    }

    @Override
    public Object[] sqlUniqueObject(String sql, Map<String, Object> args) {
        return new Object[0];
    }

    @Override
    public <X> X sqlUniqueObject(String sql, Class<? extends X> transferClass, Object... args) {
        return null;
    }

    @Override
    public <X> X sqlUniqueObject(String sql, Class<? extends X> transferClass, Map<String, Object> args) {
        return null;
    }

    @Override
    public List<Object[]> sqlListObjects(String sql, Object... args) {
        return null;
    }

    @Override
    public List<Object[]> sqlListObjects(String sql, Map<String, Object> args) {
        return null;
    }

    @Override
    public Page<Object[]> sqlPageObjects(String sql, PageParams pageParams, Object... args) {
        return null;
    }

    @Override
    public Page<Object[]> sqlPageObjects(String sql, PageParams pageParams, Map<String, Object> args) {
        return null;
    }

    @Override
    public <X> List<X> sqlListObjects(String sql, Class<? extends X> transferClass, Object... args) {
        return null;
    }

    @Override
    public <X> List<X> sqlListObjects(String sql, Class<? extends X> transferClass, Map<String, Object> args) {
        return null;
    }

    @Override
    public <X> Page<X> sqlPageObjects(String sql, Class<? extends X> transferClass, PageParams pageParams, Object... args) {
        return null;
    }

    @Override
    public <X> Page<X> sqlPageObjects(String sql, Class<? extends X> transferClass, PageParams pageParams, Map<String, Object> args) {
        return null;
    }

    @Override
    public Page<Map<String, Object>> sqlPageMaps(String hql, PageParams pageParams, Object... args) {
        return null;
    }

    @Override
    public Page<Map<String, Object>> sqlPageMaps(String hql, PageParams pageParams, Map<String, Object> args) {
        return null;
    }

    @Override
    public Page<Map<String, Object>> sqlPageMaps(String hql, PageParams pageParams, String[] fields, Object... args) {
        return null;
    }

    @Override
    public Page<Map<String, Object>> sqlPageMaps(String hql, PageParams pageParams, String[] fields, Map<String, Object> args) {
        return null;
    }

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
