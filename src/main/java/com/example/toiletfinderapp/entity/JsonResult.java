package com.example.toiletfinderapp.entity;

import java.io.Serializable;

public class JsonResult<T> implements Serializable {
    private T data;
    private String code;
    private String msg;

    public JsonResult() {
        this.code = "200";
        this.msg = "success!";
    }

    /**
     * @param code
     * @param msg
     */
    public JsonResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JsonResult(T data) {
        this.data = data;
        this.code = "200";
        this.msg = "Success!";
    }

    /**
     * @param data
     * @param msg
     */
    public JsonResult(T data, String msg) {
        this.data = data;
        this.code = "200";
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
