package com.example.msapiuser.Configuration;

import com.example.msapiuser.Entity.RoleEntity;
import com.example.msapiuser.Entity.UserEntity;
import com.example.msapiuser.Model.UserDto;
import com.example.msapiuser.Repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("usernameNotFound");
        }

        return new UserPrincipal(user, getAuthorities(user.getRoleList()));
    }

    private static List<GrantedAuthority> getAuthorities(List<RoleEntity> roleList) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleEntity role : roleList) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
}