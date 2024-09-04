package com.gsus.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class FoodSecurity {

@Bean
public UserDetailsManager userDetailsManager(DataSource dataSource)
{
    return new JdbcUserDetailsManager(dataSource);
}

    /*@Bean
    public InMemoryUserDetailsManager userDetailsManager()
    {
        UserDetails SK= User.builder()
                .username("SK")
                .password("{noop}123")
                .roles("WORKER")
                .build();

        UserDetails VJS= User.builder()
                .username("VJS")
                .password("{noop}123")
                .roles("WORKER","MANAGER")
                .build();

                UserDetails JR= User.builder()
                .username("JR")
                .password("{noop}123")
                .roles("WORKER","CEO","ADMIN")
                .build();
        return new InMemoryUserDetailsManager(SK,VJS,JR);
    }*/

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers("/").hasRole("WORKER")
                        .requestMatchers("/manager/**").hasRole("MANAGER")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form ->
                        form
                                .loginPage("/login")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll()
                )
                .logout(logout ->logout.permitAll()
                )
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied")
                        );
        return http.build();
    }
}
