package com.xwc.commons.model;

/**
 * 创建人：徐卫超
 * 时间: 2018/5/29
 * 功能：JSON 响应标准体
 * 描述：
 */
@SuppressWarnings("unused")
public class JsonMessage<T> {
    /**
     * 成功
     */
    private final static int SUCCESS = 0;
    /**
     * 业务逻辑错误
     */
    private final static int FAILED = 20;

    /**
     * 访问受限（无权限）
     */
    private final static int NOT_PRIVILEGE = 40;

    /**
     * 未认证
     */
    public final static int NOT_AUTHEN = 41;

    /**
     * 代码异常
     */
    private final static int ERROR = 50;


    private String msg;
    private int code;
    private T data;
    private String description;

    /**
     * 请求成功
     *
     * @return 返回json对象
     */
    public static <T> JsonMessage<T> succeed(T data) {
        JsonMessage<T> json = new JsonMessage<>();
        json.setData(data).setCode(SUCCESS).setMsg("操作成功");
        return json;
    }

    /**
     * 请求成功
     *
     * @return 返回json对象
     */
    public static <Void> JsonMessage<Void> succeed() {
        JsonMessage<Void> json = new JsonMessage<>();
        json.setCode(SUCCESS).setMsg("操作成功");
        return json;
    }

    /**
     * 求情失败
     *
     * @return 返回json对象
     */
    public static <Void> JsonMessage<Void> failed(String msg) {
        JsonMessage<Void> json = new JsonMessage<>();
        json.setCode(FAILED).setMsg(msg);
        return json;
    }

    public static <Void> JsonMessage<Void> failed(String msg, String description) {
        JsonMessage<Void> json = new JsonMessage<>();
        json.setCode(FAILED).setMsg(msg);
        json.setDescription(description);
        return json;
    }
    public static <Void> JsonMessage<Void> failed(String msg,Integer code, String description) {
        JsonMessage<Void> json = new JsonMessage<>();
        json.setCode(code).setMsg(msg);
        json.setDescription(description);
        return json;
    }

    public String getDescription() {
        return description;
    }

    public JsonMessage<T> setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public JsonMessage<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public int getCode() {
        return code;
    }

    public JsonMessage<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public T getData() {
        return data;
    }

    public JsonMessage<T> setData(T data) {
        this.data = data;
        return this;
    }
}
