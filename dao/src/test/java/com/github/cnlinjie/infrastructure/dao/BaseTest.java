package com.github.cnlinjie.infrastructure.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Linjie
 * @date 2015/10/1.
 */
public class BaseTest {
    public static SessionFactory sf = null ;


    @Before
    public void initCfg() {
        Configuration cfg = new Configuration().configure();
        sf = cfg.buildSessionFactory(new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build());
    }



}
