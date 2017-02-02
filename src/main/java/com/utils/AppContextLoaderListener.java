package com.utils;

import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

/**
 * Created by Sunil Golla on 1/11/2017.
 */
public class AppContextLoaderListener extends ContextLoaderListener {

    public void contextInitialized(ServletContextEvent event) {
        ServletContext sc = event.getServletContext();
        sc.setInitParameter(CONFIG_LOCATION_PARAM,"com.config.AppConfiguration");
        super.contextInitialized(event);
    }
}
