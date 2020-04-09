package com.hcb.newBilly.common;

import org.apache.commons.lang3.ObjectUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class EntityUtils {
    public static void copyProperty(Object src, Object target) {
        Class<?> srcClass = src.getClass();
        Field[] srcFields = srcClass.getDeclaredFields();
        Map<String, Field> srcFieldMap = new HashMap<>();
        for (Field field : srcFields) {
            field.setAccessible(true);
            srcFieldMap.put(field.getName(), field);
        }
        Class<?> targetClass = target.getClass();
        Field[] targetFields = targetClass.getDeclaredFields();
        for (int i = 0; i < targetFields.length; i++) {
            Field targetField = targetFields[i];
            targetField.setAccessible(true);
            String targetFieldName = targetField.getName();
            Field srcField = srcFieldMap.get(targetFieldName);
            if (srcField != null) {
                try {
                    Object srcValue = srcField.get(src);
                    if (ObjectUtils.isNotEmpty(srcValue)) {
                        targetField.set(target, srcValue);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static Double getMinus(Double num) {
        if (num == null) {
            return (double) 0;
        } else {
            return num > 0 ? -num : num;
        }
    }
    public static Double getPositive(Double num) {
        if (num == null) {
            return (double) 0;
        } else {
            return num > 0 ? num : -num;
        }
    }

}
