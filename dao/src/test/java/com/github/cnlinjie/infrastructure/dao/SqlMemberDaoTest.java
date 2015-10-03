package com.github.cnlinjie.infrastructure.dao;

import com.github.cnlinjie.infrastructure.dao.bean.MemberBean;
import com.github.cnlinjie.infrastructure.dao.infrastructure.dao.Page;
import com.github.cnlinjie.infrastructure.dao.infrastructure.dao.PageParams;
import com.github.cnlinjie.infrastructure.dao.infrastructure.dao.nativesql.NativeSqlDaoImpl;
import com.github.cnlinjie.infrastructure.util.MapUtils;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author Linjie
 * @date 2015/10/1.
 */
public class SqlMemberDaoTest extends BaseTest {

    public NativeSqlDaoImpl dao;
    public Transaction transaction;

    @Before
    public void init() {
        dao = new NativeSqlDaoImpl();
        dao.setSessionFactory(sf);
        transaction = dao.getSession().getTransaction();
        transaction.begin();
    }


//    @Test
    public void sqlUniqueValue() {

        String sql = "SELECT m.`phone`  FROM `member` m WHERE m.`pk_id` = :pkId";

        String phone = dao.sqlUniqueValue(sql, 1l);
        String phone2 = dao.sqlUniqueValue(sql, MapUtils.builder().put("pkId",1l).get());
        System.out.println(phone);
        System.out.println(phone2);

    }


//    @Test
    public void sqlUniqueObject() {

        String sql = "SELECT m.`phone`,m.password  FROM `member` m WHERE m.`pk_id` = :pkId";

        Object[] objects = dao.sqlUniqueObject(sql, 1l);
        Object[] objects2 = dao.sqlUniqueObject(sql, MapUtils.builder().put("pkId", 1l).get());
        System.out.println(objects);
        System.out.println(objects2);

    }

//    @Test
    public void sqlUniqueBean() {
        String sql = "SELECT m.`phone` ,m.password   FROM `member` m WHERE m.`pk_id` = :pkId";
        MemberBean memberBean = dao.sqlUniqueBean(sql, MemberBean.class, 1l);
        MemberBean memberBean2 = dao.sqlUniqueBean(sql, MemberBean.class, MapUtils.builder().put("pkId", 100l).get());
        System.out.println(memberBean);
        System.out.println(memberBean2);
    }


//    @Test
    public void sqlPageObjects() {
        String sql = "SELECT m.*  FROM `member` m where m.password = :password";
        Page<Object[]> page = dao.sqlPageObjects(sql, PageParams.page(1, 10), "123123");
        Page<Object[]> page2 = dao.sqlPageObjects(sql, PageParams.page(1, 10), MapUtils.builder().put("password","123123").get());
        System.out.println(page);
    }


//    @Test
    public void sqlListBeans() {
        String sql = "SELECT m.`phone` ,m.password   FROM `member` m where m.password = :password";
        List<MemberBean> list = dao.sqlListBeans(sql, MemberBean.class, "123123");
        List<MemberBean> list2 = dao.sqlListBeans(sql, MemberBean.class, MapUtils.builder().put("password", "123123").get());
        System.out.println(list);
        System.out.println(list2);
    }


//    @Test
    public void sqlPageBeans() {
        String sql = "SELECT  m.`phone` ,m.password  FROM `member` m where m.password = :password";
        Page<MemberBean> page = dao.sqlPageBeans(sql, MemberBean.class, PageParams.page(1, 12), "123123");
        Page<MemberBean> page2 = dao.sqlPageBeans(sql, MemberBean.class, PageParams.page(1, 12), MapUtils.builder().put("password","123123").get());

        System.out.println(page);
        System.out.println(page2);
    }

//    @Test
    public void sqlListMaps() {
        String sql = "SELECT  m.`phone` as phone ,m.password as password2 FROM `member` m where m.password = :password";
        List<Map<String, Object>> maps = dao.sqlListMaps(sql, "123123");
        List<Map<String, Object>> maps2 = dao.sqlListMaps(sql, MapUtils.builder().put("password","123123").get());
        List<Map<String, Object>> maps3 = dao.sqlListMaps(sql, new String[]{"phone333","password333"},"123123");
        List<Map<String, Object>> maps4 = dao.sqlListMaps(sql, new String[]{"phone444","password444"},MapUtils.builder().put("password","123123").get());
        System.out.println(maps);
        System.out.println(maps2);
        System.out.println(maps3);
        System.out.println(maps4);
    }

    @Test
    public void sqlPageMaps() {
        String sql = "SELECT  m.`phone` as phone ,m.password as password2 FROM `member` m where m.password = :password";
        Page<Map<String, Object>> page = dao.sqlPageMaps(sql, PageParams.page(1, 20), "123123");
        Page<Map<String, Object>> page2 = dao.sqlPageMaps(sql, PageParams.page(1, 20), MapUtils.builder().put("password","123123").get());
        Page<Map<String, Object>> page3 = dao.sqlPageMaps(sql, PageParams.page(1, 20),new String[]{"phone333","password333"}, "123123");
        Page<Map<String, Object>> page4 = dao.sqlPageMaps(sql, PageParams.page(1, 20), new String[]{"phone444","password444"},MapUtils.builder().put("password","123123").get());
        System.out.println(page);
        System.out.println(page2);
        System.out.println(page3);
        System.out.println(page4);
    }




    @After
    public void after() {
        transaction.commit();
        dao.getSession().close();
        dao.getSessionFactory().close();
    }


}
