package com.yd.wxpay.common.data;


import com.yd.wxpay.common.data.util.MessageUtils;

import java.io.Serializable;

/**
 * @version 1.0
 * @file_name: JsonResult.java
 * @Description: TODO 统一返回给前端的结果类
 * @author: Ma Amin
 * @date: 2018-2-9 上午10:36:00
 */
public class ResponseResult<T> implements Serializable {

    @Override
    public String toString() {
        return "window.parent.postMessage([code=" + code + ", success=" + success
                + ", message=" + message + ", data=" + data + ", getCode()="
                + getCode() + ", isSuccess()=" + isSuccess()
                + ", getMessage()=" + getMessage() + ", getData()=" + getData()
                + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "]";
    }

    /**
     * @Fields serialVersionUID:TODO
     */
    private static final long serialVersionUID = 1L;

    // 状态码
    private int code = 200;

    // 是否成功，true或者false
    private boolean success = true;

    // 消息
    private String message;

    // 数据
    private T data;

    /**
     *
     */
    public ResponseResult() {
        super();
    }

    public static ResponseResult build() {
        return new ResponseResult();
    }

    /**
     * @param code
     * @param success
     * @param message
     * @param data
     */
    public ResponseResult(int code, boolean success, String message, T data) {
        super();
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    /**
     * @param code
     * @param success
     * @param message
     */
    public ResponseResult(int code, boolean success, String message) {
        super();
        this.code = code;
        this.success = success;
        this.message = message;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    public ResponseResult code(int code) {
        this.code = code;
        return this;
    }

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(T data) {
        this.data = data;
    }

    public ResponseResult success(String... message) {
        this.success = true;
        this.message = 0 == message.length ? MessageUtils.getMessage("opt.success") : message[0];
        return this;
    }

    public ResponseResult fail(String... message) {
        this.code = 400;
        this.success = false;
        this.message = 0 == message.length ? MessageUtils.getMessage("opt.fail") : message[0];
        return this;
    }

    public ResponseResult fail(int code, String... message) {
        this.code = code;
        this.success = false;
        this.message = 0 == message.length ? MessageUtils.getMessage("opt.fail") : message[0];
        return this;
    }

    public ResponseResult data(T data) {
        this.data = data;
        return this;
    }

}
