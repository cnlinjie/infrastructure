package com.github.cnlinjie.infrastructure.dao;

import com.github.cnlinjie.infrastructure.dao.entity.Member;
import com.github.cnlinjie.infrastructure.dao.infrastructure.dao.Page;
import com.github.cnlinjie.infrastructure.dao.infrastructure.dao.PageParams;
import com.github.cnlinjie.infrastructure.dao.infrastructure.dao.hibernate.CriteriaParams;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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

    //    @Test
    public void save() {
        Member member = new Member();
        member.setUsername("zhangsan");
        System.out.println(sf);
        System.out.println(dao);
        dao.save(member);
    }


    public void unique() {

        Member unique = dao.unique(
                Restrictions.eq("username", "zhangsan")
        );
        System.out.println(unique.getPkId());
    }

    //    @Test
    public void list() {
        List<Member> list1 = dao.list(Restrictions.eq("password", "123123"));
        List<Member> list = dao.list(Restrictions.eq("phone", "111"), Order.desc("pkId"));
        List<Member> list2 = dao.list(CriteriaParams.Add(
                Restrictions.eq("password", "123123"),
                Restrictions.eq("phone", "111")
        ));
        System.out.println(list.size());
        System.out.println(list1.size());
        System.out.println(list2.size());
    }

    //    @Test
    public void page() {
        Page<Member> page = dao.page(PageParams.page(1, 2));
        Page<Member> page1 = dao.page(Restrictions.eq("password", "123123"), PageParams.page(1, 2));


        Page<Member> page2 = dao.page(CriteriaParams.
                Add(Restrictions.eq("password", "123123"))
                .addOrder(Order.asc("pkId"))
                .addProjection(Projections.property("password"), Projections.property("phone")), PageParams.page(1, 10));

        List<Member> content = page2.getContent();
        System.out.println(content);
        System.out.println(page);
    }

//    @Test
    public void uniqueValue() {
        Object o = dao.uniqueValue(
                Restrictions.eq("pkId", 1l),
                Projections.property("password")
        );
        System.out.println(o);
    }

//    @Test
    public void uniqueObject() {
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.property("password"),"password");
        projectionList.add(Projections.property("phone"));
        Object[] os = dao.uniqueObject(
                Restrictions.eq("pkId", 1l),
                projectionList
        );
        System.out.println(os);
    }



//    @Test
    public void listObjects() {
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.property("password"));
        projectionList.add(Projections.property("phone"));

        List<Object[]> list = dao.listObjects(
                Restrictions.eq("phone", "111"),
                projectionList
        );
        System.out.println(list);
    }

    @Test
    public void uniqueMap() {
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.property("password"),"password2");
        projectionList.add(Projections.property("phone"),"phone2");
        Map<String, Object> map = dao.uniqueMap(Restrictions.eq("pkId", 1l), projectionList);
        System.out.println(map);
    }


    @After
    public void after() {
        transaction.commit();
        dao.getSession().close();
        dao.getSessionFactory().close();
    }


}
