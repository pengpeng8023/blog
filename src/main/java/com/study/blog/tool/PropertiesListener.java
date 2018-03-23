package com.study.blog.tool;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * Describe:
 * Created by pengp on 2018/2/6.
 */
public class PropertiesListener implements ApplicationListener<ApplicationStartingEvent> {
    private String propertyFileName;
    public PropertiesListener(String propertyFileName){
        this.propertyFileName = propertyFileName;
    }
    @Override
    public void onApplicationEvent(ApplicationStartingEvent applicationStartingEvent) {
        PropertiesListenerConfig.loadAllProperties(propertyFileName);
    }
}
