package com.shankrish.crudpostgresemployee.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shankrish.crudpostgresemployee.config.JwtUtil;
import com.shankrish.crudpostgresemployee.model.User;
import com.shankrish.crudpostgresemployee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Map<String, String> body) {
        String name = body.get("name");
        String email = body.get("email");
        String pass = passwordEncoder.encode( body.get("pass") );

        if(userService.getUserByEmail(email) != null) {
            return new ResponseEntity<>("Email already exists", HttpStatus.CONFLICT);
        } else {
            userService.createUser(User.builder()
                    .name(name).email(email).pass(pass).build());
            return new ResponseEntity<>("User register is success", HttpStatus.CREATED);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Map<String, String> body) throws JsonProcessingException {
        String email = body.get("email");
        String pass = body.get("pass");

        var user = userService.getUserByEmail(email);
        if( user == null ) {
            return new ResponseEntity<>("User not found", HttpStatus.UNAUTHORIZED);
        } else if( !passwordEncoder.matches(pass, user.getPass()) ) {
            return new ResponseEntity<>("Invalid user", HttpStatus.UNAUTHORIZED);
        } else {
            String token = jwtUtil.generateToken(email);
            ObjectMapper mapper = new ObjectMapper();
            return ResponseEntity.ok( mapper.writeValueAsString( Map.of("token", token) ) );
        }

    }

}
