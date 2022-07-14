package org.restructure.framework.utils;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@DisplayName("Utilitários de reflexão")
@SuppressWarnings("all")
class ReflectionUtilsTest {

    private Logger log = LoggerFactory.getLogger(getClass());


    @Retention(RetentionPolicy.RUNTIME)
    private @interface TesteAnnotation {

    }

    @TesteAnnotation
    private class ReflectionTests {

        @TesteAnnotation
        private String nome;

        public String lastName;

        public ReflectionTests() {
            nome = "Teste";
        }

        @TesteAnnotation
        public void sayHello() {
        }

        public void sayHello(String name) {

        }

        private String sayBy() {
            return "by";
        }

    }

    static class Tests2 {
    }

    ReflectionTests tests = new ReflectionTests();

    @Test
    @DisplayName("Existe o campo esperado True")
    void existsFieldExpectTrue() {
        Boolean result = ReflectionUtils.hasField(tests.getClass(), "nome");
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Não existe o campo esperado False")
    void notExistsFieldExpectFalse() {
        Boolean result = ReflectionUtils.hasField(tests.getClass(), "nome2");
        Assertions.assertFalse(result);
    }

    @DisplayName("Existe o método esperado True")
    @Test
    void existsMethodExpectTrue() {
        Boolean result = ReflectionUtils.hasMethod(tests.getClass(), "sayHello");
        Assertions.assertTrue(result);
    }

    @DisplayName("Existe o método com parâmetros esperado True")
    @Test
    void existMethodWithParamsExpectTrue() {
        Boolean result = ReflectionUtils.hasMethod(tests.getClass(), "sayHello", String.class);
        Assertions.assertTrue(result);
    }

    @Test
    @DisplayName("Não existe o método esperado False")
    void notExistsMethodExpectFalse() {
        Boolean result = ReflectionUtils.hasMethod(tests.getClass(), "sayHello2");
        Assertions.assertFalse(result);
    }

    @DisplayName("Existe anotação no método esperado true")
    @Test
    void existsAnnotationInMethodExpectTrue() throws NoSuchMethodException {
        Method method = tests.getClass().getMethod("sayHello");
        Boolean result = ReflectionUtils.hasAnnotation(TesteAnnotation.class, method);
        Assertions.assertTrue(result);
    }

    @DisplayName("Não existe anotação no método esperado false")
    @Test
    void notExistsAnnotationInMethodExpectFalse() throws NoSuchMethodException {
        Method method = tests.getClass().getMethod("sayHello", String.class);
        Boolean result = ReflectionUtils.hasAnnotation(TesteAnnotation.class, method);
        Assertions.assertFalse(result);
    }

    @DisplayName("Existe Anotação na classe esperado True")
    @Test
    void existsAnnotationInClassExpectTrue() {
        Class<? extends ReflectionTests> clazz = tests.getClass();
        Boolean result = ReflectionUtils.hasAnnotation(TesteAnnotation.class, clazz);
        Assertions.assertTrue(result);
    }

    @DisplayName("Não existe anotação na classe esperado False")
    @Test
    void notExistsAnnotationInClassExpectFalse() {
        Class<Tests2> clazz = Tests2.class;
        Boolean result = ReflectionUtils.hasAnnotation(TesteAnnotation.class, clazz);
        Assertions.assertFalse(result);
    }

    @DisplayName("Existe anotação no campo esperado True ")
    @Test
    void existsAnnotationInFieldExpectTrue() throws NoSuchFieldException {
        Field f = tests.getClass().getDeclaredField("nome");
        Boolean result = ReflectionUtils.hasAnnotation(TesteAnnotation.class, f);
        Assertions.assertTrue(result);
    }

    @DisplayName("Não existe anotação no campo esperado False")
    @Test
    void notExistsAnnotationInFieldExpectFalse() throws NoSuchFieldException {
        Field f = tests.getClass().getDeclaredField("lastName");
        Boolean result = ReflectionUtils.hasAnnotation(TesteAnnotation.class, f);
        Assertions.assertFalse(result);
    }

    @DisplayName("Campo é acessível esperado True")
    @Test
    void fieldAccessibleExpectTrue() throws NoSuchFieldException {
        Field f = tests.getClass().getDeclaredField("lastName");
        Boolean result = ReflectionUtils.canAccess(tests, f);
        Assertions.assertTrue(result);
    }

    @DisplayName("Campo não é acessível esperado false")
    @Test
    void notFieldAccessibleExpectFalse() throws NoSuchFieldException {
        Field f = tests.getClass().getDeclaredField("nome");
        Boolean result = ReflectionUtils.canAccess(tests, f);
        Assertions.assertFalse(result);
    }

    @DisplayName("Método acessível esperado True")
    @Test
    void methodAccessibleExpectTrue() throws NoSuchMethodException {
        Method method = tests.getClass().getDeclaredMethod("sayHello");
        Boolean result = ReflectionUtils.canAccess(tests, method);
        Assertions.assertTrue(result);
    }

    @DisplayName("Método acessível esperado True")
    @Test
    void methodNotAccessibleExpectTrue() throws NoSuchMethodException {
        Method method = tests.getClass().getDeclaredMethod("sayBy");
        Boolean result = ReflectionUtils.canAccess(tests, method);
        Assertions.assertFalse(result);
    }

    @DisplayName("Campo existente esperado um optional com o campo")
    @Test
    void fieldExistsExpectOptionalWithField() {
        Field f = ReflectionUtils.safeGetField(tests.getClass(), "nome").get();
        Assertions.assertNotNull(f);
    }

    @DisplayName("Médodo existe esperado um optional com o método")
    @Test
    void methodExistsExpectOptionalWithMethod() {
        Method method = ReflectionUtils.safeGetMethod(tests.getClass(), "sayHello").get();
        Assertions.assertNotNull(method);
    }

    @DisplayName("Classe existente esperado um optional com a classe")
    @Test
    void classExistsExpectOptionalWithMethod() {
        Class<?> clazz = ReflectionUtils.safeGetClassByName("org.restructure.framework.utils.ReflectionUtils").get();
        Assertions.assertNotNull(clazz);
    }

    @DisplayName("Classe não existente esperado um optional vazio")
    @Test
    void classNotExistsExpectEmptyOptional() {
        Boolean result = ReflectionUtils.safeGetClassByName("org.restructure.framework.utils.ReflectionUtils").isEmpty();
        Assertions.assertFalse(result);
    }

    @DisplayName("Criação de objeto com sucesso")
    @Test
    void createInstanceWithSuccess() {
        Object result = ReflectionUtils.createInstance(Object.class).get();
        Assertions.assertNotNull(result);
    }

    @DisplayName("Execução de metodo com sucesso")
    @Test
    void callMethod() throws NoSuchMethodException {
        Method m = tests.getClass().getDeclaredMethod("sayBy");
        Assertions.assertDoesNotThrow(() -> {
            Optional<Object> result = ReflectionUtils.invokeMethod(m, tests);
        });
    }

    @DisplayName("Obetenção do campo com sucesso")
    @Test
    void getFieldValueWithSuccess() throws NoSuchFieldException {
        Field f = tests.getClass().getDeclaredField("nome");
        Assertions.assertDoesNotThrow(() -> {
            Optional<Object> result = ReflectionUtils.getFieldValue(tests, f);
        });
    }

    @DisplayName("Verifica a possibilidade de conversão de um tipo para outro")
    @Test
    void virifyIfParseTypes(){
       Boolean result = ReflectionUtils.canParse(Integer.class,Number.class);
       Assertions.assertTrue(result);
    }

    @DisplayName("Tipos não compativeis para conversão")
    @Test
    void verifyNotCanParse(){
        Boolean result = ReflectionUtils.canParse(Number.class,Integer.class);
        Assertions.assertFalse(result);
    }
}
