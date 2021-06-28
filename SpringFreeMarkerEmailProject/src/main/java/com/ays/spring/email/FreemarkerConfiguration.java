package com.ays.spring.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

import java.io.IOException;

//@Configuration : Config. işlemleri için

@Configuration
public class FreemarkerConfiguration {

//@Bean : Application contexte return tipinde belirtilen tipte bir objenin bean olarak ekleneceğini belirtir
	
    @Bean(name="emailconfiguration")
    public FreeMarkerConfigurationFactoryBean getFreeMarkerConfiguration(ResourceLoader resourceLoader) {
        FreeMarkerConfigurationFactoryBean freemarker_factory_bean = new FreeMarkerConfigurationFactoryBean();
        freemarker_factory_bean.setTemplateLoaderPath("classpath:/templates/");
        return freemarker_factory_bean;
    }
}
