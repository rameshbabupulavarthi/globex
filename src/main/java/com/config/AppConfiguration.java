package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Sunil Golla on 1/11/2017.
 */

@Configuration
@ImportResource({"classpath*:globex.xml"})
@Import({QuartzConfig.class})
public class AppConfiguration {
}
