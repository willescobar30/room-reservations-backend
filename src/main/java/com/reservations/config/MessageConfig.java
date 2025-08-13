package com.reservations.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class MessageConfig {
    @Bean
    public MessageSource messageResource(){

        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        return messageSource;

    }
    @Bean
    public LocalValidatorFactoryBean getValidator(){
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageResource());
        return bean;
    }

    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver localeResolver =new SessionLocaleResolver();
        localeResolver.setDefaultLocale(Locale.ROOT);
        return localeResolver;
    }
}
