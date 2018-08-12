package com.kingja.miaosha.result;

/**
 * Description:TODO
 * Create Time:2018/8/6 17:30
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Result {

    private int code;
    private String msg;
    private Object data;

    private Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result success() {
        return new Result(0, "操作成功", null);
    }

    public static Result success(Object data) {
        return new Result(0, "操作成功", data);
    }

    public static Result error(CodeMsg codeMsg) {
        return new Result(codeMsg.getCode(), codeMsg.getMsg(), null);
    }
}
