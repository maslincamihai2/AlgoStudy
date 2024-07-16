package org.example.algostudy.controllers;

import org.example.algostudy.dto.CategoryPointsDTO;
import org.example.algostudy.dto.LoginDTO;
import org.example.algostudy.dto.RegistrationDTO;
import org.example.algostudy.models.User;
import org.example.algostudy.security.JwtTokenUtil;
import org.example.algostudy.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationDTO registrationDTO){
        User user = userService.registerUser(registrationDTO);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO){
        Optional<String> response = userService.loginUser(loginDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/id")
    public ResponseEntity<?> getUserId(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        Optional<String> validatedEmail = jwtTokenUtil.validateToken(token);

        if (validatedEmail.isPresent()) {
            Optional<User> userOptional = userService.findByEmail(validatedEmail.get());
            if (userOptional.isPresent()) {
                return ResponseEntity.ok(userOptional.get().getId());
            }

        }
        return ResponseEntity.status(401).body("Unauthorized");
    }

    @GetMapping("/{email}")
    public Optional<User> getUserByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }
}
