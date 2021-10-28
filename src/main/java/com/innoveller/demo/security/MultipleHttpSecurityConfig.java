package com.innoveller.demo.security;

import com.innoveller.demo.jwtconfig.JwtRequestFilter;
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
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

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
//            http.csrf().disable()
//                    .authorizeRequests().antMatchers("/api/authenticate").permitAll()
//                    .antMatchers("/api/**")
//                    .authenticated();
//
//            http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

            http.csrf().disable()
                    .antMatcher("/api/**")
                    .authorizeRequests().antMatchers("/api/authenticate").permitAll().and()
                    .authorizeRequests(authorize -> authorize.anyRequest().authenticated())
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);;

            http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);


//         http.csrf().disable().antMatcher("/api/**")
//                 .authorizeRequests(authorize -> {
//                     authorize.antMatchers("/api/authenticate").permitAll();
//                     authorize.anyRequest().authenticated();
//                 })
//                 .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
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
