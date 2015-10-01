package com.github.cnlinjie.infrastructure.dao.infrastructure.dao.hibernate;

import org.hibernate.criterion.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Linjie
 * @date 2015/10/1.
 */
public class CriteriaParams {

    private List<Criterion> criterions = new ArrayList<Criterion>();

    private Projection projection = null;

    private ProjectionList projectionList = Projections.projectionList();

    private List<Order> orders = new ArrayList<Order>();

    public static CriteriaParams get() {
        return new CriteriaParams();
    }

    public CriteriaParams addOrder(Order... os) {
        if (os.length > 0) {
            orders.addAll(Arrays.asList(os));
        }
        return this;
    }


    public CriteriaParams add(Criterion... cs) {
        if (cs.length > 0) {
            criterions.addAll(Arrays.asList(cs));
        }
        return this;
    }

    public CriteriaParams addProjection(Projection... ps) {
        if(ps.length > 0) {
            for (Projection p : ps) {
                this.projectionList.add(p);
            }
        }
        this.projection = projectionList;
        return this;
    }


    public static CriteriaParams Add(Criterion... cs) {
        return get().add(cs);
    }

    public static  CriteriaParams AddProjection(Projection... p) {
        return get().addProjection(p);
    }

    public static CriteriaParams AddOrder(Order... os) {
        return get().addOrder(os);
    }


    public List<Criterion> getCriterions() {
        return criterions;
    }

    public Projection getProjection() {
        return projection;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
