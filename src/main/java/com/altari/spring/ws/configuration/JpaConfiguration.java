package com.altari.spring.ws.configuration;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author Régis LIMARE
 *
 */
@Configuration
@EnableJpaRepositories(basePackages = { "com.altari.spring.ws.repository" })
@EntityScan(basePackages = { "com.altari.spring.ws.domain" })
@EnableTransactionManagement(proxyTargetClass=false)
public class JpaConfiguration {

}
