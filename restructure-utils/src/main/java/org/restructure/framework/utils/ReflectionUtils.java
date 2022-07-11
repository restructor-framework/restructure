package org.restructure.framework.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author João Henrique
 * @since 1.0.0
 */
public class ReflectionUtils {

    private ReflectionUtils() {
    }

    /**
     * Verifica a existência de um campo
     *
     * @param target tipo para busca
     * @param field  nome do campo
     * @return <code>True</code> caso exista e <code>False</code> caso contrario
     */
    public static Boolean hasField(Class<?> target, String field) {
        try {
            target.getDeclaredField(field);
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }

    public static Boolean hasMethod(Class<?> target, String name, Class<?>... typesParam) {
        try {
            target.getMethod(name, typesParam);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    public static Boolean hasAnnotation(Class<? extends Annotation> annotation, Field f) {
        return f.isAnnotationPresent(annotation);
    }

    public static Boolean hasAnnotation(Class<? extends Annotation> annotation, Method method) {
        return method.isAnnotationPresent(annotation);
    }

    public static Boolean hasAnnotation(Class<? extends Annotation> annotation, Class<?> clazz) {
        return clazz.isAnnotationPresent(annotation);
    }
}
