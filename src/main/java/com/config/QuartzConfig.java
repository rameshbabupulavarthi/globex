package com.config;

import com.utils.DataSourceConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Sunil Golla on 1/11/2017.
 */

@Configuration
public class QuartzConfig {

    final Logger logger = LoggerFactory.getLogger(QuartzConfig.class);

    private static final String SCHEDULER_NAME = "G_QUARTZ_SCHEDULER";
    public static final String JOB_GROUP = "SPRING3-QUARTZ";
    public static final String JOB_NAME_PREFIX = "createJobDetailFactoryBeanFor";

    @Autowired
    private DataSourceConnection dataSource;

    @Autowired
    private JpaTransactionManager transactionManager;

    @Autowired
    private ApplicationContext applicationContext;

    @Value("${cron.Job.time}")
    private String cronJob;

    private Properties getProperties(){
        Properties prop = new Properties();
        try {
            prop.load(this.getClass().getClassLoader().getResourceAsStream("quartz.properties"));
        }
        catch (IOException ex) {
            logger.warn("Cannot load quartz.properties.");
//			ex.printStackTrace();
        }
        return prop;
    }
}
