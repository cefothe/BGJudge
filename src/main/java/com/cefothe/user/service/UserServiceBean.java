package com.cefothe.user.service;

import com.cefothe.user.entities.User;
import com.cefothe.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by cefothe on 02.05.17.
 */
@Service
public class UserServiceBean implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceBean(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalidate credential");
        }
        return null;
    }
}
