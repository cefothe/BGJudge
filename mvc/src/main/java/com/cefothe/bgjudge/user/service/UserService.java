package com.cefothe.bgjudge.user.service;

import com.cefothe.bgjudge.user.models.binding.RegisterUserModel;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * This {@link UserService} gives us all needed method to operate with {@link com.cefothe.bgjudge.user.entities.User}
 * Created by cefothe on 02.05.17.
 */
public interface UserService extends UserDetailsService {
    /**
     * With this method we register a {@link com.cefothe.bgjudge.user.entities.User} in database
     * @param registerUserModel {@link RegisterUserModel} contains all necessary information
     */
    void register(RegisterUserModel registerUserModel);

    /**
     * Check if email address is unique
     * @param email email for that we want to check
     * @return if in database exist that email
     */
    boolean checkForUniqueEmail(String email);
}
