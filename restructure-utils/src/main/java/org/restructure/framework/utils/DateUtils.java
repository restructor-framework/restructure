package org.restructure.framework.utils;


import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Classe Utilitária para trabalhar com datas
 *
 * @author João Henrique
 * @version 1.0.0
 * @since 1.0.0
 */
public class DateUtils {

    private DateUtils() {
    }

    /**
     * Transforma um {@link  java.time.LocalDate} em um {@link LocalDate}
     *
     * @param localDate data a ser convertida
     * @return um {@link LocalDate}
     */
    public static LocalDate toJodaLocalDate(java.time.LocalDate localDate) {
        return new LocalDate(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
    }

    /**
     * Transforma um {@link LocalDate} em um {@link java.time.LocalDate}
     *
     * @param localDate data a ser convertida
     * @return um {@link java.time.LocalDate}
     */
    public static java.time.LocalDate toJavaTimeLocalDate(LocalDate localDate) {
        return java.time.LocalDate.of(localDate.getYear(), localDate.getMonthOfYear(), localDate.getDayOfMonth());
    }

    /**
     * Transforma uma {@link java.time.LocalDateTime} em {@link LocalDateTime}
     *
     * @param localDateTime data para conversão
     * @return um {@link LocalDateTime}
     */
    public static LocalDateTime toJodaLocalDateTime(java.time.LocalDateTime localDateTime) {
        Long timeMills = getJavaLocalDateTimeMilli(localDateTime);
        return new LocalDateTime(timeMills);
    }

    /**
     * Obtêm o valor em milissegundos de uma {@link java.time.LocalDateTime}
     *
     * @param localDateTime data para obtenção
     * @return um {@link Long} com a representação da data em milissegundos
     */
    private static Long getJavaLocalDateTimeMilli(java.time.LocalDateTime localDateTime) {
        ZonedDateTime zoned = localDateTime.atZone(ZoneId.systemDefault());
        Instant instant = zoned.toInstant();
        return instant.toEpochMilli();
    }

    /**
     * Transforma uma {@link LocalDateTime} em uma {@link java.time.LocalDateTime}
     *
     * @param localDateTime data para conversão
     * @return um {@link }
     */
    public static java.time.LocalDateTime toJavaLocalDateTime(LocalDateTime localDateTime) {
        return java.time.LocalDateTime.ofInstant(localDateTime.toDate().toInstant(), ZoneId.systemDefault());
    }


}
