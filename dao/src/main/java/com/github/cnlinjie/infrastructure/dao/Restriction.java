/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.cnlinjie.infrastructure.dao;

/**
 * Created by ZaoSheng on 2015/7/30.
 */
public enum Restriction {

    /**
     * 等于查询（from Object o where o.property = ?）
     */
    EQ {
        public String toMatchString(String pattern) {
            return "= :" + pattern;
        }
    },

    /**
     * 非等于查询（from Object o where o.property <> ?）
     */
    NE {
        public String toMatchString(String pattern) {
            return "<> :" + pattern;
        }
    },

    /**
     * 大于等于查询（from Object o where o.property >= ?）
     */
    GE {
        public String toMatchString(String pattern) {
            return ">= :" + pattern;
        }

    },

    /**
     * 大于查询（from Object o where o.property > ?）
     */
    GT {
        @Override
        public String toMatchString(String pattern) {
            return "> :" + pattern;
        }
    },

    /**
     * 小于等于查询（from Object o where o.property <= ?）
     */
    LE {
        @Override
        public String toMatchString(String pattern) {
            return "<= :" + pattern;
        }
    },

    /**
     * 小于查询（from Object o where o.property < ?）
     */
    LT {
        @Override
        public String toMatchString(String pattern) {
            return "< :" + pattern;
        }
    },
    /**
     * 两个值之间查询（from Object o where o.property between ? and ?）
     */
    BETWEEN {
        @Override
        public String toMatchString(String pattern) {
            return String.format("%s between :%s1 and :%s2", pattern, pattern, pattern);
        }
    },

    /**
     * 包含查询（from Object o where o.property in(?,?,?)）
     */
    IN {
        @Override
        public String toMatchString(String pattern) {
            return String.format("in (:%s)" , pattern);
        }
    },

    /**
     * 非包含查询（from Object o where o.property not in(?,?,?)）
     */
    NIN {
        @Override
        public String toMatchString(String pattern) {
            return String.format("not in ( :%s )" , pattern);
        }
    },

    /* *
     * 左模糊查询（from Object o where o.property like %?）
     */
    LLIKE {
        @Override
        public String toMatchString(String pattern) {
            return "%" + pattern;
        }
    },

    /* *
      * 右模糊查询（from Object o where o.property like ?%)
      */
    RLIKE {
        @Override
        public String toMatchString(String pattern) {
            return pattern + '%';
        }
    },

    /* *
      * 模糊查询（from Object o where o.property like %?%)
      */
    LIKE {
        @Override
        public String toMatchString(String pattern) {
            return '%' + pattern + '%';
        }
    },
    /* *
      * 模糊查询（from Object o where o.property  is null)
      */
    NULL {
        @Override
        public String toMatchString(String pattern) {
            return  pattern + " is null";
        }
    },
    /* *
      * 模糊查询（from Object o where o.property is not null)
      */
    NOTNULL  {

        @Override
        public String toMatchString(String pattern) {
            return  pattern + " is not null";
        }
    };

    public abstract String toMatchString(String pattern);

}