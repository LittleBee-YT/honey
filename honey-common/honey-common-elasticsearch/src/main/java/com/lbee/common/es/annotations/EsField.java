package com.lbee.common.es.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EsField {

    String name() default "";

    String type() default "";

    int dims() default 0;

    boolean fielddata() default false;
}
