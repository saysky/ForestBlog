package com.liuyanzhao.ssm.blog.enums;

/**
 * @author 言曌
 * @date 2018/11/17 下午4:47
 */

public enum Role {

    ADMIN(1, "博主"),
    VISITOR(0, "访客");

    private Integer value;

    private String message;

    Role(Integer value, String message) {
        this.value = value;
        this.message = message;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
