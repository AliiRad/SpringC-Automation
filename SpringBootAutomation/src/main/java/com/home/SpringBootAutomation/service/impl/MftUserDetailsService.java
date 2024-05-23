package com.home.SpringBootAutomation.service.impl;

import com.home.SpringBootAutomation.model.MftUserDetails;
import com.home.SpringBootAutomation.model.User;
import com.home.SpringBootAutomation.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class MftUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public MftUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsernameAndDeletedFalse(username);
        user.orElseThrow(
                () -> new UsernameNotFoundException("User not found !!!")
        );
        return new MftUserDetails(user.get());
    }
}
