package org.geekhub.kukotin.coursework.config;

import org.springframework.context.annotation.Configuration;

//import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http, DataSource dataSource) throws Exception {
//        var authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
//
//        authenticationManagerBuilder
//            .inMemoryAuthentication()
//            .withUser("admin")
//            .password("{bcrypt}$2a$10$rkWfnHrSpo0JyNBH4tHRDOeuZACtCU5v4sCQpleWl4P41YuYqQMjC") //admin
//            .roles("ADMIN");
//
//        authenticationManagerBuilder
//            .jdbcAuthentication()
//            .dataSource(dataSource)
//            .withUser("user")
//            .password("{bcrypt}$2a$10$GlpFG1Ml3U9AvkOu0D1B9ufnoquX5xqCR/NHaMfBZliYgPa8/e5sK")
//            .roles("USER");
//
//        return authenticationManagerBuilder.build();
//    }
}
