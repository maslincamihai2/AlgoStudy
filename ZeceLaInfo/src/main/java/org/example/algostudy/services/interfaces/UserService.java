package org.example.algostudy.services.interfaces;

import org.example.algostudy.dto.LoginDTO;
import org.example.algostudy.dto.RegistrationDTO;
import org.example.algostudy.models.User;

import java.util.Optional;

public interface UserService {
    User registerUser(RegistrationDTO registrationDTO);
    Optional<String> loginUser(LoginDTO loginDTO);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findById(Long id);
}
