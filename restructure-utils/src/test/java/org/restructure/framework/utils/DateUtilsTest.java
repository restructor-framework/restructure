package org.restructure.framework.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.time.LocalDateTime;


@DisplayName("Utilitários de datas")
class DateUtilsTest {

    @Test
    @DisplayName("LocalDate Java Time para Joda Time com os valores corretos")
    void toJodaLocalDate() {

        LocalDate local = LocalDate.now();
        org.joda.time.LocalDate result = DateUtils.toJodaLocalDate(local);

        Assertions.assertEquals(local.getYear(), result.getYear());
        Assertions.assertEquals(local.getMonthValue(), result.getMonthOfYear());
        Assertions.assertEquals(local.getDayOfYear(), result.getDayOfYear());
    }

    @Test
    @DisplayName("LocalDate Joda Time para Java Time com os valores corretos")
    void toJavaTimeLocalDate() {
        org.joda.time.LocalDate date = org.joda.time.LocalDate.now();
        LocalDate result = DateUtils.toJavaTimeLocalDate(date);
        Assertions.assertEquals(date.getYear(), result.getYear());
        Assertions.assertEquals(date.getMonthOfYear(), result.getMonthValue());
    }

    @Test
    @DisplayName("LocalDateTime Java Time para Joda Time com os valores corretos")
    void toJodaLocalDateTime() {
        LocalDateTime local = LocalDateTime.now();
        org.joda.time.LocalDateTime result = DateUtils.toJodaLocalDateTime(local);

        Assertions.assertEquals(local.getYear(), result.getYear());
        Assertions.assertEquals(local.getMonthValue(), result.getMonthOfYear());
        Assertions.assertEquals(local.getDayOfMonth(), result.getDayOfMonth());
        Assertions.assertEquals(local.getHour(), result.getHourOfDay());
        Assertions.assertEquals(local.getMinute(), result.getMinuteOfHour());
        Assertions.assertEquals(local.getSecond(), result.getSecondOfMinute());
    }

    @Test
    @DisplayName("LocalDateTime Java Time para Joda Time com os valores corretos")
    void toJavaLocalDateTime() {
        org.joda.time.LocalDateTime local = org.joda.time.LocalDateTime.now();
        LocalDateTime result = DateUtils.toJavaLocalDateTime(local);

        Assertions.assertEquals(local.getYear(), result.getYear());
        Assertions.assertEquals(local.getMonthOfYear(), result.getMonthValue());
        Assertions.assertEquals(local.getDayOfMonth(), result.getDayOfMonth());
        Assertions.assertEquals(local.getHourOfDay(), result.getHour());
        Assertions.assertEquals(local.getMinuteOfHour(), result.getMinute());
        Assertions.assertEquals(local.getSecondOfMinute(), result.getSecond());
    }
}
