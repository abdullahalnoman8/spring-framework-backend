package com.abdullah.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {
    private String manager = "MANAGER";
    private String employee = "EMPLOYEE";
    private String admin = "ADMIN";


    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        String password = "{noop}test123";
        UserDetails john = User.builder()
                .username("john")
                .password(password)
                .roles(employee)
                .build();


        UserDetails mary = User.builder()
                .username("mary")
                .password(password)
                .roles(employee, manager)
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password(password)
                .roles(employee, manager, admin)
                .build();

        return new InMemoryUserDetailsManager(john, mary, susan);
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
