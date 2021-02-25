package com.lbee.common.result;

public enum ResultCode implements IErrorCode {

    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),

    REQUEST_METHOD_NOT(400, "不支持的请求方法"),
    ILLEGAL_ARGUMENT(401, "不合法的参数"),
    UNAUTHORIZED(402, "暂未登录或token已经过期"),
    FORBIDDEN(403, "没有相关权限"),
    VALIDATE_FAILED(405, "参数检验失败"),

//    TOKEN_PAST(301, "token过期"),
//    TOKEN_ERROR(302, "token异常"),
    // 登录异常
    LOGIN_ERROR(303, "登录异常"),
    LOGIN_LOCK(304, "用户被禁用"),
    LOGIN_NAME(305, "用户名错误"),
    LOGIN_NAME_NULL(306, "用户名为空"),
    LOGIN_PASSWORD(307, "密码错误"),
    LOGIN_CODE(308, "验证码错误"),
    LOGOUT_CODE(309, "退出失败，token 为空");

    private int code;

    private String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
