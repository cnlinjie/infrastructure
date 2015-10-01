package com.github.cnlinjie.infrastructure.dao.infrastructure.dao.nativesql;

import com.github.cnlinjie.infrastructure.dao.infrastructure.dao.Page;
import com.github.cnlinjie.infrastructure.dao.infrastructure.dao.PageParams;

import java.util.List;
import java.util.Map;

/**
 * @author Linjie
 * @date 2015/10/1.
 */
public class NativeSqlDaoImpl implements INativeSqlDao {

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
}
