package com.github.cnlinjie.infrastructure.dao.infrastructure.dao.nativesql;

import com.github.cnlinjie.infrastructure.dao.infrastructure.dao.Page;
import com.github.cnlinjie.infrastructure.dao.infrastructure.dao.PageParams;
import com.github.cnlinjie.infrastructure.util.ReflectionUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author Linjie
 * @date 2015/10/1.
 */
public class NativeSqlDaoImpl implements INativeSqlDao {


    private boolean useCurrentSession = false;

    protected SessionFactory sessionFactory;

    private Session session;


    private static Logger logger = LoggerFactory.getLogger(NativeSqlDaoImpl.class);

    public NativeSqlDaoImpl() {
    }

    /**
     * 设置Hibernate sessionFactory
     *
     * @param sessionFactory
     */
    @Autowired(required = false)
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * 获取Hibernate SessionFactory
     *
     * @return {@link org.hibernate.SessionFactory}
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * 取得当前Session.
     *
     * @return {@link org.hibernate.Session}
     */
    public Session getSession() {
        if (null != session && session.isOpen()) {
            return session;
        }
        if (useCurrentSession) {
            this.session = sessionFactory.getCurrentSession();
        } else {
            this.session = sessionFactory.openSession();
        }
        return session;
    }


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
