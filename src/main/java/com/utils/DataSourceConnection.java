package com.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Sunil Golla on 1/11/2017.
 */
public class DataSourceConnection extends org.apache.tomcat.jdbc.pool.DataSource{

    final Logger logger = LoggerFactory.getLogger(DataSourceConnection.class);

    public static final String MYSQL_ADDRESS_PROP_NAME ="JDBC_CONNECTION_STRING";
    public static final String JDBC_MAX_ACTIVE_CONNECTIONS ="JDBC_MAX_ACTIVE_CONNECTIONS";

    private static final String URL = "jdbc:mysql://localhost:3306/globex?user=dbuser&password=dbpassword&useUnicode=true&characterEncoding=utf-8";

    public void configureUrl() {
        super.setUrl(URL);
        String configUrl = System.getProperty(MYSQL_ADDRESS_PROP_NAME);
        if(!StringUtils.isEmpty(configUrl)){
            super.setUrl(configUrl);
        }
        String maxActiveConnections = System.getProperty(JDBC_MAX_ACTIVE_CONNECTIONS);
        if(!StringUtils.isEmpty(maxActiveConnections)){
            try{
                logger.info("Setting mysql max active connections to "+maxActiveConnections);
                super.setMaxActive(new Integer(maxActiveConnections).intValue());
            } catch (NumberFormatException e){
                logger.error("Could not parse JDBC_MAX_ACTIVE_CONNECTIONS property with value : \""+maxActiveConnections+"\"", e);
                super.setMaxActive(100);
                logger.info("Setting mysql max active connections to default value.");
            }
        } else {
            super.setMaxActive(100);
        }

        //Attempt to resolve perf issues.
        super.setRemoveAbandoned(true);
        super.setRemoveAbandonedTimeout(60);
        super.setLogAbandoned(true);
    }



}
