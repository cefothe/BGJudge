package com.cefothe.user.service;

import com.cefothe.user.models.binding.RegisterUserModel;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by cefothe on 02.05.17.
 */
public interface UserService extends UserDetailsService {
    void register(RegisterUserModel registerUserModel);
}
