package com.example.msapiuser.Configuration;

import com.example.msapiuser.Model.UserDto;
import com.example.msapiuser.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<Long> {

    private AuthService userService;

    @Override
    public Optional<Long> getCurrentAuditor() {

        if (SecurityContextHolder.getContext() == null || SecurityContextHolder.getContext().getAuthentication() == null) {
            //if create by system
            return Optional.of(1L);
        } else {
            String username;
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }

            UserDto user = userService.login(username);
            return Optional.of(user != null ? user.getPkId() : 1L);
        }
    }

    @Autowired
    public void setUserService(AuthService userService) {
        this.userService = userService;
    }
}