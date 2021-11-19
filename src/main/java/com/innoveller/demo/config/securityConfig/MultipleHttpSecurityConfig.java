package com.innoveller.demo.config.securityConfig;

import com.innoveller.demo.services.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class MultipleHttpSecurityConfig {
    private static final String API_KEY_AUTH_HEADER_NAME = "Authorization";

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Configuration
    @Order(1)
    public class JWTSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected  void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            ApiKeyAuthFilter filter = new ApiKeyAuthFilter(API_KEY_AUTH_HEADER_NAME);
            filter.setAuthenticationManager(new ApiKeyAuthManager());

            http.antMatcher("/api/**").
                    cors().and().
                    csrf().
                    disable().
                    sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                    and()
                    .addFilter(filter)
                    .authorizeRequests()
                    .anyRequest()
                    .authenticated();
        }

        @Bean
        public PasswordEncoder getPasswordEncoder() {
            return NoOpPasswordEncoder.getInstance();
        }


        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }
    }

    @Configuration
    public class FormLoginConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .anyRequest().authenticated().and().formLogin();
        }
    }
}
