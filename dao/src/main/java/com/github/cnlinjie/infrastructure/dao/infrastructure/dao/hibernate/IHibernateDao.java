package com.github.cnlinjie.infrastructure.dao.infrastructure.dao.hibernate;

import com.github.cnlinjie.infrastructure.dao.infrastructure.dao.IDao;

import java.io.Serializable;

/**
 * @author Linj
 * @date 2015/9/22
 */
public interface IHibernateDao<T, PK extends Serializable>  extends IDao {
}
