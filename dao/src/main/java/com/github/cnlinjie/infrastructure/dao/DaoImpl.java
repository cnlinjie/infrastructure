package com.github.cnlinjie.infrastructure.dao;

import com.github.cnlinjie.infrastructure.dao.hibernate.HibernateSupportDao;
import org.hibernate.criterion.Restrictions;

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
    public int executeUpdate(String hql, Map<String, Object> args) {
        int i = createQuery(hql)
                .setProperties(args)
                .executeUpdate();
        return i;
    }

    @Override
    public int executeUpdate(String hql, Object... args) {
        int i = setParameters(createQuery(hql), args)
                .executeUpdate();
        return i;
    }

    @Override
    public int delete(List<PK> ids) {
        return 0;
    }

    @Override
    public T get(PK pk) {
        T t = (T) this.getSession().get(this.entityClass, pk);
        return t;
    }

    @Override
    public T find(String key, String value) {
        T unique = this.unique(
                Restrictions.eq(key, value)
        );
        return unique;
    }

    @Override
    public Page<T> findPage(PageParams params) {
        String hql = " from " + getTableName()  ;
        Page<T> page = page(hql, params);
        return page;
    }

    @Override
    public List<T> findAll() {
        String hql = " from " + getTableName()  ;
        List list = createQuery(hql).list();
        return list;
    }
}
