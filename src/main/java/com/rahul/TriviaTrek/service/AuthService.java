package com.rahul.TriviaTrek.service;

import com.rahul.TriviaTrek.dao.UserDao;
import com.rahul.TriviaTrek.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    UserDao userDao;
    @Autowired
    PasswordEncoder encoder;
    public ResponseEntity<String> addUser(User user) {
        try {
            user.setPassword(encoder.encode(user.getPassword()));
            userDao.save(user);
            return new ResponseEntity<>("User Added Successfully!", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("User Could not added!", HttpStatus.BAD_REQUEST);
        }
    }
}
