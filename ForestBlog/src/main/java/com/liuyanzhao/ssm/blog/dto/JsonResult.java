package com.liuyanzhao.ssm.blog.dto;

import lombok.Data;

/**
 * @author 言曌
 * @date 2017/11/30 下午7:04
 */

@Data
public class JsonResult<T> {


    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 返回的具体内容
     */
    private T data;


    public JsonResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public JsonResult() {
    }


    public JsonResult fail() {
        return new JsonResult(1, "操作失败", null);
    }

    public JsonResult fail(String msg) {
        return new JsonResult(1, msg, null);
    }

    public JsonResult ok() {
        return new JsonResult(0, "操作成功", null);
    }


    public JsonResult ok(T data) {
        return new JsonResult(0, "操作成功", data);
    }
}
