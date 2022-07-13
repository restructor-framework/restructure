package org.restructure.framework.utils;


import org.apache.commons.lang3.reflect.TypeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Optional;


/**
 * Classe Utilitária para trabalhar com reflexão
 *
 * @author João Henrique
 * @version 1.0.0
 * @since 1.0.0
 */
public class ReflectionUtils {

    private ReflectionUtils() {
    }

    private static final Logger logger = LogManager.getLogger();

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

    /**
     * Obtêm uma classe pelo seu nome
     *
     * @param name nome da classe
     * @return Um {@link Optional} com a classe
     */
    public static Optional<Class<?>> safeGetClassByName(String name) {
        try {
            Class<?> clazz = Class.forName(name);
            return Optional.of(clazz);
        } catch (ClassNotFoundException e) {
            return Optional.empty();
        }
    }

    /**
     * Cria uma instância de uma classe
     *
     * @param target classe para criação
     * @return um {@link Optional} contento o objeto criado
     */
    public static Optional<Object> createInstance(Class<?> target) {
        try {
            return Optional.of(target.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            logger.error("Não Foi possível criar uma instancia de {}", target.getSimpleName());
            return Optional.empty();
        }
    }

    /**
     * Realiza a execução de um método devolvendo seu retorno
     *
     * @param method método a ser executado
     * @param target objeto da execução
     * @param params parâmetros do método
     * @return um {@link Optional} evitando problemas de {@link NullPointerException} em caso do método não retornar nada
     */
    public static Optional<Object> invokeMethod(Method method, Object target, Object... params) {
        try {
            if (method.trySetAccessible()) {
                Object result = method.invoke(target, params);
                if (Objects.nonNull(result)) {
                    return Optional.of(result);
                }
            }
        } catch (InvocationTargetException | IllegalAccessException ignored) {
            logger.error("Não Foi possível executar o método {}", method.getName());
        }
        return Optional.empty();
    }

    /**
     * Obtêm o valor de um campo
     *
     * @param target objeto fonte da execução
     * @param field  campo a ser obtido
     * @return um {@link Optional} em caso de houver algum problema na obtenção do valor evitando {@link  NullPointerException}
     */
    public static Optional<Object> getFieldValue(Object target, Field field) {
        try {
            if (field.trySetAccessible()) {
                return Optional.of(field.get(target));
            }
        } catch (Exception e) {
            logger.error("Não foi possível acessar o campo {}", field.getName());
        }
        return Optional.empty();
    }

    /**
     * Realiza a verificação se
     *
     * @param origin tipo de origem
     * @param dest   tipo de destino
     * @return <code>True</code> caso seja possível a conversão e <code>False</code> caso contrario
     */
    public static boolean canParse(Class<?> origin, Class<?> dest) {
        return TypeUtils.isAssignable(origin, dest);
    }
}
