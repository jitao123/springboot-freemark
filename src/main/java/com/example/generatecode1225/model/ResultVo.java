package com.example.generatecode1225.model;

/**
 * @description:
 * @author: AT
 * @Date: 2020/12/25 2:02 下午
 */
public class ResultVo {
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObject() {
        return obj;
    }

    public void setObject(Object object) {
        this.obj = object;
    }


    private ResultVo() {
    }

    private ResultVo(Integer status, String msg, Object object) {
        this.status = status;
        this.msg = msg;
        this.obj = object;
    }

    public static ResultVo Ok(String msg,Object object){
        return new ResultVo(200,msg,object);
    }

    public static ResultVo Ok(String msg){
        return new ResultVo(200,msg,null);
    }


    public static ResultVo err(String msg,Object object){
        return new ResultVo(500,msg,object);
    }

    public static ResultVo err(String msg){
        return new ResultVo(500,msg,null);
    }

    private Integer status;
    private String msg;
    private Object obj;
}
