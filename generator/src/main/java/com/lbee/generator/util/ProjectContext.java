package com.lbee.generator.util;

import java.util.HashMap;
import java.util.Map;

public class ProjectContext {
    private static ThreadLocal<Map<String, String>> PROJECT_CONFIG = new ThreadLocal<>();

    public static void add(String key, String val) {
        Map<String, String> config = PROJECT_CONFIG.get();
        if (null == config) {
            config = new HashMap<>();
        }
        config.put(key, val);
        PROJECT_CONFIG.set(config);
    }

    public static Map<String, String> getConfigMap() {
        return PROJECT_CONFIG.get();
    }
}
