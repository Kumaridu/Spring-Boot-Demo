package com.innoveller.demo.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        List<GrantedAuthority> adminAuthority = new ArrayList<>();
//        adminAuthority.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//
//        List<GrantedAuthority> userAuthority = new ArrayList<>();
//        userAuthority.add(new SimpleGrantedAuthority("ROLE_USER"));
//
//        User adminUser = new User("Judy", "12345", adminAuthority);
//        User staffUser = new User("Sindhu", "45678", userAuthority);

        UserDetails adminUser = User.withUsername("Judy")
                .password("12345")
                .roles("ADMIN")
                .build();
        UserDetails staffUser = User.withUsername("Sindhu")
                .password("12345")
                .roles("USER")
                .build();


        List<UserDetails> userList = new ArrayList<>();
        userList.add(adminUser);
        userList.add(staffUser);

        Optional<UserDetails> userOptional = userList.stream().filter(user -> user.getUsername().equals(userName)).findFirst();
        if(userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new UsernameNotFoundException("User does not exit");
        }
    }
}
