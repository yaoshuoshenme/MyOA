package com.edu.commons.vo;

public class JsonModel {
    private Integer code;
    private String msg;
    private Object ob;

    public static JsonModel ok(){
        JsonModel js = new JsonModel();
        js.setCode(0);
        js.setMsg("success");
        return js;
    }

    public static JsonModel error(){
        JsonModel js = new JsonModel();
        js.setCode(1);
        js.setMsg("false");
        return js;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getOb() {
        return ob;
    }

    public void setOb(Object ob) {
        this.ob = ob;
    }
}
