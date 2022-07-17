package org.restructure.framework.core;

/**
 * Definição de contrato para inicialização da aplicação
 *
 * @author João Henrique
 */
public interface ApplicationInitializer {

    void initCache();

    void initNotify();

    void initPersistence();
}
