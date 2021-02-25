package com.lbee.generator.util;

public class FileVersion {
    private static long VERSION = 0;

    static {
        System.out.println("加载了一次哦");
        VERSION = System.currentTimeMillis();
    }

    public static long get(){
        return VERSION;
    }

}
