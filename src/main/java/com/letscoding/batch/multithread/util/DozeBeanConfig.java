package com.letscoding.batch.multithread.util;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozeBeanConfig {
    @Bean
    public DozerBeanMapper mapper() {
        return new DozerBeanMapper();
    }
}
