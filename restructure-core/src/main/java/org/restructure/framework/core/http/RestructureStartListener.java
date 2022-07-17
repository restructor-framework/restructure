package org.restructure.framework.core.http;

import org.restructure.framework.core.ApplicationInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class RestructureStartListener implements ServletContextListener, ApplicationInitializer {

    Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        init();
    }


}
