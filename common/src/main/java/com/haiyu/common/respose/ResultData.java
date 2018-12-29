package com.haiyu.common.respose;

import lombok.Data;

import java.io.Serializable;

/**
 * @Title: ResultData
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/12/28 16:59
 */
@Data
public class ResultData implements Serializable {
    private Head head;
    private Object data;

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResultData() {
    }

    public ResultData(Head head) {
        this.head = head;
    }

    public ResultData(Head head, Object data) {
        this.head = head;
        this.data = data;
    }



    /**
     * 创建一个成功的ResultData
     *
     * @return
     */
    public static ResultData success(String statusCode,String message) {
        Head head = new Head(200, statusCode,message);
        ResultData resultData = new ResultData(head);
        return resultData;
    }

    /**
     * 创建一个错误的ResultData
     *
     * @return
     */
    public static ResultData fail(String statusCode,String message) {
        Head head = new Head(400, statusCode,message);
        ResultData resultData = new ResultData(head);
        return resultData;
    }

    /**
     * 往ResultData中放入数据
     *
     * @param data
     * @return
     */
    public ResultData with(Object data) {
        this.data = data;
        return this;
    }




    /**
     * 内部类
     * 接口返回的响应Head
     */
    @Data
    public static final class Head implements Serializable {
        public final int status;
        public final String statusCode;
        public final String message;


        public Head(int status, String statusCode, String message) {
            this.status = status;
            this.statusCode = statusCode;
            this.message = message;
        }
    }

}
