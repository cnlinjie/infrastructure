package com.github.cnlinjie.infrastructure.dao.hibernate;


import com.github.cnlinjie.infrastructure.util.spring.Assert;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by tminglei on 2015/6/3.
 */
public class QueryUtil {

    /**
     * 把 "true"/"false", 1/0 等方式表示的字符串转成 Boolean
     * @param value
     * @return
     */
    public static Boolean toBoolean(String value) {
        if (value != null && value.matches("^\\d$")) {
            return Integer.parseInt(value) == 1 ? true : false;
        } else {
            return Boolean.parseBoolean(value);
        }
    }

    /**
     * 把不同类型的整形数字转成 Integer
     * @param value Integer, Long, BigInteger or BigDecimal
     * @return
     */
    public static Integer toInt(Object value) {
        if (value == null || value instanceof Integer) return (Integer) value;
        else if (value instanceof Long) return ((Long) value).intValue();
        else if (value instanceof BigInteger) return ((BigInteger) value).intValue();
        else if (value instanceof BigDecimal) return ((BigDecimal) value).intValue();
        else throw new IllegalArgumentException("Unsupported type: " + value.getClass().getName());
    }

    /**
     * 把不同类型的整形数字转成 Long
     * @param value Integer, Long, BigInteger or BigDecimal
     * @return
     */
    public static Long toLong(Object value) {
        if (value == null || value instanceof Long) return (Long) value;
        else if (value instanceof Integer) return ((Integer) value).longValue();
        else if (value instanceof BigInteger) return ((BigInteger) value).longValue();
        else if (value instanceof BigDecimal) return ((BigDecimal) value).longValue();
        else throw new IllegalArgumentException("Unsupported type: " + value.getClass().getName());
    }



    // omit prefix and postfix brackets
    public static String trimBrackets(String arrayStr) {
        if (arrayStr == null) return arrayStr;

        return arrayStr
                .replace("[", "")
                .replace("]", "");
    }

    public static List<Integer> idStrToIntList(String ids) {
        List<Integer> results = new ArrayList<Integer>();
        ids = trimBrackets(ids);
        if(!StringUtils.isEmpty(ids)) {
            for(String id : ids.split(",")) {
                results.add(Integer.parseInt(id.trim()));
            }
        }
        return results;
    }

    public static List<Long> idStrToLongList(String ids) {
        List<Long> results = new ArrayList<Long>();
        ids = trimBrackets(ids);
        if(!StringUtils.isEmpty(ids)) {
            for(String id : ids.split(",")) {
                results.add(Long.parseLong(id.trim()));
            }
        }
        return results;
    }

    // map array data to map objects
    // NOTE: object[].length should be equals to keyNames.length
    public static List<Map<String, Object>> arraysToMaps(List<Object[]> records, String... keyNames) {
        if (records == null) throw new IllegalArgumentException("记录集为NULL！");
        if (!records.isEmpty() && records.get(0).length != keyNames.length) {
            throw new IllegalArgumentException("记录的列数不等于键名的数量！");
        }

        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
        for(Object[] record : records) {
            results.add(arrayToMap(record, keyNames));
        }
        return results;
    }

    // map array data to map object
    // NOTE: record.length should be equals to keyNames.length
    public static Map<String, Object> arrayToMap(Object[] record, String... keyNames) {
        if (record == null) throw new IllegalArgumentException("记录为 NULL！");
        if (record.length != keyNames.length) {
            throw new IllegalArgumentException("记录的列数不等于键名的数量！");
        }
        Assert.isTrue(!StringUtils.isAnyEmpty(keyNames),"有列名(alias) 为空");
        Map<String, Object> result = new HashMap<String, Object>();
        for(int i=0; i< record.length; i++) {
            result.put(keyNames[i], record[i] != null ? record[i]:"");
        }
        return result;
    }



    public static List<Map<String, Object>> beansToMaps(List<?> beans) throws Exception {
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
        if (beans != null) {
            for(Object bean : beans) {
                results.add(beanToMap(bean, null));
            }
        }
        return results;
    }

    static final List<String> IGNORED_PROP_NAMES = Arrays.asList(
            "hibernateLazyInitializer",
            "handler",
            "class");
    public static Map<String, Object> beanToMap(Object bean, Map<String, Object> dest) throws Exception {
        if (dest == null) dest = new HashMap<String, Object>();

        if (bean != null) {
            Map<String, Object> props = PropertyUtils.describe(bean);
            for(String name : IGNORED_PROP_NAMES) {
                props.remove(name);
            }
            dest.putAll(props);
        }

        return dest;
    }

    public static boolean isAnswerEquals(String expected, String toBeChecked) {
        if (StringUtils.isEmpty(expected)) return true;
        else if (StringUtils.isEmpty(toBeChecked)) return false;

        expected = trimBrackets(expected);
        toBeChecked = trimBrackets(toBeChecked);
        if (expected.contains(",")) {
            List<String> expectedSet = Arrays.asList(expected.split(","));
            List<String> toBeCheckSet = Arrays.asList(toBeChecked.split(","));
            return expectedSet.containsAll(toBeCheckSet) && toBeCheckSet.containsAll(expectedSet);
        } else {
            return expected.equalsIgnoreCase(toBeChecked);
        }
    }

}
