package com.example.study.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class PropertiesConfig {
    @Bean
    public PropertiesFactoryBean commonProperties(){// jsp에 application.properties key, value 값 전송
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocations(new ClassPathResource("application.properties"));
        return bean;
    }//commonProperties()
}//class PropertiesConfig
