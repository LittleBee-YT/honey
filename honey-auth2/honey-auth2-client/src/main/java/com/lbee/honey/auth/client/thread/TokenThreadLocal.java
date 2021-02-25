package com.lbee.honey.auth.client.thread;

public class TokenThreadLocal {
    private final ThreadLocal<String> tokenThreadLocal = new ThreadLocal<>();

    public void setToken(String token) {
        tokenThreadLocal.set(token);
    }

    public String getToken() {
        return tokenThreadLocal.get();
    }

    /**
     * 不可实例化
     */
    private TokenThreadLocal() {

    }

    /**
     * 创建单例
     */
    private static TokenThreadLocal ttl = null;

    public static TokenThreadLocal get() {
        if (null == ttl)
            ttl = new TokenThreadLocal();
        return ttl;
    }
}
