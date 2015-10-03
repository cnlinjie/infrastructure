package com.github.cnlinjie.infrastructure.dao.infrastructure.dao.nativesql;

import com.github.cnlinjie.infrastructure.dao.infrastructure.dao.Page;
import com.github.cnlinjie.infrastructure.dao.infrastructure.dao.PageParams;

import java.util.List;
import java.util.Map;

/**
 * @author Linjie
 * @date 2015/9/29.
 */
public interface INativeSqlDao {


    public <X> X  sqlUniqueValue(String sql,Object... args);

    /**
     * 不要带封号，会识别不出来
     * @param sql
     * @param args
     * @param <X>
     * @return
     */
    public <X> X sqlUniqueValue(String sql,Map<String,Object> args);

    public Object[] sqlUniqueObject(String sql,Object... args);
    public Object[] sqlUniqueObject(String sql,Map<String,Object> args);

    public <X> X sqlUniqueBean(String sql,Class<? extends X> transferClass,Object... args);
    public <X> X sqlUniqueBean(String sql,Class<? extends X> transferClass,Map<String,Object> args);


    public List<Object[]>  sqlListObjects(String sql,Object... args);
    public List<Object[]>  sqlListObjects(String sql,Map<String,Object> args);

    public Page<Object[]>  sqlPageObjects(String sql,PageParams pageParams,Object... args);
    public Page<Object[]>  sqlPageObjects(String sql,PageParams pageParams,Map<String,Object> args);



    public <X> List<X>  sqlListBeans(String sql,Class<? extends X> transferClass,Object... args);
    public <X> List<X>  sqlListBeans(String sql,Class<? extends X> transferClass,Map<String,Object> args);

    public <X> Page<X>  sqlPageBeans(String sql,Class<? extends X> transferClass,PageParams pageParams,Object... args);
    public <X> Page<X>  sqlPageBeans(String sql,Class<? extends X> transferClass,PageParams pageParams,Map<String,Object> args);


    public List<Map<String, Object>> sqlListMaps(String sql,Object... args);
    public List<Map<String, Object>> sqlListMaps(String sql,Map<String, Object> args);
    public List<Map<String, Object>> sqlListMaps(String sql,String[] fields, Object... args);
    public List<Map<String, Object>> sqlListMaps(String sql,String[] fields,Map<String, Object> args);



    public Page<Map<String, Object>> sqlPageMaps(String sql, PageParams pageParams,Object... args);
    public Page<Map<String, Object>> sqlPageMaps(String sql, PageParams pageParams,Map<String, Object> args);

    public Page<Map<String, Object>> sqlPageMaps(String sql, PageParams pageParams,String[] fields, Object... args);
    public Page<Map<String, Object>> sqlPageMaps(String sql, PageParams pageParams,String[] fields,Map<String, Object> args);



}

