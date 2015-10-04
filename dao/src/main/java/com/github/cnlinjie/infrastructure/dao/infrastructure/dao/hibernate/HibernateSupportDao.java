package com.github.cnlinjie.infrastructure.dao.infrastructure.dao.hibernate;

import com.github.cnlinjie.infrastructure.dao.infrastructure.dao.Page;
import com.github.cnlinjie.infrastructure.dao.infrastructure.dao.PageParams;
import com.github.cnlinjie.infrastructure.util.ReflectionUtils;
import com.github.cnlinjie.infrastructure.util.spring.Assert;
import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.AbstractQueryImpl;
import org.hibernate.transform.Transformers;
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

    protected String getTableName() {
        String name = ReflectionUtils.getSuperClassGenricType(getClass()).getName();
        return name;
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
        Assert.isNull(params.getProjection(),"查询值为实体，请勿设置 projection 值");
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
        Assert.isNull(params.getProjection(),"查询值为实体，请勿设置 projection 值");
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
        List list = criteria.list();
        if (list.size() > 0) {
            return (Object[]) list.get(0);
        }
        return null;
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
        return uniqueMap(CriteriaParams.Add(criterion).addProjection(projection));
    }

    @Override
    public Map<String, Object> uniqueMap(CriteriaParams params) {
        Map<String, Object> map = (Map<String, Object>) createCriteria(params)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                .uniqueResult();
        return map;
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
        Assert.notNull(params.getProjection(), "需要指定投影的列名");
        List<Map<String, Object>> maps = createCriteria(params)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                .list();
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
        Assert.notNull(params.getProjection(), "需要指定投影的列名");
        List<Map<String, Object>> maps =
                createCriteria(params)
                        .setFirstResult(pageParams.getStartRowByInt())
                        .setMaxResults(pageParams.getPageSize())
                        .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                        .list();
        Long total = getCountRow(params);
        Page<Map<String, Object>> page = new Page<Map<String, Object>>(maps, total, pageParams.getPageIndex(), pageParams.getPageSize());

/*
       // 自己实现的方式
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
*/
        return page;
    }


    /**
     * 设置查询参数
     *
     * @param query
     * @param values
     */
    protected Query setParameters(Query query, Object... values) {
        if (ArrayUtils.isEmpty(values)) {
            return query;
        }
        AbstractQueryImpl impl = (AbstractQueryImpl) query;
        String[] params = impl.getNamedParameters();

        int methodParameterPosition = params.length - 1;

        if (impl.hasNamedParameters()) {
            for (String p : params) {
                Object o = values[methodParameterPosition--];
                query.setParameter(p, o);
            }
        } else {
            for (Integer i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query;
    }

    protected Query createQuery(String hql) {
        return this.getSession().createQuery(hql);
    }


    protected Long getCountRow(String hql, Object... args) {
        Long o = (Long) setParameters(createQuery(getTotalHql(hql)), args).uniqueResult();
        return o;
    }

    protected Long getCountRow(String hql, Map<String, Object> args) {
        Long o = (Long) createQuery(getTotalHql(hql)).setProperties(args).uniqueResult();
        return o;
    }

    protected String getTotalHql(String hql) {
        String counthql = "select count(*) ";
        String upperhql = hql.toUpperCase();
        int start = upperhql.indexOf("FROM");
        int end = hql.length();
        counthql += hql.substring(start, end);
        logger.info(counthql);
        return counthql;
    }

    @Override
    public T unique(String hql, Object... args) {
        T t = (T) setParameters(createQuery(hql), args)
                .uniqueResult();
        return t;
    }

    @Override
    public T unique(String hql, Map<String, Object> args) {
        T t = (T) createQuery(hql)
                .setProperties(args)
                .uniqueResult();
        return t;
    }

    @Override
    public List<T> list(String hql, Object... args) {
        List list = setParameters(createQuery(hql), args).list();
        return list;
    }

    @Override
    public List<T> list(String hql, Map<String, Object> args) {
        List list = createQuery(hql).setProperties(args).list();
        return list;
    }

    @Override
    public Page<T> page(String hql, PageParams pageParams, Object... args) {
        List list = setParameters(createQuery(hql), args)
                .setFirstResult(pageParams.getStartRowByInt())
                .setMaxResults(pageParams.getPageSize())
                .list();
        Long totalCount = getCountRow(hql, args);
        Page<T> page = new Page<T>(list, totalCount, pageParams.getPageIndex(), pageParams.getPageSize());
        return page;
    }

    @Override
    public Page<T> page(String hql, PageParams pageParams, Map<String, Object> args) {
        List list = createQuery(hql)
                .setProperties(args)
                .setFirstResult(pageParams.getStartRowByInt())
                .setMaxResults(pageParams.getPageSize())
                .list();
        Long totalCount = getCountRow(hql, args);
        Page<T> page = new Page<T>(list, totalCount, pageParams.getPageIndex(), pageParams.getPageSize());
        return page;
    }

    @Override
    public <X> X uniqueValue(String hql, Object... args) {
        return (X) setParameters(createQuery(hql), args).uniqueResult();
    }

    @Override
    public <X> X uniqueValue(String hql, Map<String, Object> args) {
        return (X) createQuery(hql).setProperties(args).uniqueResult();
    }

    @Override
    public Object[] uniqueObject(String hql, Object... args) {
        List<Object[]> list = listObjects(hql, args);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public Object[] uniqueObject(String hql, Map<String, Object> args) {
        List<Object[]> list = listObjects(hql, args);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<Object[]> listObjects(String hql, Object... args) {
        List list = setParameters(createQuery(hql), args).list();
        return list;
    }

    @Override
    public List<Object[]> listObjects(String hql, Map<String, Object> args) {
        List list = createQuery(hql).setProperties(args).list();
        return list;
    }

    @Override
    public Page<Object[]> pageObjects(String hql, PageParams pageParams, Object... args) {
        List<Object[]> list = setParameters(createQuery(hql), args)
                .setFirstResult(pageParams.getStartRowByInt())
                .setMaxResults(pageParams.getPageSize())
                .list();
        Long totalCount = getCountRow(hql, args);
        Page<Object[]> page = new Page<Object[]>(list, totalCount, pageParams.getPageIndex(), pageParams.getPageSize());
        return page;
    }

    @Override
    public Page<Object[]> pageObjects(String hql, PageParams pageParams, Map<String, Object> args) {
        List<Object[]> list = createQuery(hql)
                .setProperties(args)
                .setFirstResult(pageParams.getStartRowByInt())
                .setMaxResults(pageParams.getPageSize())
                .list();
        Long totalCount = getCountRow(hql, args);
        Page<Object[]> page = new Page<Object[]>(list, totalCount, pageParams.getPageIndex(), pageParams.getPageSize());
        return page;
    }

    @Override
    public Map<String, Object> uniqueMap(String hql, Object... args) {
        Map<String, Object> map = (Map<String, Object>)
                setParameters(createQuery(hql), args)
                        .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                        .uniqueResult();
        return map;
    }

    @Override
    public Map<String, Object> uniqueMap(String hql, Map<String, Object> args) {
        Map<String, Object> map = (Map<String, Object>)
                createQuery(hql).setProperties(args)
                        .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                        .uniqueResult();
        return map;
    }

    @Override
    public List<Map<String, Object>> listMaps(String hql, Object... args) {
        List<Map<String, Object>> maps = setParameters(createQuery(hql), args)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                .list();
        return maps;
    }

    @Override
    public List<Map<String, Object>> listMaps(String hql, Map<String, Object> args) {
        List<Map<String, Object>> maps = createQuery(hql).setProperties(args)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                .list();
        return maps;
    }


    @Override
    public Page<Map<String, Object>> pageMaps(String hql, PageParams pageParams, Object... args) {
        List<Map<String, Object>> list = setParameters(createQuery(hql), args)
                .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                .setFirstResult(pageParams.getStartRowByInt())
                .setMaxResults(pageParams.getPageSize())
                .list();
        Long totalCount = getCountRow(hql, args);
        Page<Map<String, Object>> page = new Page<Map<String, Object>>(list, totalCount, pageParams.getPageIndex(), pageParams.getPageSize());
        return page;
    }

    @Override
    public Page<Map<String, Object>> pageMaps(String hql, PageParams pageParams, Map<String, Object> args) {
        List<Map<String, Object>> list =
                createQuery(hql)
                        .setProperties(args)
                        .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                        .setFirstResult(pageParams.getStartRowByInt())
                        .setMaxResults(pageParams.getPageSize())
                        .list();
        Long totalCount = getCountRow(hql, args);
        Page<Map<String, Object>> page = new Page<Map<String, Object>>(list, totalCount, pageParams.getPageIndex(), pageParams.getPageSize());
        return page;
    }


    @Override
    public <X> X uniqueBean(String hql, Class<? extends X> transferClass, Object... args) {
        X x = (X) setParameters(createQuery(hql), args)
                .setResultTransformer(Transformers.aliasToBean(transferClass))
                .uniqueResult();
        return x;
    }

    @Override
    public <X> X uniqueBean(String hql, Class<? extends X> transferClass, Map<String, Object> args) {
        X x = (X) createQuery(hql)
                .setProperties(args)
                .setResultTransformer(Transformers.aliasToBean(transferClass))
                .uniqueResult();
        return x;
    }

    @Override
    public <X> List<X> listBeans(String hql, Class<? extends X> transferClass, Object... args) {
        List<X> list = setParameters(createQuery(hql), args)
                .setResultTransformer(Transformers.aliasToBean(transferClass))
                .list();
        return list;
    }

    @Override
    public <X> List<X> listBeans(String hql, Class<? extends X> transferClass, Map<String, Object> args) {
        List<X> list = createQuery(hql).setProperties(args)
                .setResultTransformer(Transformers.aliasToBean(transferClass))
                .list();
        return list;
    }

    @Override
    public <X> Page<X> pageBeans(String hql, Class<? extends X> transferClass, PageParams pageParams, Object... args) {
        List<X> list = setParameters(createQuery(hql), args)
                .setFirstResult(pageParams.getStartRowByInt())
                .setMaxResults(pageParams.getPageSize())
                .setResultTransformer(Transformers.aliasToBean(transferClass))
                .list();

        Long totalCount = getCountRow(hql, args);
        Page<X> page = new Page<X>(list, totalCount, pageParams.getPageIndex(), pageParams.getPageSize());
        return page;
    }

    @Override
    public <X> Page<X> pageBeans(String hql, Class<? extends X> transferClass, PageParams pageParams, Map<String, Object> args) {
        List<X> list =
                createQuery(hql)
                        .setResultTransformer(Transformers.aliasToBean(transferClass))
                        .setFirstResult(pageParams.getStartRowByInt())
                        .setMaxResults(pageParams.getPageSize())
                        .setProperties(args)
                        .list();
        Long totalCount = getCountRow(hql, args);
        Page<X> page = new Page<X>(list, totalCount, pageParams.getPageIndex(), pageParams.getPageSize());
        return page;
    }
}
