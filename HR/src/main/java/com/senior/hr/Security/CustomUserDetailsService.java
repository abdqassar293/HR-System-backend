package com.senior.hr.Security;

import com.senior.hr.model.Role;
import com.senior.hr.model.UserEntity;
import com.senior.hr.repository.UserEntityRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserEntityRepository userEntityRepository;

    public CustomUserDetailsService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userEntityRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return new User(user.getUsername(), user.getPassword(), List.of(new SimpleGrantedAuthority("gg")));
        //todo change the authority to take the authorities from the database.
    }

    private Collection<GrantedAuthority> mapAuthorityToGrantedAuthority(Role role) {
        return role.getAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName())).collect(Collectors.toList());
    }
}
