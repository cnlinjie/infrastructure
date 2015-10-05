package com.github.cnlinjie.infrastructure.dao;

import com.github.cnlinjie.infrastructure.dao.entity.Member;
import com.github.cnlinjie.infrastructure.dao.hibernate.CriteriaParams;
import com.google.common.collect.Lists;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Linjie
 * @date 2015/10/1.
 */
public class MemberDaoTest extends BaseTest {

    public MemberDao dao;
    public Transaction transaction;

    @Before
    public void init() {
        dao = new MemberDao();
        dao.setSessionFactory(sf);
        transaction = dao.getSession().getTransaction();
        transaction.begin();
    }


    @Test
    public void delete() {
        ArrayList<Long> ids = Lists.newArrayList(100l,101l,102l);
        int i = dao.delete(ids);
        System.out.println(i);
    }

    @After
    public void after() {
        transaction.commit();
        dao.getSession().close();
        dao.getSessionFactory().close();
    }


}
