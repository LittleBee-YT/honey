package com.lbee.generator.util;

import java.util.HashMap;
import java.util.Map;

public class DataTypeUtil {

    private static Map<String, String> types = new HashMap();

    static {
        types.put("tinyint", "Integer");
        types.put("smallint", "Integer");
        types.put("mediumint", "Integer");
        types.put("int", "Integer");
        types.put("integer", "Integer");
        types.put("bigint", "Long");
        types.put("float", "Float");
        types.put("double", "Double");
        types.put("decimal", "BigDecimal");
        types.put("bit", "Boolean");
        types.put("char", "String");
        types.put("varchar", "String");
        types.put("tinytext", "String");
        types.put("text", "String");
        types.put("mediumtext", "String");
        types.put("longtext", "String");
        types.put("date", "LocalDate");
        types.put("datetime", "LocalDateTime");
        types.put("timestamp", "LocalDateTime");
    }

    public static String getDataType(String dbType){
        return types.get(dbType);
    }

}
