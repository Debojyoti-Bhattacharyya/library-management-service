package com.example.librarymanagementservice.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {
        @Bean
        SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
                http.authorizeExchange(authorize -> authorize.anyExchange().permitAll())
                                .httpBasic(withDefaults());
                return http.build();
        }

        @SuppressWarnings("deprecation")
        @Bean
        MapReactiveUserDetailsService userDetailsService() {
                UserDetails user = User.withDefaultPasswordEncoder()
                                .username("user")
                                .password("password")
                                .roles("USER")
                                .build();
                UserDetails admin = User.withDefaultPasswordEncoder()
                                .username("admin")
                                .password("password")
                                .roles("ADMIN", "USER")
                                .build();
                return new MapReactiveUserDetailsService(user, admin);
        }
}
