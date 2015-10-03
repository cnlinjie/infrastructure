package com.github.cnlinjie.infrastructure.dao.infrastructure.dao.nativesql;

import com.github.cnlinjie.infrastructure.dao.infrastructure.dao.Page;
import com.github.cnlinjie.infrastructure.dao.infrastructure.dao.PageParams;
import com.github.cnlinjie.infrastructure.dao.infrastructure.dao.hibernate.QueryUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.AbstractQueryImpl;
import org.hibernate.transform.Transformers;
import org.linjie.framework.util.MappingUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.transform.Transformer;
import java.math.BigInteger;
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


    protected Long getCountRow(String sql, Object... args) {
        BigInteger o = (BigInteger) setParameters(createSQLQuery(getTotalSql(sql)), args).uniqueResult();
        return o.longValue();
    }

    protected Long getCountRow(String sql, Map<String, Object> args) {
        BigInteger o = (BigInteger) createSQLQuery(getTotalSql(sql)).setProperties(args).uniqueResult();
        return o.longValue();
    }

    protected String getTotalSql(String sql) {
        String countSql = "select count(*) ";
        String upperSql = sql.toUpperCase();
        int start = upperSql.indexOf("FROM");
        int end = sql.length();
        countSql += sql.substring(start, end);
        logger.info(countSql);
        return countSql;
    }

    protected SQLQuery createSQLQuery(String sql) {
        return this.getSession().createSQLQuery(sql);
    }

    @Override
    public <X> X sqlUniqueValue(String sql, Object... args) {
        X x = (X) setParameters(createSQLQuery(sql), args).uniqueResult();
        return x;
    }

    @Override
    public <X> X sqlUniqueValue(String sql, Map<String, Object> args) {
        X x = (X) createSQLQuery(sql)
                .setProperties(args).uniqueResult();
        return x;
    }

    @Override
    public Object[] sqlUniqueObject(String sql, Object... args) {
        return sqlListObjects(sql, args).get(0);
    }

    @Override
    public Object[] sqlUniqueObject(String sql, Map<String, Object> args) {
        return sqlListObjects(sql, args).get(0);
    }

    @Override
    public <X> X sqlUniqueBean(String sql, Class<? extends X> transferClass, Object... args) {
        X x = (X) setParameters(createSQLQuery(sql), args)
                .setResultTransformer(Transformers.aliasToBean(transferClass))
                .uniqueResult();
        return x;
    }

    @Override
    public <X> X sqlUniqueBean(String sql, Class<? extends X> transferClass, Map<String, Object> args) {
        X x = (X) createSQLQuery(sql)
                .setProperties(args)
                .setResultTransformer(Transformers.aliasToBean(transferClass))
                .uniqueResult();
        return x;
    }

    @Override
    public List<Object[]> sqlListObjects(String sql, Object... args) {
        List<Object[]> list = setParameters(createSQLQuery(sql), args).list();
        return list;
    }

    @Override
    public List<Object[]> sqlListObjects(String sql, Map<String, Object> args) {
        List<Object[]> list = createSQLQuery(sql).setProperties(args).list();
        return list;
    }

    @Override
    public Page<Object[]> sqlPageObjects(String sql, PageParams pageParams, Object... args) {
        List list = setParameters(createSQLQuery(sql), args)
                .setFirstResult(pageParams.getStartRowByInt())
                .setMaxResults(pageParams.getPageSize())
                .list();
        Long totalCount = getCountRow(sql, args);
        Page<Object[]> page = new Page<Object[]>(list, totalCount, pageParams.getPageIndex(), pageParams.getPageSize());
        return page;
    }

    @Override
    public Page<Object[]> sqlPageObjects(String sql, PageParams pageParams, Map<String, Object> args) {
        List list = createSQLQuery(sql)
                .setProperties(args)
                .setFirstResult(pageParams.getStartRowByInt())
                .setMaxResults(pageParams.getPageSize())
                .list();
        Long totalCount = getCountRow(sql, args);
        Page<Object[]> page = new Page<Object[]>(list, totalCount, pageParams.getPageIndex(), pageParams.getPageSize());
        return page;
    }


    @Override
    public <X> List<X> sqlListBeans(String sql, Class<? extends X> transferClass, Object... args) {
        List<X> list = setParameters(createSQLQuery(sql), args)
                .setResultTransformer(Transformers.aliasToBean(transferClass))
                .list();
        return list;
    }


    @Override
    public <X> List<X> sqlListBeans(String sql, Class<? extends X> transferClass, Map<String, Object> args) {
        List<X> list = createSQLQuery(sql).setProperties(args)
                .setResultTransformer(Transformers.aliasToBean(transferClass))
                .list();
        return list;
    }

    @Override
    public <X> Page<X> sqlPageBeans(String sql, Class<? extends X> transferClass, PageParams pageParams, Object... args) {

        List<X> list = setParameters(createSQLQuery(sql), args)
                .setFirstResult(pageParams.getStartRowByInt())
                .setMaxResults(pageParams.getPageSize())
                .setResultTransformer(Transformers.aliasToBean(transferClass))
                .list();

        Long totalCount = getCountRow(sql, args);
        Page<X> page = new Page<X>(list, totalCount, pageParams.getPageIndex(), pageParams.getPageSize());
        return page;
    }

    @Override
    public <X> Page<X> sqlPageBeans(String sql, Class<? extends X> transferClass, PageParams pageParams, Map<String, Object> args) {
        List<X> list =
                createSQLQuery(sql)
                        .setResultTransformer(Transformers.aliasToBean(transferClass))
                        .setFirstResult(pageParams.getStartRowByInt())
                        .setMaxResults(pageParams.getPageSize())
                        .setProperties(args)
                        .list();
        Long totalCount = getCountRow(sql, args);
        Page<X> page = new Page<X>(list, totalCount, pageParams.getPageIndex(), pageParams.getPageSize());
        return page;
    }


    @Override
    public List<Map<String, Object>> sqlListMaps(String sql, Object... args) {
        List<Map<String, Object>> list =
                setParameters(createSQLQuery(sql), args)
                        .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                        .list();
        return list;
    }

    @Override
    public List<Map<String, Object>> sqlListMaps(String sql, Map<String, Object> args) {

        List<Map<String, Object>> list =
                createSQLQuery(sql)
                        .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                        .setProperties(args)
                        .list();
        return list;

    }

    @Override
    public List<Map<String, Object>> sqlListMaps(String sql, String[] fields, Object... args) {
        List<Object[]> list =
                setParameters(createSQLQuery(sql), args)
                        .list();
        List<Map<String, Object>> maps = QueryUtil.arraysToMaps(
                list, fields
        );
        return maps;
    }

    @Override
    public List<Map<String, Object>> sqlListMaps(String sql, String[] fields, Map<String, Object> args) {
        List<Object[]> list =
                createSQLQuery(sql)
                        .setProperties(args)
                        .list();
        List<Map<String, Object>> maps = QueryUtil.arraysToMaps(
                list, fields
        );
        return maps;
    }

    @Override
    public Page<Map<String, Object>> sqlPageMaps(String sql, PageParams pageParams, Object... args) {

        List<Map<String, Object>> list =
                setParameters(createSQLQuery(sql), args)
                        .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                        .setFirstResult(pageParams.getStartRowByInt())
                        .setMaxResults(pageParams.getPageSize())
                        .list();
        Long totalCount = getCountRow(sql, args);
        Page<Map<String, Object>> page = new Page<Map<String, Object>>(list, totalCount, pageParams.getPageIndex(), pageParams.getPageSize());
        return page;
    }

    @Override
    public Page<Map<String, Object>> sqlPageMaps(String sql, PageParams pageParams, Map<String, Object> args) {

        List<Map<String, Object>> list =
                createSQLQuery(sql)
                        .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                        .setFirstResult(pageParams.getStartRowByInt())
                        .setMaxResults(pageParams.getPageSize())
                        .setProperties(args)
                        .list();
        Long totalCount = getCountRow(sql, args);
        Page<Map<String, Object>> page = new Page<Map<String, Object>>(list, totalCount, pageParams.getPageIndex(), pageParams.getPageSize());
        return page;
    }

    @Override
    public Page<Map<String, Object>> sqlPageMaps(String sql, PageParams pageParams, String[] fields, Object... args) {
        List<Object[]> list =
                setParameters(createSQLQuery(sql), args)
                        .setFirstResult(pageParams.getStartRowByInt())
                        .setMaxResults(pageParams.getPageSize())
                        .list();
        Long totalCount = getCountRow(sql, args);
        List<Map<String, Object>> maps = QueryUtil.arraysToMaps(list, fields);
        Page<Map<String, Object>> page = new Page<Map<String, Object>>(maps, totalCount, pageParams.getPageIndex(), pageParams.getPageSize());
        return page;
    }

    @Override
    public Page<Map<String, Object>> sqlPageMaps(String sql, PageParams pageParams, String[] fields, Map<String, Object> args) {
        List<Object[]> list =
                createSQLQuery(sql)
                        .setFirstResult(pageParams.getStartRowByInt())
                        .setMaxResults(pageParams.getPageSize())
                        .setProperties(args)
                        .list();
        Long totalCount = getCountRow(sql, args);
        List<Map<String, Object>> maps = QueryUtil.arraysToMaps(list, fields);
        Page<Map<String, Object>> page = new Page<Map<String, Object>>(maps, totalCount, pageParams.getPageIndex(), pageParams.getPageSize());
        return page;
    }




}
