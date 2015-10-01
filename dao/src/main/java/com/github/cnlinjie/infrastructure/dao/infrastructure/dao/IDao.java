package com.github.cnlinjie.infrastructure.dao.infrastructure.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Linjie
 * @date 2015/9/21.
 */
public interface IDao<T ,PK extends Serializable> {

    // 保存
    public void save(T t);

    // 更新
    public void update(T t);

    // 删除（主键）
    public void delete(PK pk);

    // 删除
    public void delete(T t);

    // 保存或更新
    public void saveOrUpdate(T t);

    // 执行语句
    public int executeUpdate(String queryString, Map<String, Object> args);


    // 执行语句
    public int executeUpdate(String queryString, Object... args);

    // 删除多条
    public int delete(List<PK> ids);


    /**
     * 查询单个
     * @param pk 主键
     * @return
     */
    public T get(PK pk);

    // 根据属性查单个
    public T find(String key,String value);

    // 根据参数查单个
    public T find(QueryParams params);

    // 分页查
    public Page<T>  findPage(QueryParams params);

    // 根据条件List
    public List<T> findlist(QueryParams params);

    // 查全部
    public List<T> findAll();

}
