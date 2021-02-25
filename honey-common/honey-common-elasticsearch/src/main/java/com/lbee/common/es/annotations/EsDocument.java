package com.lbee.common.es.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EsDocument {

    String indexName();

    int shards() default 1;

    int replicas() default 0;

    String mappingId() default "id";
}
