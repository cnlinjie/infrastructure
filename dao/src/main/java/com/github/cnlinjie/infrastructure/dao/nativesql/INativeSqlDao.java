package com.github.cnlinjie.infrastructure.dao.nativesql;

import com.github.cnlinjie.infrastructure.dao.PageParams;
import com.github.cnlinjie.infrastructure.dao.Page;

import java.util.List;
import java.util.Map;

/**
 * @author Linjie
 * @date 2015/9/29.
 */
public interface INativeSqlDao {


    /**
     * 查询单值，不过貌似也可以用来查询单个Bean， 我去~~
     *
     * @param sql  查询的SQL语句
     * @param args SQL参数，没有可不传
     * @param <X>
     * @return
     */
    public <X> X sqlUniqueValue(String sql, Object... args);

    /**
     * 不要带封号，会识别不出来
     *
     * @param sql
     * @param args
     * @param <X>
     * @return
     */
    public <X> X sqlUniqueValue(String sql, Map<String, Object> args);

    /**
     * 查询单条记录，返回一个数组
     *
     * @param sql  查询SQL语句
     * @param args 参数，如果没有，可不传
     * @return
     */
    public Object[] sqlUniqueObject(String sql, Object... args);

    /**
     * 查询单条记录，返回一个数组
     *
     * @param sql  查询SQL语句
     * @param args map 参数，如果没有，可不传
     * @return
     */
    public Object[] sqlUniqueObject(String sql, Map<String, Object> args);

    /**
     * 查询单条记录，根据字段值转换为一个 Bean对象
     *
     * @param sql           SQL语句
     * @param transferClass 需要转换的Bean对象
     * @param args          参数，如果没有，可不传
     * @param <X>
     * @return
     */
    public <X> X sqlUniqueBean(String sql, Class<? extends X> transferClass, Object... args);

    /**
     * 查询单条记录，根据字段值转换为一个 Bean对象
     *
     * @param sql           SQL语句
     * @param transferClass 需要转换的Bean对象
     * @param args          Map 参数，如果没有，可不传
     * @param <X>
     * @return
     */
    public <X> X sqlUniqueBean(String sql, Class<? extends X> transferClass, Map<String, Object> args);


    /**
     * 查询多条记录，每条记录以Object[]方式存储
     *
     * @param sql  查询的SQL语句
     * @param args 参数，如果没有，可不传
     * @return
     */
    public List<Object[]> sqlListObjects(String sql, Object... args);




    /**
     * 查询多条记录，每条记录以Object[]方式存储
     *
     * @param sql  查询的SQL语句
     * @param args Map 参数，如果没有，可不传
     * @return
     */
    public List<Object[]> sqlListObjects(String sql, Map<String, Object> args);

    /**
     * 查询多条记录
     *
     * @param sql  查询的SQL语句
     * @param args 参数，如果没有，可不传
     * @return
     */
    public <X> List<X> sqlListX(String sql, Object... args);



    /**
     * 查询多条记录
     *
     * @param sql  查询的SQL语句
     * @param args 参数，如果没有，可不传
     * @return
     */
    public <X> List<X> sqlListX(String sql, Map<String, Object> args);



    /**
     * 分页查询多条记录，每条记录以Object[]方式存储
     *
     * @param sql        SQL 语句
     * @param pageParams 分页参数
     * @param args       SQL参数,如果没有，可不传
     * @return
     */
    public Page<Object[]> sqlPageObjects(String sql, PageParams pageParams, Object... args);


    /**
     * 分页查询多条记录，每条记录以Object[]方式存储
     *
     * @param sql        SQL 语句
     * @param pageParams 分页参数
     * @param args       SQL参数,如果没有，可不传
     * @return
     */
    public Page<Object[]> sqlPageObjects(String sql, PageParams pageParams, Map<String, Object> args);


    /**
     * 查询多条记录，每条记录转为Bean存储，查询出来的字段名称要与 Bean 中的字段一致,否则会爆没有 没有 PropertyNotFoundException 错误
     *
     * @param sql           查询的SQl语句
     * @param transferClass 需要转换的Bean对象
     * @param args          SQl参数，没有可不传
     * @param <X>
     * @return
     */
    public <X> List<X> sqlListBeans(String sql, Class<? extends X> transferClass, Object... args);

    /**
     * 查询多条记录，每条记录转为Bean存储，查询出来的字段名称要与 Bean 中的字段一致,否则会爆没有 没有 PropertyNotFoundException 错误
     *
     * @param sql           查询的SQl语句
     * @param transferClass 需要转换的Bean对象
     * @param args          SQl参数，没有可不传
     * @param <X>
     * @return
     */
    public <X> List<X> sqlListBeans(String sql, Class<? extends X> transferClass, Map<String, Object> args);

    /**
     * 分页查询多条记录，每条记录转为Bean存储，查询出来的字段名称要与 Bean 中的字段一致,否则会爆没有 没有 PropertyNotFoundException 错误
     *
     * @param sql           查询的SQl语句
     * @param transferClass 需要转换的Bean对象
     * @param pageParams    分页参数
     * @param args          SQl参数，没有可不传
     * @param <X>
     * @return
     */
    public <X> Page<X> sqlPageBeans(String sql, Class<? extends X> transferClass, PageParams pageParams, Object... args);

    /**
     * 分页查询多条记录，每条记录转为Bean存储，查询出来的字段名称要与 Bean 中的字段一致,否则会爆没有 没有 PropertyNotFoundException 错误
     *
     * @param sql           查询的SQl语句
     * @param transferClass 需要转换的Bean对象
     * @param pageParams    分页参数
     * @param args          SQl参数，没有可不传
     * @param <X>
     * @return
     */
    public <X> Page<X> sqlPageBeans(String sql, Class<? extends X> transferClass, PageParams pageParams, Map<String, Object> args);


    /**
     * 查询多条记录，每天记录以Map的方式存储，需要注意的是，如果使用此方法，需要以 as ,as 来重命名字段,如 <br/>
     * SELECT  m.`phone` as phone2 ,m.password as password2 FROM `member` m where m.password = :password <br/>
     * 此时 phone2 和password2 将是Map的Key值，如果不想用这种方式，可以采用以下：sqlListMaps(String sql,String[] fields, Object... args); 方法<br/>
     *
     * @param sql  查询的SQl语句
     * @param args SQL参数，没有可不传
     * @return
     */
    public List<Map<String, Object>> sqlListMaps(String sql, Object... args);

    /**
     * 查询多条记录，每天记录以Map的方式存储，需要注意的是，如果使用此方法，需要以 as ,as 来重命名字段,如 <br/>
     * SELECT  m.`phone` as phone2 ,m.password as password2 FROM `member` m where m.password = :password <br/>
     * 此时 phone2 和password2 将是Map的Key值，如果不想用这种方式，可以采用以下：sqlListMaps(String sql,String[] fields, Object... args); 方法<br/>
     *
     * @param sql  查询的SQl语句
     * @param args SQL参数，没有可不传
     * @return
     */
    public List<Map<String, Object>> sqlListMaps(String sql, Map<String, Object> args);

    /**
     * 查询多条记录，每天记录以Map的方式存储 , 需要注意的是，查询的字段数量需要和 fields 参数数组的数量一致，顺序也一致
     * <br/> 如果采用了 此方法，在SQL语句可不用 as 来重命名
     *
     * @param sql    查询的SQL
     * @param fields 用来重命名字段的，此时SQL语句可以不用 as ，不管SQL里面是什么，最终返回的Map里面的Key，都以此为主
     * @param args   SQL参数，没有可不传
     * @return
     */
    public List<Map<String, Object>> sqlListMaps(String sql, String[] fields, Object... args);

    /**
     * 查询多条记录，每天记录以Map的方式存储 , 需要注意的是，查询的字段数量需要和 fields 参数数组的数量一致，顺序也一致
     * <br/> 如果采用了 此方法，在SQL语句可不用 as 来重命名
     *
     * @param sql    查询的SQL
     * @param fields 用来重命名字段的，此时SQL语句可以不用 as ，不管SQL里面是什么，最终返回的Map里面的Key，都以此为主
     * @param args   SQL参数，没有可不传
     * @return
     */
    public List<Map<String, Object>> sqlListMaps(String sql, String[] fields, Map<String, Object> args);


    /**
     * 分页查询多条记录，每天记录以Map的方式存储，需要注意的是，如果使用此方法，需要以 as ,as 来重命名字段,如 <br/>
     * SELECT  m.`phone` as phone2 ,m.password as password2 FROM `member` m where m.password = :password <br/>
     * 此时 phone2 和password2 将是Map的Key值
     *
     * @param sql        查询的SQl语句
     * @param pageParams 分页参数
     * @param args       SQL参数，没有可不传
     * @return
     */
    public Page<Map<String, Object>> sqlPageMaps(String sql, PageParams pageParams, Object... args);

    /**
     * 分页查询多条记录，每天记录以Map的方式存储，需要注意的是，如果使用此方法，需要以 as ,as 来重命名字段,如 <br/>
     * SELECT  m.`phone` as phone2 ,m.password as password2 FROM `member` m where m.password = :password <br/>
     * 此时 phone2 和password2 将是Map的Key值
     *
     * @param sql        查询的SQl语句
     * @param pageParams 分页参数
     * @param args       SQL参数，没有可不传
     * @return
     */
    public Page<Map<String, Object>> sqlPageMaps(String sql, PageParams pageParams, Map<String, Object> args);

    /**
     * 分页查询多条记录，每天记录以Map的方式存储 , 需要注意的是，查询的字段数量需要和 fields 参数数组的数量一致，顺序也一致
     * <br/> 如果采用了 此方法，在SQL语句可不用 as 来重命名
     *
     * @param sql        查询的SQL
     * @param pageParams 分页参数
     * @param fields     用来重命名字段的，此时SQL语句可以不用 as ，不管SQL里面是什么，最终返回的Map里面的Key，都以此为主
     * @param args       SQL参数，没有可不传
     * @return
     */
    public Page<Map<String, Object>> sqlPageMaps(String sql, PageParams pageParams, String[] fields, Object... args);

    /**
     * 分页查询多条记录，每天记录以Map的方式存储 , 需要注意的是，查询的字段数量需要和 fields 参数数组的数量一致，顺序也一致
     * <br/> 如果采用了 此方法，在SQL语句可不用 as 来重命名
     *
     * @param sql        查询的SQL
     * @param pageParams 分页参数
     * @param fields     用来重命名字段的，此时SQL语句可以不用 as ，不管SQL里面是什么，最终返回的Map里面的Key，都以此为主
     * @param args       SQL参数，没有可不传
     * @return
     */
    public Page<Map<String, Object>> sqlPageMaps(String sql, PageParams pageParams, String[] fields, Map<String, Object> args);

    /**
     * 执行SQL语句，返回影响行数
     *
     * @param sql  SQL语句
     * @param args SQl参数，没有可不传
     * @return
     */
    public int sqlExecute(String sql, Object... args);

    /**
     * 执行SQL语句，返回影响行数
     *
     * @param sql  SQL语句
     * @param args SQl参数，没有可不传
     * @return
     */
    public int sqlExecute(String sql, Map<String, Object> args);


}

