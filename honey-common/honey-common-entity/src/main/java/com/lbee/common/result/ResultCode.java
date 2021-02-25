//package com.lbee.common.result;
//
//public enum ResultCode implements IErrorCode {
//
//    SUCCESS(200, "操作成功"),
//
//    FAILED(500, "操作失败"),
//
//    UNAUTHORIZED(401, "暂未登录或token已经过期"),
//    FORBIDDEN(403, "没有相关权限"),
//
//    TOKEN_PAST(301, "token过期"),
//    TOKEN_ERROR(302, "token异常"),
//    // 登录异常
//    LOGIN_ERROR(303, "登录异常"),
//    LOGIN_LOCK(304, "用户被禁用"),
//    LOGIN_NAME(305, "用户名错误"),
//    LOGIN_NAME_NULL(306, "用户名为空"),
//    LOGIN_PASSWORD(307, "密码错误"),
//    LOGIN_CODE(308, "验证码错误"),
//    LOGOUT_CODE(309, "退出失败，token 为空"),
//
//    VALIDATE_FAILED(404, "参数检验失败");
//
//    private long code;
//
//    private String message;
//
//    ResultCode(long code, String message) {
//        this.code = code;
//        this.message = message;
//    }
//
//    @Override
//    public long getCode() {
//        return code;
//    }
//
//    @Override
//    public String getMessage() {
//        return message;
//    }
//}
