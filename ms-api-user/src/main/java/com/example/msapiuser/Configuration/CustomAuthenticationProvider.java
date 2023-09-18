package com.example.msapiuser.Configuration;

import com.example.msapiuser.Exception.CustomAuthException;
import com.example.msapiuser.Service.Impl.AuthServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private PasswordEncoder passwordEncoder;

    private final CustomUserDetailsService userDetailsService;

    public CustomAuthenticationProvider(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserPrincipal userDetails;

        try {
            userDetails = (UserPrincipal) userDetailsService.loadUserByUsername(email);

            if (!getPasswordEncoder().matches(password, userDetails.getPassword())) {
                logger.info("Login Process Fail: incorrectPassword");
                throw new CustomAuthException("incorrectPassword");
            }
            if (!userDetails.isAccountNonExpired()) {
                logger.info("Login Process Fail: accountExpired");
                throw new CustomAuthException("accountExpired");
            }
            if (!userDetails.isEnabled()) {
                logger.info("Login Process Fail: accountNotActive");
                throw new CustomAuthException("accountNotActive");
            }
        } catch (Exception e) {
            throw e;
        }

        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    public PasswordEncoder getPasswordEncoder() {
        if (passwordEncoder == null) {
            passwordEncoder = new BCryptPasswordEncoder();
        }
        return passwordEncoder;
    }
}