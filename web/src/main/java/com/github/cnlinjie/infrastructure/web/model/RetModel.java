package com.github.cnlinjie.infrastructure.web.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.github.cnlinjie.infrastructure.web.Constant;
import com.google.common.collect.Maps;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author linjie
 * @version 0.0.1
 * @date 16-5-26
 */
public class RetModel implements Serializable {

    @Deprecated
    public static final RetModel SUCCESS = new RetModel(Constant.DEFAULT_ERRCODE_SUCCESS, "SUCCESS", new Object());

    protected Integer errCode = Constant.DEFAULT_ERRCODE_SUCCESS;
    protected Integer code = Constant.DEFAULT_ERRCODE_SUCCESS;

    protected String msg = "";

    protected Object data;

    public Object getData() {
        return data;
    }

    public RetModel setData(Object data) {
        this.data = data;

        return this;
    }

    public RetModel put(String key, Object value) {
        if (data == null || !(data instanceof Map)) {
            data = Maps.newHashMap();
        }
        ((Map) data).put(key, value);
        return this;
    }

    public RetModel() {
    }


    public RetModel(Object data) {
        this.data = data;
    }

    public RetModel(Integer errCode, String msg, Object data) {
        this.errCode = errCode;
        this.code = errCode;
        this.msg = msg;
        this.data = data;
    }


    public RetModel(Integer errCode, String msg) {
        this.errCode = errCode;
        this.code = errCode;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.errCode = code;
        this.code = code;
    }


    @JSONField(serialize = false)
    @Deprecated
    public Integer getErrCode() {
        return errCode;
    }

    @Deprecated
    public RetModel setErrCode(Integer errCode) {
        this.code = errCode;
        this.errCode = errCode;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RetModel setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public static RetModel Put(String key, Object value) {
        return get().put(key, value);
    }


    public static RetModel get() {
        return new RetModel(Constant.DEFAULT_ERRCODE_SUCCESS, "SUCCESS", new HashMap<String, Object>());
    }

    public static RetModel error() {
        return new RetModel(Constant.DEFAULT_ERRCODE_FAIL, "FAIL", new HashMap<String, Object>());
    }

}
