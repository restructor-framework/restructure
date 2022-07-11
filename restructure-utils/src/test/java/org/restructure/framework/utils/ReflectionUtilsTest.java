package org.restructure.framework.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@DisplayName("Utilitários de reflexão")
class ReflectionUtilsTest {

    @Retention(RetentionPolicy.RUNTIME)
    private @interface TesteAnnotation {

    }

    @TesteAnnotation
    private static class ReflectTonClassTests {

        @TesteAnnotation
        private String nome;

        private String lastName;

        @TesteAnnotation
        public void sayHello() {
        }

        public void sayHello(String name) {

        }

    }

    static class Tests2 {
    }

    ReflectTonClassTests tests = new ReflectTonClassTests();

    @Test
    @DisplayName("Existe o campo esperado True")
    void existsFieldExpectTrue() {
        Boolean result = ReflectionUtils.hasField(tests.getClass(), "nome");
        Assertions.assertEquals(Boolean.TRUE, result);
    }

    @Test
    @DisplayName("Não existe o campo esperado False")
    void notExistsFieldExpectFalse() {
        Boolean result = ReflectionUtils.hasField(tests.getClass(), "nome2");
        Assertions.assertEquals(Boolean.FALSE, result);
    }

    @DisplayName("Existe o método esperado True")
    @Test
    void existsMethodExpectTrue() {
        Boolean result = ReflectionUtils.hasMethod(tests.getClass(), "sayHello");
        Assertions.assertEquals(Boolean.TRUE, result);
    }

    @DisplayName("Existe o método com parâmetros esperado True")
    @Test
    void existMethodWithParamsExpectTrue() {
        Boolean result = ReflectionUtils.hasMethod(tests.getClass(), "sayHello", String.class);
        Assertions.assertEquals(Boolean.TRUE, result);
    }

    @Test
    @DisplayName("Não existe o método esperado False")
    void notExistsMethodExpectFalse() {
        Boolean result = ReflectionUtils.hasMethod(tests.getClass(), "sayHello2");
        Assertions.assertEquals(Boolean.FALSE, result);
    }

    @DisplayName("Existe anotação no método esperado true")
    @Test
    void existsAnnotationInMethodExpectTrue() throws NoSuchMethodException {
        Method method = tests.getClass().getMethod("sayHello");
        Boolean result = ReflectionUtils.hasAnnotation(TesteAnnotation.class, method);
        Assertions.assertEquals(Boolean.TRUE, result);
    }

    @DisplayName("Não existe anotação no método esperado false")
    @Test
    void notExistsAnnotationInMethodExpectFalse() throws NoSuchMethodException {
        Method method = tests.getClass().getMethod("sayHello", String.class);
        Boolean result = ReflectionUtils.hasAnnotation(TesteAnnotation.class, method);
        Assertions.assertEquals(Boolean.FALSE, result);
    }

    @DisplayName("Existe Anotação na classe esperado True")
    @Test
    void existsAnnotationInClassExpectTrue() {
        Class<? extends ReflectTonClassTests> clazz = tests.getClass();
        Boolean result = ReflectionUtils.hasAnnotation(TesteAnnotation.class, clazz);
        Assertions.assertEquals(Boolean.TRUE, result);
    }

    @DisplayName("Não existe anotação na classe esperado False")
    @Test
    void notExistsAnnotationInClassExpectFalse() {
        Class<Tests2> clazz = Tests2.class;
        Boolean result = ReflectionUtils.hasAnnotation(TesteAnnotation.class, clazz);
        Assertions.assertEquals(Boolean.FALSE, result);
    }

    @DisplayName("Existe anotação no campo esperado True ")
    @Test
    void existsAnnotationInFieldExpectTrue() throws NoSuchFieldException {
        Field f = tests.getClass().getDeclaredField("nome");
        Boolean result = ReflectionUtils.hasAnnotation(TesteAnnotation.class, f);
        Assertions.assertEquals(Boolean.TRUE, result);
    }

    @DisplayName("Não existe anotação no campo esperado False")
    @Test
    void notExistsAnnotationInFieldExpectFalse() throws NoSuchFieldException {
        Field f = tests.getClass().getDeclaredField("lastName");
        Boolean result = ReflectionUtils.hasAnnotation(TesteAnnotation.class, f);
        Assertions.assertEquals(Boolean.FALSE, result);
    }
}
