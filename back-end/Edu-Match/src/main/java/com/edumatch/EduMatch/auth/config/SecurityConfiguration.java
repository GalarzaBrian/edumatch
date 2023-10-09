package com.edumatch.EduMatch.auth.config;

import com.edumatch.EduMatch.auth.filter.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    private final JwtRequestFilter jwtRequestFilter;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private static final String ROLE_ADMIN = "ADMIN";
    private static final String ROLE_USER = "USER";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/auth/register").permitAll()
                .antMatchers("/h2-console/*").permitAll()
                .antMatchers("/auth/me").permitAll()
                .antMatchers("/storage/*").hasRole(ROLE_ADMIN)

                //Users_ADMIN
                .antMatchers(HttpMethod.GET, "/users").hasRole(ROLE_ADMIN)
                .antMatchers(HttpMethod.PUT, "/users/{id}").hasAnyRole(ROLE_ADMIN, ROLE_USER)
                .antMatchers(HttpMethod.GET, "/users/{id}").hasAnyRole(ROLE_ADMIN, ROLE_USER)
                .antMatchers(HttpMethod.DELETE, "/users/{id}").hasRole(ROLE_USER)

                .anyRequest().authenticated()
                .and().exceptionHandling()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

}