package com.altari.spring.ws.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 
 * @author Régis LIMARE
 *
 */
@Configuration
public class WebSecurityConfig  {

    @Bean
    public ApplicationSecurity applicationSecurity() {
        return new ApplicationSecurity();
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Configuration
    protected static class AuthenticationSecurity   extends GlobalAuthenticationConfigurerAdapter {
        @Autowired
        private Environment env;

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            if (env.acceptsProfiles("dev")) {
                auth
                    .inMemoryAuthentication()
                        .withUser("admin")
                            .password("admin")
                            .roles("ADMIN", "USER", "SUPERUSER")
                        .and().withUser("user")
                            .password("user")
                            .roles("USER");
            }
        }
    }

    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {
        @Autowired
        private Environment env;
        
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
            if (env.acceptsProfiles("test")) {
                http
                .authorizeRequests().anyRequest().permitAll();
            }else{
            http
                .authorizeRequests()
                // .anyRequest().permitAll()
                    .antMatchers("/").permitAll()
                    .antMatchers("/docs/**").permitAll()
                    .antMatchers("/login").permitAll()
                    .antMatchers("/api/launchJob").permitAll()
                    .antMatchers("/api/customSearch").permitAll()
                    .antMatchers("/error").permitAll()
                    .antMatchers("/webjars/**").permitAll()
                    .antMatchers("/home").permitAll()
                    .antMatchers("/css/**").permitAll()
                    .antMatchers("/images/**").permitAll()
                    .antMatchers("/resources/**").permitAll()
                    .anyRequest().fullyAuthenticated()
                .and()
                    .formLogin();
            }
        }
    }
}