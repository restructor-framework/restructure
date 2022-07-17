package org.restructure.framework.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Definição de contrato para inicialização da aplicação
 *
 * @author João Henrique
 */
public interface ApplicationInitializer extends Initializer {

    Logger logger = LoggerFactory.getLogger(ApplicationInitializer.class);

    default void initCache() {
        logger.info("Init Cache");
    }

    default void initNotify() {
        logger.info("Notificação Iniciada");
    }

    default void initPersistence() {
        logger.info("Persistência Iniciada");
    }


    default void init() {
        initCache();
        initPersistence();
        initNotify();
    }
}
