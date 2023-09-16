/*
 * Copyright (C) 2023 CodeCraftersCN.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.org.codecrafters.devkit.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * {@linkplain MapUtil} 是一个实用程序类，提供将对象转换为地图和将地图转换为对象的方法。
 * <p>
 * 它还提供了使用反射获取和设置字段值的方法。
 *
 * @author Zihlu Wang
 * @version 1.1.0
 * @since 1.0.0
 */
@Slf4j
public final class MapUtil {

    /**
     * 私有构造函数，防止 {@linkplain MapUtil} 实例化。
     */
    private MapUtil() {
    }

    /**
     * 通过将字段名映射为相应的值，将对象转换为 {@linkplain Map}。
     *
     * @param obj 要转换为 {@linkplain Map} 的对象
     * @return 代表对象字段及其值的映射
     * @throws IllegalAccessException 如果在访问对象的字段时发生错误
     */
    public static Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        if (obj == null) {
            return null;
        }

        var map = new HashMap<String, Object>();

        var declaredFields = obj.getClass().getDeclaredFields();
        for (var field : declaredFields) {
            field.setAccessible(true);
            Object result = field.get(obj);
            if (result != null) {
                map.put(field.getName(), result);
            }
        }

        return map;
    }

    /**
     * 通过使用映射条目设置字段值，将映射转换为指定类型的对象。
     *
     * @param map          表示字段及其值的 {@linkplain Map}
     * @param requiredType {@linkplain Map} 中存储数据对应的类型
     * @param <T>          {@linkplain Map} 中存储数据对应的类型
     * @return 由 {@linkplain Map} 转换而来的指定类型的对象
     * the map
     * @throws NoSuchMethodException     如果找不到给定类型的构造函数
     * @throws InvocationTargetException 如果在调用构造函数时出现了异常
     * @throws InstantiationException    如果给定的类型是抽象类或接口
     * @throws IllegalAccessException    如果在访问对象的属性时出现了错误
     */
    public static <T> T mapToObject(Map<String, Object> map, Class<T> requiredType) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        var bean = requiredType.getConstructor().newInstance();
        if (map != null) {
            for (var entry : map.entrySet()) {
                try {
                    var entryValue = entry.getValue().toString();
                    // 通过字段名获取字段
                    var field = requiredType.getDeclaredField(entry.getKey());
                    var fieldType = field.getGenericType();

                    // 将字段转换为字节码
                    if (fieldType instanceof Class<?> fieldClass) {
                        if (fieldClass == Short.class || fieldClass == short.class) {
                            entry.setValue(Short.parseShort(entryValue));
                        } else if (fieldClass == Integer.class || fieldClass == int.class) {
                            entry.setValue(Integer.parseInt(entryValue));
                        } else if (fieldClass == Long.class || fieldClass == long.class) {
                            entry.setValue(Long.parseLong(entryValue));
                        } else if (fieldClass == Float.class || fieldClass == float.class) {
                            entry.setValue(Float.parseFloat(entryValue));
                        } else if (fieldClass == Double.class || fieldClass == double.class) {
                            entry.setValue(Double.parseDouble(entryValue));
                        } else if (fieldClass == Character.class || fieldClass == char.class) {
                            entry.setValue(entryValue.charAt(0));
                        } else if (fieldClass == Byte.class || fieldClass == byte.class) {
                            entry.setValue(Byte.parseByte(entryValue));
                        } else if (fieldClass == Boolean.class || fieldClass == boolean.class) {
                            entry.setValue(Boolean.parseBoolean(entryValue));
                        } else if (fieldClass == String.class) {
                            entry.setValue(entryValue);
                        } else {
                            log.error("无法推断 {} 字段的类型。", field.getName());
                            continue;
                        }
                    }

                    setFieldValue(bean, entry.getKey(), entry.getValue());
                } catch (Exception e) {
                    log.error("Map 转换至 Object 失败！");
                }
            }
        }
        return bean;
    }

    /**
     * 使用反射从对象中读取字段值。
     *
     * @param obj       从中获取字段值的对象
     * @param fieldName 字段的名称
     * @param fieldType 字段的类型的字节码
     * @param <T>       字段的类型
     * @return 对象中字段的值；如果字段不存在或无法访问，则为空。不存在或无法访问
     * @throws IllegalAccessException    如果在访问字段时发生错误
     * @throws InvocationTargetException 如果在调用字段的 {@code getter} 方法时发生错误
     * @throws NoSuchMethodException     如果指定的 {@code getter} 不存在
     */
    public static <T> T getFieldValue(Object obj, String fieldName, Class<T> fieldType) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        var methodName = getMethodName("get", fieldName);
        var objectClass = obj.getClass();
        var method = objectClass.getDeclaredMethod(methodName);

        method.setAccessible(true);
        return cast(method.invoke(obj), fieldType);
    }

    /**
     * 使用反射设置对象中字段的值。
     *
     * @param obj        设置字段值的对象
     * @param fieldName  字段名称
     * @param fieldValue 要设置的值
     * @throws InvocationTargetException 如果在调用字段设置方法时发生错误
     * @throws IllegalAccessException    如果在访问字段时发生错误
     * @throws NoSuchMethodException     如果没有指定的 {@code setter}
     */
    public static void setFieldValue(Object obj, String fieldName, Object fieldValue) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        var objectClass = obj.getClass();
        var methodName = getMethodName("set", fieldName);
        var method = objectClass.getDeclaredMethod(methodName, fieldValue.getClass());
        method.setAccessible(true);
        method.invoke(obj, fieldValue);
    }

    /**
     * 将指定值转换为所需类型。
     *
     * @param value        要转换的数值
     * @param requiredType 数值的转换的类型
     * @param <T>          数值的转换的类型
     * @return 转换后的值，如果转换不成功则返回 {@code null}
     */
    public static <T> T cast(Object value, Class<T> requiredType) {
        if (requiredType.isInstance(value)) {
            return requiredType.cast(value);
        }
        return null;
    }

    /**
     * 根据给定的前缀和字段名构建方法名。
     *
     * @param prefix    要添加到字段名中的前缀
     * @param fieldName 字段名称
     * @return 构造出的方法名称
     */
    private static String getMethodName(String prefix, String fieldName) {
        return prefix + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }

    /**
     * 返回指定对象的默认字符串表示。
     *
     * @param obj 返回默认字符串表示的对象
     * @return 对象的默认字符串表示
     */
    private static String defaultObject(Object obj) {
        if (obj == null) {
            return "";
        } else {
            return String.valueOf(obj);
        }
    }
}
