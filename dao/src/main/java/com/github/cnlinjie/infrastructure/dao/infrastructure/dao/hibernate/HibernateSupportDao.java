package com.github.cnlinjie.infrastructure.dao.infrastructure.dao.hibernate;

import com.github.cnlinjie.infrastructure.dao.infrastructure.dao.Page;
import com.github.cnlinjie.infrastructure.dao.infrastructure.dao.PageParams;
import com.github.cnlinjie.infrastructure.util.ReflectionUtils;
import com.github.cnlinjie.infrastructure.util.spring.Assert;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Linjie
 * @date 2015/9/21.
 */
public abstract class HibernateSupportDao<T, PK extends Serializable> implements IHibernateCriteriaDao<T, PK>, IHibernateHqlDao<T, PK> {

    private boolean useCurrentSession = false;

    protected SessionFactory sessionFactory;

    private Session session;

    protected Class<T> entityClass;

    private static Logger logger = LoggerFactory.getLogger(HibernateSupportDao.class);

    public HibernateSupportDao() {
        this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
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


    /**
     * 根据Criterion可变数组创建Criteria对象
     *
     * @param params 可变长度的Criterion数组
     * @return @return {@link org.hibernate.Criteria}
     */
    protected Criteria createCriteria(CriteriaParams params) {

        Criteria criteria = getSession().createCriteria(this.entityClass);

        for (Criterion criterion : params.getCriterions()) {
            criteria.add(criterion);
        }
        for (Order order : params.getOrders()) {
            criteria.addOrder(order);
        }
        if (null != params.getProjection()) {
            criteria.setProjection(params.getProjection());
        }
        return criteria;
    }

    protected Long getCountRow(CriteriaParams params) {
        Object total = createCriteria(
                CriteriaParams
                        .Add(params.getCriterions().toArray(new Criterion[]{}))
                        .addProjection(Projections.rowCount()))
                .uniqueResult();
        return (Long) total;
    }


    @Override
    public T unique(Criterion criterion) {
        Criteria criteria = createCriteria(CriteriaParams.Add(criterion));
        return (T) criteria.uniqueResult();
    }

    @Override
    public List<T> list(Criterion criterion) {
        return list(CriteriaParams.Add(criterion));
    }

    @Override
    public List<T> list(Criterion criterion, Order order) {
        return list(CriteriaParams.Add(criterion).addOrder(order));
    }

    @Override
    public List<T> list(CriteriaParams params) {
        return createCriteria(params).list();
    }

    @Override
    public Page<T> page(Criterion criterion, PageParams pageParams) {
        return page(CriteriaParams.Add(criterion), pageParams);
    }

    @Override
    public Page<T> page(Criterion criterion, PageParams pageParams, Order order) {
        return page(
                CriteriaParams
                        .Add(criterion)
                        .addOrder(order),
                pageParams);
    }

    @Override
    public Page<T> page(CriteriaParams params, PageParams pageParams) {

        Criteria criteria =
                createCriteria(params)
                        .setFirstResult(pageParams.getStartRowByInt())
                        .setMaxResults(pageParams.getPageSize());
        Long total = getCountRow(params);
        List<T> list = criteria.list();
        Page<T> page = new Page<T>(list, total, pageParams.getPageIndex(), pageParams.getPageSize());
        return page;
    }

    @Override
    public Page<T> page(PageParams pageParams) {
        return page(CriteriaParams.get(), pageParams);
    }

    @Override
    public Object uniqueValue(Criterion criterion, Projection projection) {
        Criteria criteria = createCriteria(
                CriteriaParams
                        .Add(criterion)
                        .addProjection(projection)

        );
        return criteria.uniqueResult();
    }

    @Override
    public Object[] uniqueObject(Criterion criterion, Projection projection) {
        Criteria criteria = createCriteria(
                CriteriaParams
                        .Add(criterion)
                        .addProjection(projection)

        );
        Object[] o = (Object[]) criteria.list().get(0);
        return o;
    }

    @Override
    public List<Object[]> listObjects(Criterion criterion, Projection projection) {

        return listObjects(CriteriaParams
                .Add(criterion)
                .addProjection(projection));
    }

    @Override
    public List<Object[]> listObjects(Criterion criterion, Projection projection, Order order) {
        return listObjects(CriteriaParams
                .Add(criterion)
                .addProjection(projection)
                .addOrder(order));
    }

    @Override
    public List<Object[]> listObjects(CriteriaParams params) {
        Criteria criteria = createCriteria(params);
        List<Object[]> list = criteria.list();
        return list;
    }

    @Override
    public Page<Object[]> pageObjects(Criterion criterion, Projection projection, PageParams pageParams) {
        return pageObjects(
                CriteriaParams
                        .Add(criterion)
                        .addProjection(projection)
                , pageParams
        );
    }

    @Override
    public Page<Object[]> pageObjects(Criterion criterion, Projection projection, Order order, PageParams pageParams) {

        return pageObjects(
                CriteriaParams
                        .Add(criterion)
                        .addProjection(projection)
                        .addOrder(order)
                , pageParams
        );
    }

    @Override
    public Page<Object[]> pageObjects(CriteriaParams params, PageParams pageParams) {
        Criteria criteria = createCriteria(params)
                .setFirstResult(pageParams.getStartRowByInt())
                .setMaxResults(pageParams.getPageSize());
        Long total = getCountRow(params);
        List<Object[]> list = criteria.list();
        Page<Object[]> page = new Page<Object[]>(list, total, pageParams.getPageIndex(), pageParams.getPageSize());
        return page;
    }

    @Override
    public Map<String, Object> uniqueMap(Criterion criterion, Projection projection) {
        Object[] os = uniqueObject(criterion, projection);
        return QueryUtil.arrayToMap(os, projection.getAliases());
    }

    @Override
    public List<Map<String, Object>> listMaps(Criterion criterion, Projection projection) {
        return listMaps(
                CriteriaParams
                        .Add(criterion)
                        .addProjection(projection));
    }

    @Override
    public List<Map<String, Object>> listMaps(Criterion criterion, Projection projection, Order order) {
        return listMaps(
                CriteriaParams
                        .Add(criterion)
                        .addProjection(projection)
                        .addOrder(order));
    }

    @Override
    public List<Map<String, Object>> listMaps(CriteriaParams params) {
        List<Object[]> list = createCriteria(params).list();
        String[] fieldNames = params.getProjection().getAliases();
        List<Map<String, Object>> maps = QueryUtil.arraysToMaps(list, fieldNames);
        return maps;
    }

    @Override
    public Page<Map<String, Object>> pageMaps(Criterion criterion, Projection projection, PageParams pageParams) {
        return pageMaps(CriteriaParams
                        .Add(criterion)
                        .addProjection(projection),
                pageParams);
    }

    @Override
    public Page<Map<String, Object>> pageMaps(Criterion criterion, Projection projection, Order order, PageParams pageParams) {

        return pageMaps(CriteriaParams
                        .Add(criterion)
                        .addProjection(projection)
                        .addOrder(order),
                pageParams);
    }

    @Override
    public Page<Map<String, Object>> pageMaps(CriteriaParams params, PageParams pageParams) {
        List<Object[]> list =
                createCriteria(params)
                        .setFirstResult(pageParams.getStartRowByInt())
                        .setMaxResults(pageParams.getPageSize())
                        .list();
        Long total = getCountRow(params);
        Assert.notNull(params.getProjection(), "需要指定投影的列名");
        String[] fieldNames = params.getProjection().getAliases();
        List<Map<String, Object>> maps = QueryUtil.arraysToMaps(list, fieldNames);
        Page<Map<String, Object>> page = new Page<Map<String, Object>>(maps, total, pageParams.getPageIndex(), pageParams.getPageSize());
        return page;
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
