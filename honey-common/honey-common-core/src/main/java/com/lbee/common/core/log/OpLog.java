package com.lbee.common.core.log;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OpLog {
    String module() default ""; // 操作模块
    String type() default "";  // 操作类型
    String desc() default "";  // 操作说明
}
