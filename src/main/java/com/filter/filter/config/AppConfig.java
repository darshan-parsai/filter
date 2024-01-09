package com.filter.filter.config;

import com.filter.filter.repository.UserRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AppConfig {
    private final UserRepo userRepo;

    public AppConfig(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
////        UserDetails userDetails = User.builder().username("darshan").password(passwordEncoder().encode("abc")).roles("ADMIN")
////                .build();
////        return  new InMemoryUserDetailsManager(userDetails);
//        System.out.println("inside the App config -------->");
//        return new UserDetailsServiceImpl(userRepo);
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
