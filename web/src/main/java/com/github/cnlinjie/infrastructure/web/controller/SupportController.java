package com.github.cnlinjie.infrastructure.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class SupportController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected Session session;


}
