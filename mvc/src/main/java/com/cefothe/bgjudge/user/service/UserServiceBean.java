package com.cefothe.bgjudge.user.service;

import com.cefothe.bgjudge.user.entities.Role;
import com.cefothe.bgjudge.user.entities.User;
import com.cefothe.bgjudge.user.entities.UserInformation;
import com.cefothe.bgjudge.user.models.binding.RegisterUserModel;
import com.cefothe.bgjudge.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@inheritDoc}
 */
@Service
@Transactional
public class UserServiceBean implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleService roleService;

    @Autowired
    public UserServiceBean(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, RoleService roleService) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.roleService = roleService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalidate credential");
        }
        return user ;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void register(RegisterUserModel registerUserModel) {
        // TODO : register use model mapper
        String encodePassword = bCryptPasswordEncoder.encode(registerUserModel.getPassword());
        Role userRole = this.roleService.findByAuthority("");
        UserInformation userInformation = new UserInformation(registerUserModel.getFirstName(),registerUserModel.getLastName(),registerUserModel.getEmail());
        User user = new User(registerUserModel.getUsername(),encodePassword,userInformation,userRole);

        this.userRepository.save(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean checkForUniqueEmail(String email) {
        return userRepository.existsByUserInformationEmail(email);
    }
}
