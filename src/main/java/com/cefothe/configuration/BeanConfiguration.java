package com.cefothe.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by cefothe on 02.05.17.
 */
@Configuration
public class BeanConfiguration {

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper createModelMapper(){
        return new ModelMapper();
    }

}
