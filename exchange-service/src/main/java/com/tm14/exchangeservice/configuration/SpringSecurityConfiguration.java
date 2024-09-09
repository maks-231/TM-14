package com.tm14.exchangeservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
public class SpringSecurityConfiguration {
  @Bean
  public SecurityFilterChain filterChaim(HttpSecurity http) {
    try {
      http
          .authorizeHttpRequests(authorize -> {
            authorize.requestMatchers(antMatcher("/customer-details/**")).permitAll()
                .requestMatchers(antMatcher(HttpMethod.GET, "/currency-exchange/**")).permitAll()
                .anyRequest().permitAll();
          });

      http.csrf().disable();
      http.headers().frameOptions().disable();
      return http.build();
    } catch (Exception e) {
      throw new RuntimeException("Cannot create a security chain");
    }
  }
}
