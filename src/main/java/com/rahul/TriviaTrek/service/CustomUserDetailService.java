package com.rahul.TriviaTrek.service;

import com.rahul.TriviaTrek.config.CustomUserDetails;
import com.rahul.TriviaTrek.dao.UserDao;
import com.rahul.TriviaTrek.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> optionalUser = Optional.ofNullable(userDao.findByEmail(username));
        Optional<User> optionalUser = userDao.findByEmail(username);
        System.out.println("here" + username);
        if (!optionalUser.isEmpty()) {
            System.out.println("here");
            System.out.println(optionalUser.get());
        }
        return optionalUser.map(CustomUserDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException(username + " not found!"));
    }
}
