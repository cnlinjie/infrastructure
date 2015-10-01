package com.github.cnlinjie.infrastructure.dao.infrastructure.dao;

import java.util.List;

/**
 * @author Linjie
 * @date 2015/9/21.
 */
public class Where {



    private String key;

    private Restriction restriction = Restriction.EQ;

    private String value;

    private List<Where> childers;

    public Where(String key, String value) {
        this.key = key;
        this.value = value;
    }


    public Where(String key,String value, Restriction restriction) {
        this.key = key;
        this.restriction = restriction;
        this.value = value;
    }

    public Where() {
    }



    public String getKey() {
        return key;
    }

    public Restriction getRestriction() {
        return restriction;
    }

    public String getValue() {
        return value;
    }

    public List<Where> getChilders() {
        return childers;
    }
}
