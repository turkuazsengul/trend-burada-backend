package com.example.msapiuser.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableTransactionManagement
public class PersistenceContext {

//    @Bean
//    public AuditorAware<Long> auditorAware() {
//        return new AuditorAwareImpl();
//    }
}