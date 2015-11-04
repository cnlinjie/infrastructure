package com.github.cnlinjie.infrastructure.dao.hibernate;

import com.github.cnlinjie.infrastructure.dao.PageParams;
import com.github.cnlinjie.infrastructure.dao.Page;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Linjie
 * Hibernate  Criteria 对象查询帮助类
 * @date 2015/9/29.
 */
public interface IHibernateCriteriaDao<T, PK extends Serializable> {


    /**
     * 查询单个实体
     * @param criterion 查询条件
     * @return
     */
    public T unique(Criterion criterion);


    /**
     * 查询单个实体
     * @param criteriaParams 查询条件
     * @return
     */
    public T unique(CriteriaParams criteriaParams);

    /**
     * 查询列表
     * @param criterion 查询条件
     * @return
     */
    public List<T> list(Criterion criterion);

    /**
     * 查询列表
     * @param criterion 条件
     * @param order 排序
     * @return
     */
    public List<T> list(Criterion criterion,Order order);


    /**
     * @param params 查询参数，因为查询的是实体，所以请不要设置：Projection 这个参数
     * @return
     */
    public List<T> list(CriteriaParams params);

    /**
     * 查询分页 实体
     * @param criterion 查询条件
     * @param pageParams 分页条件
     * @return
     */

    /**
     * 分页查询实体
     * @param criterion 条件
     * @param pageParams 分页参数
     * @return
     */
    public Page<T> page(Criterion criterion,PageParams pageParams);

    /**
     * 分页查询实体
     * @param criterion 条件
     * @param pageParams 分页参数
     * @param order 排序参数
     * @return
     */
    public Page<T> page(Criterion criterion,PageParams pageParams,Order order);

    /**
     * 分页查询实体
     * @param params   查询参数，因为查询的是实体，所以请不要设置：Projection 这个参数
     * @param pageParams 分页参数
     * @return
     */
    public Page<T> page(CriteriaParams params,PageParams pageParams);

    /**
     * 分页查询实体
     * @param pageParams 分页参数
     * @return
     */
    public Page<T> page(PageParams pageParams);

    /**
     * 查询单个值
     * @param criterion  条件
     * @param projection 需要查询的字段
     * @return
     */
    public Object uniqueValue(Criterion criterion,Projection projection);

    /**
     * 查询单条记录，多个值
     * @param criterion
     * @param projection
     */
    public Object[] uniqueObject(Criterion criterion,Projection projection);

    /**
     * 查询多条记录
     * @param criterion 条件
     * @param projection 需要查询的字段
     * @return
     */
    public List<Object[]> listObjects(Criterion criterion,Projection projection);
    public List<Object[]> listObjects(Criterion criterion,Projection projection,Order order);
    public List<Object[]> listObjects(CriteriaParams params);


    /**
     * 分页查询多条记录 
     * @param criterion  查询条件
     * @param projection 需要查询的字段
     * @param pageParams 分页参数
     * @return
     */
    public Page<Object[]> pageObjects(Criterion criterion,Projection projection,PageParams pageParams);
    public Page<Object[]> pageObjects(Criterion criterion,Projection projection,Order order,PageParams pageParams);
    public Page<Object[]> pageObjects(CriteriaParams params,PageParams pageParams);


    /**
     * 查询单条记录，以Map的形式返回，比较推荐用： listMaps(CriteriaParams params)
     * @param criterion   查询条件
     * @param projection 需要查询的字段 , 如果使用这个方法查询，可能需要参照如下例子，我比较推荐用：
     *                   uniqueMap(CriteriaParams params)
     * <br/>
     * ProjectionList projectionList = Projections.projectionList();          <br/>
     * projectionList.add(Projections.property("password"),"password2");      <br/>
     * projectionList.add(Projections.property("phone"),"phone2");            <br/>
     * Map<String, Object> map = dao.uniqueMap(Restrictions.eq("pkId", 1), projectionList);
     * @return
     */
    public Map<String,Object> uniqueMap(Criterion criterion,Projection projection);

    /**
     * 查询单条记录，以Map的形式返回，比较推荐用： listMaps(CriteriaParams params)
     * @param params 需要查询的字段 ，看如下示例：
     * <br/>
     *  Map<String, Object> map2 = dao.uniqueMap (
     *  <br/>
     *  CriteriaParams
     *    .Add(Restrictions.eq("pkId", 1))
     *   <br/>
     *    .addProjection(Projections.property("password"), "password2")
     *   <br/>
     *    .addProjection(Projections.property("phone"), "phone2")
     *    <br/>
     * );
     * @return
     */
    public Map<String,Object> uniqueMap(CriteriaParams params);

    /**
     * 查询列表，以List<Map>>的形式返回
     *
     * @param criterion  查询条件
     * @param projection 需要查询的字段 , 如果使用这个方法查询，可能需要参照如下例子，我比较推荐用：
     *                   listMaps(CriteriaParams params)
     * <br/>
     * 如果需要查询使用查询多个字段，可以如下示例：
     * ProjectionList projectionList = Projections.projectionList();          <br/>
     * projectionList.add(Projections.property("password"),"password2");      <br/>
     * projectionList.add(Projections.property("phone"),"phone2");            <br/>
     * listMaps(Restrictions.eq("password", "123123"),projectionList);
     * @return
     */
    public List<Map<String,Object>> listMaps(Criterion criterion,Projection projection);


    /**
     * 查询列表，以List<Map>>的形式返回
     * @param criterion 查询条件
     * @param projection 查询字段
     * @param order  排序
     *
     * 如果需要查询使用查询多个字段，可以如下示例：
     * ProjectionList projectionList = Projections.projectionList();          <br/>
     * projectionList.add(Projections.property("password"),"password2");      <br/>
     * projectionList.add(Projections.property("phone"),"phone2");            <br/>
     * listMaps(Restrictions.eq("password", "123123"),projectionList,Order.desc('pkId'),);
     * @return
     */
    public List<Map<String,Object>> listMaps(Criterion criterion,Projection projection,Order order);

    /**
     * 列表查询，以Map返回
     * @param params  查询条件和查询字段，参考如下：                    <br/>
     *
     *  List<Map<String, Object>> maps = dao.listMaps(CriteriaParams <br/>
     * .Add(Restrictions.eq("password", "123123"))                   <br/>
     * .addProjection(Projections.property("password"), "password")  <br/>
     * .addProjection(Projections.property("phone"), "phone")        <br/>
     * );
     * @return
     */
    public List<Map<String,Object>> listMaps(CriteriaParams params);

    /**
     * 查询分页，以Map的形式
     * @param criterion  查询条件
     * @param projection 需要查询的字段
     * @param pageParams 分页条件
     *
     * 如果需要查询使用查询多个字段，可以如下示例：
     * ProjectionList projectionList = Projections.projectionList();          <br/>
     * projectionList.add(Projections.property("password"),"password2");      <br/>
     * projectionList.add(Projections.property("phone"),"phone2");            <br/>
     * pageMaps(Restrictions.eq("password", "123123"),projectionList,PageParams.page(1,15));
     *
     * @return
     */
    public Page<Map<String,Object>> pageMaps(Criterion criterion,Projection projection,PageParams pageParams);


    /**
     *　查询分页，以Map的形式
     * @param criterion　查询条件
     * @param projection　需要查询的字段
     * @param order　　 　　排序
     * @param pageParams　分页条件
     *
     * 如果需要查询使用查询多个字段，可以如下示例：
     * ProjectionList projectionList = Projections.projectionList();          <br/>
     * projectionList.add(Projections.property("password"),"password2");      <br/>
     * projectionList.add(Projections.property("phone"),"phone2");            <br/>
     * pageMaps(Restrictions.eq("password", "123123"),projectionList,Order.desc('pkId'),PageParams.page(1,15));
     *
     * @return
     */
    public Page<Map<String,Object>> pageMaps(Criterion criterion,Projection projection,Order order,PageParams pageParams);

    /**
     * 查询分页，以Map的形式
     * @param params
     * @param pageParams 查询条件，排序条件，查询字段
     *  参考如下:                                                     <br/>
     * Page<Map<String, Object>> maps = dao.pageMaps(CriteriaParams  <br/>
     * .Add(Restrictions.eq("password", "123123"))                   <br/>
     * .addProjection(Projections.property("password"), "password")  <br/>
     * .addProjection(Projections.property("phone"), "phone")        <br/>
     * , PageParams.page(1, 10)                                      <br/>
     * );
     * @return
     */
    public Page<Map<String,Object>> pageMaps(CriteriaParams params,PageParams pageParams);



}
