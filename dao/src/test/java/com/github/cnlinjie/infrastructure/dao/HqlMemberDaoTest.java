package com.github.cnlinjie.infrastructure.dao;

import com.github.cnlinjie.infrastructure.dao.bean.MemberBean;
import com.github.cnlinjie.infrastructure.dao.entity.Member;
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
public class HqlMemberDaoTest extends BaseTest {

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
    public void unique() {
        String hql = "from Member where pkId = ?";
        String hqlMap = "from Member where pkId = :pkId";
        Member unique = dao.unique(hql, 1l);
        Member member = dao.unique(hqlMap, MapUtils.builder().put("pkId", 1l).get());
        System.out.println(unique);
        System.out.println(member);
    }

//    @Test
    public void list() {
        String hql = " from Member where phone = :phone";
        List<Member> list = dao.list(hql, "111");
        List<Member> list2 = dao.list(hql, MapUtils.builder().put("phone","111").get());
        System.out.println(list);
    }


//    @Test
    public void page() {
        String hql = " from Member where phone = :phone and password= :password";
        Page<Member> page = dao.page(hql, PageParams.page(1, 10), "111", "123123");
        Page<Member> page2 = dao.page(hql, PageParams.page(1, 10),
                MapUtils.builder().put("phone","222").put("password","123123").get());
        System.out.println(page2);
        System.out.println(page);
    }


//    @Test
    public void uniqueValue() {
        String hql = "select phone from Member where pkId = :pkId";
        String phone = dao.uniqueValue(hql, 2l);
        String phone2 = dao.uniqueValue(hql, MapUtils.builder().put("pkId",3l).get());
        System.out.println(phone);
        System.out.println(phone2);
    }

//    @Test
    public void uniqueObject() {
        String hql = "select phone,password from Member where pkId = :pkId";
        Object[] o = dao.uniqueObject(hql, 2l);
        Object[] o2  = dao.uniqueObject(hql, MapUtils.builder().put("pkId",3l).get());
        System.out.println(o);
        System.out.println(o2);
//        System.out.println(phone2);
    }
//    @Test
    public void listObjects() {
        String hql = "select phone,password from Member where phone = :phone";
        List<Object[]> list = dao.listObjects(hql, "111");
        List<Object[]> list2  = dao.listObjects(hql, MapUtils.builder().put("phone", "111").get());
        System.out.println(list);
        System.out.println(list2);
//        System.out.println(phone2);
    }

//    @Test
    public void pageObjects() {
        String hql = "select phone,password from Member where phone = :phone";
        Page<Object[]> page = dao.pageObjects(hql, PageParams.page(1, 6), "111");
        Page<Object[]> page2  = dao.pageObjects(hql, PageParams.page(1, 5),MapUtils.builder().put("phone", "111").get());
        System.out.println(page);
        System.out.println(page2);

//        System.out.println(phone2);
    }


//    @Test
    public void uniqueMap() {
        String hql = "select phone as phone2 ,password as password2 from Member where pkId = :pkId";
        Map<String, Object> map1 = dao.uniqueMap(hql, 2l);
        Map<String, Object> map2 = dao.uniqueMap(hql, MapUtils.builder().put("pkId", 3l).get());
        System.out.println(map1);
//        System.out.println(phone2);
    }



//    @Test
    public void listMaps() {
        String hql = "select phone as phone2,password  as password2 from Member where phone = :phone";
        List<Map<String, Object>> maps = dao.listMaps(hql, "111");
        List<Map<String, Object>> maps2 = dao.listMaps(hql, MapUtils.builder().put("phone", "111").get());

        System.out.println(maps);
        System.out.println(maps2);
    }


//    @Test
    public void pageMaps() {
        String hql = "select phone as phone2,password  as password2 from Member where phone = :phone";
        Page<Map<String, Object>> mapPage = dao.pageMaps(hql, PageParams.page(1, 18), "111");
        Page<Map<String, Object>> mapPage2 = dao.pageMaps(hql, PageParams.page(1, 18), MapUtils.builder().put("phone", "111").get());

        System.out.println(mapPage);
        System.out.println(mapPage2);
    }


//    @Test
    public void uniqueBean() {
        String hql = "select m.phone as phone ,m.password as password  from Member m where m.pkId = :pkId";
        MemberBean memberBean = dao.uniqueBean(hql, MemberBean.class, 1l);
        System.out.println(memberBean);

    }
    @Test
    public void pageBeans() {
        String hql = "select phone as phone,password as password from Member where phone = :phone";
        Page<MemberBean> page = dao.pageBeans(hql, MemberBean.class, PageParams.page(1, 3), "222");
        System.out.println(page);

    }




    @After
    public void after() {
        transaction.commit();
        dao.getSession().close();
        dao.getSessionFactory().close();
    }


}
