package com.cefothe.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by cefothe on 02.05.17.
 */
@Configuration
public class PasswordConfiguration {

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder(){
        return  new BCryptPasswordEncoder();
    }
}
