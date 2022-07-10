package org.restructure.framework.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Utilitários de reflexão")
class ReflectionUtilsTest {

    private static class ReflectTonClassTests {
        private String nome;
    }

    ReflectTonClassTests tests = new ReflectTonClassTests();

    @Test
    @DisplayName("Existe o campo esperado True")
    void existsFieldExpectTrue() {
        Boolean result = ReflectionUtils.hasField(tests.getClass(), "nome");
        Assertions.assertEquals(Boolean.TRUE, result);
    }

    @Test
    @DisplayName("Não Existe o campo esperado false")
    void notExistsFieldExpectFalse() {
        Boolean result = ReflectionUtils.hasField(tests.getClass(), "nome2");
        Assertions.assertEquals(Boolean.FALSE, result);
    }


}
