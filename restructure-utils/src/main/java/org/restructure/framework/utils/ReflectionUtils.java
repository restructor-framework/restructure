package org.restructure.framework.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Optional;

/**
 * Classe Utilitária para trabalhar com reflexão
 *
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
        return safeGetField(target, field).isPresent();
    }

    /**
     * @param target     classe para busca
     * @param name       nome do método
     * @param typesParam tipos dos parâmetros
     * @return <code>True</code> caso seja encontrado e <code>False</code> caso contrario
     */
    public static Boolean hasMethod(Class<?> target, String name, Class<?>... typesParam) {
        return safeGetMethod(target, name, typesParam).isPresent();
    }

    /**
     * Verifica se determinado campo possui uma determinada anotação
     *
     * @param annotation anotação desejada
     * @param f          campo para verificação
     * @return <code>True</code> caso contenha e <code>False</code> caso contrario
     */
    public static Boolean hasAnnotation(Class<? extends Annotation> annotation, Field f) {
        return f.isAnnotationPresent(annotation);
    }

    /**
     * Verifica se determinado método possui determinada anotação
     *
     * @param annotation anotação desejada
     * @param method     método para verificação
     * @return <code>True</code> caso exista e <code>False</code> caso contrario
     */
    public static Boolean hasAnnotation(Class<? extends Annotation> annotation, Method method) {
        return method.isAnnotationPresent(annotation);
    }

    /**
     * Verifica se determinada classe possui determinada anotação
     *
     * @param annotation anotação desejada
     * @param clazz      classe para verificação
     * @return <code>True</code> caso exista e <code>False</code> caso contrario
     */
    public static Boolean hasAnnotation(Class<? extends Annotation> annotation, Class<?> clazz) {
        return clazz.isAnnotationPresent(annotation);
    }

    /**
     * Verifica se determinado campo é acessível
     *
     * @param target objeto para verificação
     * @param f      campo para verificação
     * @return <code>True</code> caso seja acessível e <code>False</code> caso contrario
     */
    public static Boolean canAccess(Object target, Field f) {
        return f.canAccess(target);
    }

    /**
     * Verifica se determinado método é acessível
     *
     * @param target objeto para verificação
     * @param method método para verificação
     * @return <code>True</code> caso seja possível e <code>False</code> caso contrario
     */
    public static Boolean canAccess(Object target, Method method) {
        return method.canAccess(target);
    }

    /**
     * Realiza uma busca de um campo
     *
     * @param target classe para busca
     * @param name   nome do campo
     * @return um {@link Optional} contendo um {@link Field} evitando {@link NullPointerException}
     */
    public static Optional<Field> safeGetField(Class<?> target, String name) {
        try {
            Field f = target.getDeclaredField(name);
            return Optional.of(f);
        } catch (NoSuchFieldException e) {
            return Optional.empty();
        }
    }

    /**
     * Realiza uma busca de um método
     *
     * @param target classe para busca
     * @param name   nome do método
     * @param types  tipos dos parâmetros do método
     * @return um {@link Optional} contendo um {@link Method} evitando {@link NullPointerException}
     */
    public static Optional<Method> safeGetMethod(Class<?> target, String name, Class<?>... types) {
        try {
            Method m = target.getDeclaredMethod(name, types);
            return Optional.of(m);
        } catch (NoSuchMethodException e) {
            return Optional.empty();
        }
    }
}
