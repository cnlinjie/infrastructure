package com.github.cnlinjie.infrastructure.dao;

import com.github.cnlinjie.infrastructure.dao.hibernate.HibernateSupportDao;
import com.github.cnlinjie.infrastructure.dao.nativesql.INativeSqlDao;
import com.github.cnlinjie.infrastructure.dao.nativesql.NativeSqlDaoImpl;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Linjie
 * @date 2015/10/1.
 */
public class DaoImpl<T,PK extends Serializable> extends HibernateSupportDao<T,PK> implements IDao<T,PK> , INativeSqlDao {


    private NativeSqlDaoImpl sqlDao = new NativeSqlDaoImpl();

    
    public void setSessionFactory(SessionFactory sessionFactory) {
        sqlDao.setSessionFactory(sessionFactory);
        super.setSessionFactory(sessionFactory);
    }

    
    public void save(T t) {
        this.getSession().save(t);
    }

    
    public void update(T t) {
        this.getSession().update(t);
    }

    
    public void delete(PK pk) {
        this.getSession().delete(pk);
    }

    
    public void delete(T t) {
        this.getSession().delete(t);
    }

    
    public void saveOrUpdate(T t) {
        this.getSession().saveOrUpdate(t);
    }

    
    public int executeUpdate(String hql, Map<String, Object> args) {
        int i = createQuery(hql)
                .setProperties(args)
                .executeUpdate();
        return i;
    }

    
    public int executeUpdate(String hql, Object... args) {
        int i = setParameters(createQuery(hql), args)
                .executeUpdate();
        return i;
    }

    
    public int delete(List<PK> ids) {
        String hql  = " delete from " + getTableName() + " t where t.id in (:ids)";
        int i = createQuery(hql)
                .setParameterList("ids", ids)
                .executeUpdate();
        return i;
    }

    
    public T get(PK pk) {
        T t = (T) this.getSession().get(this.entityClass, pk);
        return t;
    }

    
    public T find(String key, String value) {
        T unique = this.unique(
                Restrictions.eq(key, value)
        );
        return unique;
    }

    
    public Page<T> findPage(PageParams params) {
        String hql = " from " + getTableName()  ;
        Page<T> page = page(hql, params);
        return page;
    }

    
    public List<T> findAll() {
        String hql = " from " + getTableName()  ;
        List list = createQuery(hql).list();
        return list;
    }


    
    public <X> X sqlUniqueValue(String sql, Object... args) {
        return sqlDao.sqlUniqueValue(sql, args);
    }

    
    public <X> X sqlUniqueValue(String sql, Map<String, Object> args) {
        return sqlDao.sqlUniqueValue(sql, args);
    }

    
    public Object[] sqlUniqueObject(String sql, Object... args) {
        return sqlDao.sqlUniqueObject(sql, args);
    }

    
    public Object[] sqlUniqueObject(String sql, Map<String, Object> args) {
        return sqlDao.sqlUniqueObject(sql, args);
    }

    
    public <X> X sqlUniqueBean(String sql, Class<? extends X> transferClass, Object... args) {
        return sqlDao.sqlUniqueBean(sql, transferClass, args);
    }

    
    public <X> X sqlUniqueBean(String sql, Class<? extends X> transferClass, Map<String, Object> args) {
        return sqlDao.sqlUniqueBean(sql,transferClass,args);
    }

    
    public List<Object[]> sqlListObjects(String sql, Object... args) {
        return sqlDao.sqlListObjects(sql,args);
    }

    
    public List<Object[]> sqlListObjects(String sql, Map<String, Object> args) {
        return sqlDao.sqlListObjects(sql,args);
    }

    
    public Page<Object[]> sqlPageObjects(String sql, PageParams pageParams, Object... args) {
        return  sqlDao.sqlPageObjects(sql, pageParams, args);
    }

    
    public Page<Object[]> sqlPageObjects(String sql, PageParams pageParams, Map<String, Object> args) {
        return sqlDao.sqlPageObjects(sql,pageParams,args);
    }

    
    public <X> List<X> sqlListBeans(String sql, Class<? extends X> transferClass, Object... args) {
        return sqlDao.sqlListBeans(sql, transferClass, args);
    }

    
    public <X> List<X> sqlListBeans(String sql, Class<? extends X> transferClass, Map<String, Object> args) {
        return sqlDao.sqlListBeans(sql,transferClass,args);
    }

    
    public <X> Page<X> sqlPageBeans(String sql, Class<? extends X> transferClass, PageParams pageParams, Object... args) {
        return sqlDao.sqlPageBeans(sql, transferClass, pageParams, args);
    }

    
    public <X> Page<X> sqlPageBeans(String sql, Class<? extends X> transferClass, PageParams pageParams, Map<String, Object> args) {
        return sqlDao.sqlPageBeans(sql,transferClass,pageParams,args);
    }

    
    public List<Map<String, Object>> sqlListMaps(String sql, Object... args) {
        return  sqlDao.sqlListMaps(sql,args);
    }

    
    public List<Map<String, Object>> sqlListMaps(String sql, Map<String, Object> args) {
        return sqlDao.sqlListMaps(sql,args);
    }

    
    public List<Map<String, Object>> sqlListMaps(String sql, String[] fields, Object... args) {
        return sqlDao.sqlListMaps(sql,fields,args);
    }

    
    public List<Map<String, Object>> sqlListMaps(String sql, String[] fields, Map<String, Object> args) {
        return sqlDao.sqlListMaps(sql,fields,args);
    }

    
    public Page<Map<String, Object>> sqlPageMaps(String sql, PageParams pageParams, Object... args) {
        return sqlDao.sqlPageMaps(sql, pageParams, args);
    }

    
    public Page<Map<String, Object>> sqlPageMaps(String sql, PageParams pageParams, Map<String, Object> args) {
        return sqlDao.sqlPageMaps(sql, pageParams,args);
    }

    
    public Page<Map<String, Object>> sqlPageMaps(String sql, PageParams pageParams, String[] fields, Object... args) {
        return sqlDao.sqlPageMaps(sql, pageParams,fields,args);
    }

    
    public Page<Map<String, Object>> sqlPageMaps(String sql, PageParams pageParams, String[] fields, Map<String, Object> args) {
        return sqlDao.sqlPageMaps(sql, pageParams,fields,args);
    }

    
    public int sqlExecute(String sql, Object... args) {
        return sqlDao.sqlExecute(sql,args);
    }

    
    public int sqlExecute(String sql, Map<String, Object> args) {
        return sqlDao.sqlExecute(sql,args);
    }
}
