package com.example.usermodule.service;

import com.example.usermodule.Repository.DoctorantRepo;
import com.example.usermodule.Repository.UserRepo;
import com.example.usermodule.model.CustomUserDetails;
import com.example.usermodule.model.Doctorant;
import com.example.usermodule.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Supplier<UsernameNotFoundException> s =
                () -> new UsernameNotFoundException(
                        "Problem during authentication!");
        User u = repo
                .findByEmail(username)
                .orElseThrow(s);
        return new CustomUserDetails(u);
    }
}
