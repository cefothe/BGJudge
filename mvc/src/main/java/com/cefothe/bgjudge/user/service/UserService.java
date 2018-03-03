package com.cefothe.bgjudge.user.service;

import com.cefothe.bgjudge.user.models.binding.RegisterUserModel;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by cefothe on 02.05.17.
 */
public interface UserService extends UserDetailsService {
    void register(RegisterUserModel registerUserModel);

}
