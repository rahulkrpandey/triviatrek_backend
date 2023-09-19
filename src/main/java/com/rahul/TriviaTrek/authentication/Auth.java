package com.rahul.TriviaTrek.authentication;

import com.rahul.TriviaTrek.model.User;
import com.rahul.TriviaTrek.service.AuthService;
import com.rahul.TriviaTrek.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/authentication")
public class Auth {
    @Autowired
    AuthService authService;

    @Autowired
    JwtService jwtService;
    @PostMapping("login")
    private ResponseEntity<String> login(@RequestBody AuthRequestUtil authRequest) {
//        return new ResponseEntity<>("success", HttpStatus.OK);
        return jwtService.gererateToken(authRequest);
    }

    @PostMapping("signup")
    private ResponseEntity<String> signup(@RequestBody User user) {
        return authService.addUser(user);
    }
}
