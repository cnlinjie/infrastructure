package com.github.cnlinjie.infrastructure.web.model;

import com.github.cnlinjie.infrastructure.web.Constant;
import com.google.common.collect.Maps;

import java.io.Serializable;
import java.util.Map;

/**
 * @author linjie
 * @version 0.0.1
 * @date 16-5-26
 */
public class RetModel implements Serializable {

    public static final RetModel SUCCESS = new RetModel(Constant.DEFAULT_ERRCODE_SUCCESS,"SUCCESS", new Object());

    protected Integer errCode = Constant.DEFAULT_ERRCODE_SUCCESS;

    protected String msg = "";

    protected Object data;

    public Object getData () {
        return data;
    }

    public void setData (Object data) {
        this.data = data;
    }

    public void put(String key,Object value) {
        if (data == null || !(data instanceof Map)) {
            data = Maps.newHashMap();
        }
        ((Map) data).put(key, value);
    }

    public RetModel () {
    }

    public RetModel (Object data) {
        this.data = data;
    }

    public RetModel (Integer errCode, String msg, Object data) {
        this.errCode = errCode;
        this.msg = msg;
        this.data = data;
    }


    public RetModel (Integer errCode, String msg) {
        this.errCode = errCode;
        this.msg = msg;
    }

    public Integer getErrCode () {
        return errCode;
    }

    public void setErrCode (Integer errCode) {
        this.errCode = errCode;
    }

    public String getMsg () {
        return msg;
    }

    public void setMsg (String msg) {
        this.msg = msg;
    }



}
