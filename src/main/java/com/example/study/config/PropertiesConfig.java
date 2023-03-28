package com.example.study.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class PropertiesConfig {
    @Bean
    public PropertiesFactoryBean commonProperties(){
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocations(new ClassPathResource("application.properties"));
        return bean;
    }
}
