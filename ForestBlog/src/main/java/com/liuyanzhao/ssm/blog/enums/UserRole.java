package com.liuyanzhao.ssm.blog.enums;

/**
 * 角色枚举
 *
 * @author 言曌
 * @date 2021/2/25 2:31 下午
 */

public enum UserRole {

    ADMIN("admin", "管理员"),

    USER("user", "用户");

    private String value;

    private String message;

    UserRole(String value, String message) {
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
