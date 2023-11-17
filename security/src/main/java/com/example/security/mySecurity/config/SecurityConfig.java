package com.example.security.mySecurity.config;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CorsFilter corsFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        // 세션 x
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 필터를 통해 항상 corsFilter를 거쳐감
                .addFilter(corsFilter)

                // 폼 로그인이나 기본 http로그인 방식 x
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeRequests()

                .antMatchers("/api/v1/user/**")
                .access("hasRole('Role_User') or hasRole('Role_Manager') or hasRole('Role_Admin')")

                .antMatchers("/api/v1/manager/**")
                .access("hasRole('Role_Manager') or hasRole('Role_Admin')")

                .antMatchers("/api/v1/admin/**")
                .access(" hasRole('Role_Admin')")

                .anyRequest().permitAll()
        ;
    }
}
