package com.example.school_management.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {


    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        JdbcUserDetailsManager theUserDetailsManager = new JdbcUserDetailsManager(dataSource);


        //define query to get user by username
        theUserDetailsManager
                .setUsersByUsernameQuery("select email, password, active from member where email=?"); //regular sql

        //define query to find authorities/roles by username
        theUserDetailsManager
                .setAuthoritiesByUsernameQuery("select email, role from member where email=?"); //regular sql
        // ? will be passed by login form

        return theUserDetailsManager;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/StudentCreateAccount", "/TeacherCreateAccount", "/createStudent", "/createTeacher").permitAll()
                                .requestMatchers("/").hasAnyRole("STUDENT", "TEACHER", "ADMIN")
                                  .anyRequest().authenticated()
                ).exceptionHandling(configurer ->
                        configurer
                                .accessDeniedPage("/access-denied") //authorization error, or user can't access this page
                )
                .formLogin(form ->
                        form
                                .loginPage("/showLoginPage")//custom form login (url) (get mapping)
                                .loginProcessingUrl("/authenticateTheUser")//login processing url (url to authenticate the user)
                                // no control request mapping required for the processing url , we get it for free
                                .permitAll() //everyone can access this without having to  br logged in

                )
                .logout(logout -> logout.permitAll()
                );

        return http.build();
    }


}
