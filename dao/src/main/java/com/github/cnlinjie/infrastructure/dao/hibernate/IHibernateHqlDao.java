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


    /**
     * 查询单个字段值，如果你要使用这个查询一个对象，我也木有办法，里面实现使用的是：uniqueResult()
     * @param hql   hql 语句
     * @param args 参数，如果没有时可不传
     * @param <X>
     * @return 返回指定对象
     */
    public <X> X uniqueValue(String hql, Object... args);

    /**
     * 查询单个字段值，如果你要使用这个查询一个对象，我也木有办法，里面实现使用的是：uniqueResult()
     * @param hql   hql 语句
     * @param args 参数，如果没有时可不传
     * @param <X>
     * @return 返回指定对象
     */
    public <X> X uniqueValue(String hql, Map<String, Object> args);

    /**
     * 查询一条记录，但多个字段，如 member 表里面，我只想要 username,password ，如下HQL<br/>
     * select m.username, m.password from member where pkId= ?
     * @param hql hql
     * @param args 参数 ，如上面的HQL，此时可以传 对应的参数值，如果没有时可不传
     * @return
     */
    public Object[] uniqueObject(String hql, Object... args);

    /**
     * 查询一条记录，但多个字段，如 member 表里面，我只想要 username,password ，如下HQL<br/>
     * select m.username, m.password from member where pkId= :pkId
     * @param hql hql
     * @param args Map 参数 ，如上面的HQL，此时可以传 对应的Map结构为{pkId:1}，如果没有时可不传
     * @return
     */

    public Object[] uniqueObject(String hql, Map<String, Object> args);

    /**
     * 查询多条记录，一个记录包含多个字段，如member 我只想要 username,password ，如下HQL<br/>
     * select m.username, m.password from member where delStatus = ?
     * @param hql hql语句
     * @param args  参数 ，如上面的HQL，此时可以传 对应的参数值，如果没有时可不传
     * @return
     */
    public List<Object[]> listObjects(String hql, Object... args);

    /**
     * 查询多条记录，一个记录包含多个字段，如member 我只想要 username,password ，如下HQL<br/>
     * select m.username, m.password from member where delStatus = :delStatus
     * @param hql hql语句
     * @param args  Map 参数 ，如上面的HQL，此时可以传 对应的Map结构为{delStatus:0}，如果没有时可不传
     * @return
     */
    public List<Object[]> listObjects(String hql, Map<String, Object> args);


    /**
     * 分页查询记录，一个记录包含多个字段，如member 我只想要 username,password ，如下HQL<br/>
     *  select m.username, m.password from member where delStatus = ？
     * @param hql  hql 语句
     * @param pageParams  分页参数，可直接使用静态方法： PageParams.page(1,10) ，表明第1页，每页10条
     * @param args M 参数 ，如上面的HQL，此时可以传对应值 ，如果没有时可不传
     * @return
     */
    public Page<Object[]> pageObjects(String hql, PageParams pageParams, Object... args);

    /**
     * 分页查询记录，一个记录包含多个字段，如member 我只想要 username,password ，如下HQL<br/>
     *  select m.username, m.password from member where delStatus = :delStatus
     * @param hql  hql 语句
     * @param pageParams  分页参数，可直接使用静态方法： PageParams.page(1,10) ，表明第1页，每页10条
     * @param args Map 参数 ，如上面的HQL，此时可以传 对应的Map结构为{delStatus:0} ，如果没有时可不传
     * @return
     */
    public Page<Object[]> pageObjects(String hql, PageParams pageParams, Map<String, Object> args);

    /**
     * 查询的结果集合为Map, 写HQL语句时，需要使用 as 来重命名，否则会以数组序号作为key值，HQL可以如下：
     * <br/>
     *  select phone as phone2,password  as password2 from Member where phone = :phone
     * @param hql hql 语句 , 记得因为是map，所以请使用 as , as 来重命名字段名
     * @param args HQL 参数，如果没有时可不传
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
     * @param args HQL 参数，如果没有时可不传
     * @return
     */
    public List<Map<String, Object>> listMaps(String hql, Object... args);

    /**
     * 集合查询，查询的结果集合为Map, 写HQL语句时，需要使用 as 来重命名，否则会以数组序号作为key值，HQL可以如下：
     * <br/>
     *  select phone as phone2,password  as password2 from Member where phone = :phone
     * @param hql hql 语句 , 记得因为是map，所以请使用 as , as 来重命名字段名
     * @param args HQL 参数 ,key 为字段名， value 为值，如果没有时可不传
     * @return
     */
    public List<Map<String, Object>> listMaps(String hql, Map<String, Object> args);


    /**
     * 分页查询，查询的结果集合为Map, 写HQL语句时，需要使用 as 来重命名，否则会以数组序号作为key值，HQL可以如下：
     * <br/>
     * select phone as phone2,password  as password2 from Member where phone = :phone
     * @param hql hql 语句 , 记得因为是map，所以请使用 as , as 来重命名字段名
     * @param pageParams 分页参数
     * @param args  HQL 参数，如果没有时可不传
     * @return
     */
    public Page<Map<String, Object>> pageMaps(String hql, PageParams pageParams, Object... args);

    /**
     * 分页查询，查询的结果集合为Map, 写HQL语句时，需要使用 as 来重命名，否则会以数组序号作为key值，HQL可以如下：
     * <br/>
     * select phone as phone2,password  as password2 from Member where phone = :phone
     * @param hql hql 语句 , 记得因为是map，所以请使用 as , as 来重命名字段名
     * @param pageParams 分页参数
     * @param args  HQL 参数，如果没有时可不传
     * @return
     */
    public Page<Map<String, Object>> pageMaps(String hql, PageParams pageParams,  Map<String, Object> args);


    /**
     * 因为HQL语句在被Hibernate转换为SQL时，框架会自动 as 别名，所以
     * HQL的 Bean 查询需要 as , as ，如<br/>
     * select phone as phone2,password as password2 from Member where phone = :phone <br/>
     * 而参数 transferClass 对应的类，需要有对应的字段才能映射
     * @param hql   HQL语句 ，所需查询的需要　as 重命名
     * @param transferClass  需要转换的类，字段名对应HQL中所查询的(as )之后的名称
     * @param args  参数，如果没有时可不传
     * @param <X>  返回值对应的类
     * @return 返回对应的Bean
     */
    public <X> X uniqueBean(String hql,Class<? extends X> transferClass,Object... args);

    /**
     * 因为HQL语句在被Hibernate转换为SQL时，框架会自动 as 别名，所以
     * HQL的 Bean 查询需要 as , as ，如<br/>
     * select phone as phone2,password as password2 from Member where phone = :phone <br/>
     * 而参数 transferClass 对应的类，需要有对应的字段才能映射
     * @param hql   HQL语句 ，所需查询的需要　as 重命名
     * @param transferClass  需要转换的类，字段名对应HQL中所查询的(as )之后的名称
     * @param args  Map参数，如果没有时可不传
     * @param <X>  返回值对应的类
     * @return 返回对应的Bean
     */
    public <X> X uniqueBean(String hql,Class<? extends X> transferClass,Map<String,Object> args);

    /**
     * 因为HQL语句在被Hibernate转换为SQL时，框架会自动 as 别名，所以
     * HQL的 Bean 查询需要 as , as ，如<br/>
     * select phone as phone2,password as password2 from Member where phone = :phone <br/>
     * 而参数 transferClass 对应的类，需要有对应的字段才能映射
     * @param hql   HQL语句 ，所需查询的需要　as 重命名
     * @param transferClass  需要转换的类，字段名对应HQL中所查询的(as )之后的名称
     * @param args  参数，如果没有时可不传
     * @param <X>  返回值对应的类
     * @return 返回对应的Bean , List
     */
    public <X> List<X>  listBeans(String hql,Class<? extends X> transferClass,Object... args);

    /**
     * 因为HQL语句在被Hibernate转换为SQL时，框架会自动 as 别名，所以
     * HQL的 Bean 查询需要 as , as ，如<br/>
     * select phone as phone2,password as password2 from Member where phone = :phone <br/>
     * 而参数 transferClass 对应的类，需要有对应的字段才能映射
     * @param hql   HQL语句 ，所需查询的需要　as 重命名
     * @param transferClass  需要转换的类，字段名对应HQL中所查询的(as )之后的名称
     * @param args  Map参数，如果没有时可不传
     * @param <X>  返回值对应的类
     * @return 返回对应的Bean , List
     */
    public <X> List<X>  listBeans(String hql,Class<? extends X> transferClass,Map<String,Object> args);


    /**
     * 因为HQL语句在被Hibernate转换为SQL时，框架会自动 as 别名，所以
     * HQL的 Bean 查询需要 as , as ，如<br/>
     * select phone as phone2,password as password2 from Member where phone = :phone <br/>
     * 而参数 transferClass 对应的类，需要有对应的字段才能映射
     * @param hql   HQL语句 ，所需查询的需要　as 重命名
     * @param transferClass  需要转换的类，字段名对应HQL中所查询的(as )之后的名称
     * @param pageParams 分页参数
     * @param args  参数，如果没有时可不传
     * @param <X>  返回值对应的类
     * @return 返回对应的Bean , Page
     */
    public <X> Page<X>  pageBeans(String hql,Class<? extends X> transferClass,PageParams pageParams,Object... args);

    /**
     * 因为HQL语句在被Hibernate转换为SQL时，框架会自动 as 别名，所以
     * HQL的 Bean 查询需要 as , as ，如<br/>
     * select phone as phone2,password as password2 from Member where phone = :phone <br/>
     * 而参数 transferClass 对应的类，需要有对应的字段才能映射
     * @param hql   HQL语句 ，所需查询的需要　as 重命名
     * @param transferClass  需要转换的类，字段名对应HQL中所查询的(as )之后的名称
     * @param pageParams 分页参数
     * @param args  Map参数，如果没有时可不传
     * @param <X>  返回值对应的类
     * @return 返回对应的Bean , Page
     */
    public <X> Page<X>  pageBeans(String hql,Class<? extends X> transferClass,PageParams pageParams,Map<String,Object> args);



}
