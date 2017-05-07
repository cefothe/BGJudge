package com.cefothe.common.component;

import com.cefothe.bgjudge.user.entities.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by cefothe on 05.05.17.
 */
@Component
public class AuthenticationFacade implements IAuthenticationFacade {

    @Override
    public User getUser() {
        return  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
