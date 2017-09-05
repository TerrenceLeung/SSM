package utils;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by YannLeung on 2016/11/18.
 * the standard data returned from Server to Web Browser
 */
@SuppressWarnings("unchecked")
public class Json<T> {

    public static final String CODE_SUCCESS = "0";

    @JSONField(ordinal = 1)
    private String code;//opt result:0.success
    @JSONField(ordinal = 2)
    private String msg;
    @JSONField(ordinal = 3)
    private T data;

    public Json() {
    }

    public Json(String msg, T data, String code) {
        this.msg = msg;
        this.data = data;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 判断结果是否成功
     *
     * @return
     */
    @JSONField(serialize = false)
    public boolean isOK() {
        return CODE_SUCCESS.equals(this.code);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).
                append("msg", this.msg).
                append("code", this.code).
                append("data", this.data).
                toString();
    }

    /**
     * 直接构造操作成功的Json实例
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Json success(T data) {
        Json<T> json = new Json<T>();
        json.setCode(CODE_SUCCESS);
        json.setMsg("操作成功！");
        json.setData(data);
        return json;
    }

    /**
     * 直接构造操作成功的Json实例
     * @param data 业务数据
     * @param msg  提示信息
     * @param <T>
     * @return
     */
    public static <T> Json success(T data, String msg) {
        Json<T> json = new Json<T>();
        json.setCode(CODE_SUCCESS);
        json.setMsg(msg);
        json.setData(data);
        return json;
    }

    /**
     * 直接构造操作失败的Json实例
     *
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Json fail(String code, String msg) {
        Json<T> json = new Json<T>();
        json.setCode(code);
        json.setMsg(msg);
        return json;
    }

    /**
     * 直接构造操作失败的Json实例
     *
     * @param code
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Json fail(String code, String msg, T data) {
        Json<T> json = fail(code, msg);
        json.setData(data);
        return json;
    }
}
