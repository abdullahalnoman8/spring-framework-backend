package com.abdullah.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    private String manager = "MANAGER";
    private String employee = "EMPLOYEE";
    private String admin = "ADMIN";

    // add suport for JDBC ... no more hard coded users
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        // this is for using default user details table
        // return new JdbcUserDetailsManager(dataSource);

        // custom user and authorities table
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        // define query to retrieve user by username
        userDetailsManager.setUsersByUsernameQuery(
                "select user_id, pw, active from members where user_id=?"
        );

        // define query to retrieve the authorities/roles by username

        userDetailsManager.setAuthoritiesByUsernameQuery(
                "select user_id, role from roles where user_id=?"
        );
        return userDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String employeeEndPoint = "/api/employees";

        http.authorizeHttpRequests(configurer -> configurer
                .requestMatchers(HttpMethod.GET, employeeEndPoint).hasRole(employee)
                .requestMatchers(HttpMethod.GET, employeeEndPoint+ "/**").hasRole(employee)
                .requestMatchers(HttpMethod.POST, employeeEndPoint).hasRole(manager)
                .requestMatchers(HttpMethod.PUT, employeeEndPoint).hasRole(manager)
                .requestMatchers(HttpMethod.DELETE, employeeEndPoint+"/**").hasRole(admin));
        // use HTTP Basic authentication
        http.httpBasic(Customizer.withDefaults());

        // disable Cross Site Request Forgery (CSRF)
        // in general, not required for stateless REST API's that use POST, PUT, DELETE and/or PATCH
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}