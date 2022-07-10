package org.restructure.framework.utils;

import java.lang.reflect.Field;

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

    public static Boolean hasMethod(Class<?> target, String name) {
        return true;
    }
}
