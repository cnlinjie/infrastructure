package com.github.cnlinjie.infrastructure.dao.hibernate;

import com.github.cnlinjie.infrastructure.dao.Page;
import com.github.cnlinjie.infrastructure.dao.PageParams;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Linjie Hibernate HQL 帮助类
 * @date 2015/9/29.
 */
public interface IHibernateHqlDao<T, PK extends Serializable> {


    public T unique(String hql, Object... args);

    public T unique(String hql, Map<String, Object> args);

    public List<T> list(String hql, Object... args);

    public List<T> list(String hql, Map<String, Object> args);

    public Page<T> page(String hql, PageParams pageParams, Object... args);

    public Page<T> page(String hql, PageParams pageParams, Map<String, Object> args);


    public <X> X uniqueValue(String hql, Object... args);

    public <X> X uniqueValue(String hql, Map<String, Object> args);

    public Object[] uniqueObject(String hql, Object... args);

    public Object[] uniqueObject(String hql, Map<String, Object> args);



    public List<Object[]> listObjects(String hql, Object... args);

    public List<Object[]> listObjects(String hql, Map<String, Object> args);



    public Page<Object[]> pageObjects(String hql, PageParams pageParams, Object... args);

    public Page<Object[]> pageObjects(String hql, PageParams pageParams, Map<String, Object> args);

    /**
     * 查询的结果集合为Map, 写HQL语句时，需要使用 as 来重命名，否则会以数组序号作为key值，HQL可以如下：
     * <br/>
     *  select phone as phone2,password  as password2 from Member where phone = :phone
     * @param hql hql 语句 , 记得因为是map，所以请使用 as , as 来重命名字段名
     * @param args HQL 参数
     * @return
     */
    public Map<String, Object> uniqueMap(String hql, Object... args);

    /**
     * 查询的结果集合为Map, 写HQL语句时，需要使用 as 来重命名，否则会以数组序号作为key值，HQL可以如下：
     * <br/>
     *  select phone as phone2,password  as password2 from Member where phone = :phone
     * @param hql hql 语句 , 记得因为是map，所以请使用 as , as 来重命名字段名
     * @param args HQL 参数 ,key 为字段名， value 为值
     * @return
     */
    public Map<String, Object> uniqueMap(String hql, Map<String, Object> args);

    /**
     * 集合查询，查询的结果集合为Map, 写HQL语句时，需要使用 as 来重命名，否则会以数组序号作为key值，HQL可以如下：
     * <br/>
     *  select phone as phone2,password  as password2 from Member where phone = :phone
     * @param hql hql 语句 , 记得因为是map，所以请使用 as , as 来重命名字段名
     * @param args HQL 参数
     * @return
     */
    public List<Map<String, Object>> listMaps(String hql, Object... args);

    /**
     * 集合查询，查询的结果集合为Map, 写HQL语句时，需要使用 as 来重命名，否则会以数组序号作为key值，HQL可以如下：
     * <br/>
     *  select phone as phone2,password  as password2 from Member where phone = :phone
     * @param hql hql 语句 , 记得因为是map，所以请使用 as , as 来重命名字段名
     * @param args HQL 参数 ,key 为字段名， value 为值
     * @return
     */
    public List<Map<String, Object>> listMaps(String hql, Map<String, Object> args);


    /**
     * 分页查询，查询的结果集合为Map, 写HQL语句时，需要使用 as 来重命名，否则会以数组序号作为key值，HQL可以如下：
     * <br/>
     * select phone as phone2,password  as password2 from Member where phone = :phone
     * @param hql hql 语句 , 记得因为是map，所以请使用 as , as 来重命名字段名
     * @param pageParams 分页参数
     * @param args  HQL 参数
     * @return
     */
    public Page<Map<String, Object>> pageMaps(String hql, PageParams pageParams, Object... args);

    /**
     * 分页查询，查询的结果集合为Map, 写HQL语句时，需要使用 as 来重命名，否则会以数组序号作为key值，HQL可以如下：
     * <br/>
     * select phone as phone2,password  as password2 from Member where phone = :phone
     * @param hql hql 语句 , 记得因为是map，所以请使用 as , as 来重命名字段名
     * @param pageParams 分页参数
     * @param args  HQL 参数
     * @return
     */
    public Page<Map<String, Object>> pageMaps(String hql, PageParams pageParams,  Map<String, Object> args);


    /**
     * HQL的 Bean 查询需要 as , as ，如<br/>
     * select phone as phone,password as password from Member where phone = :phone
     * @param hql
     * @param transferClass
     * @param args
     * @param <X>
     * @return
     */
    public <X> X uniqueBean(String hql,Class<? extends X> transferClass,Object... args);
    public <X> X uniqueBean(String hql,Class<? extends X> transferClass,Map<String,Object> args);

    public <X> List<X>  listBeans(String hql,Class<? extends X> transferClass,Object... args);
    public <X> List<X>  listBeans(String hql,Class<? extends X> transferClass,Map<String,Object> args);

    public <X> Page<X>  pageBeans(String hql,Class<? extends X> transferClass,PageParams pageParams,Object... args);
    public <X> Page<X>  pageBeans(String hql,Class<? extends X> transferClass,PageParams pageParams,Map<String,Object> args);



}
