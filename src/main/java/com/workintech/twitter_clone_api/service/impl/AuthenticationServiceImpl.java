package com.workintech.twitter_clone_api.service.impl;

import com.workintech.twitter_clone_api.entity.Role;
import com.workintech.twitter_clone_api.entity.User;
import com.workintech.twitter_clone_api.repository.RoleRepository;
import com.workintech.twitter_clone_api.repository.UserRepository;
import com.workintech.twitter_clone_api.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(String username, String email, String password) {
        if (userRepository.findUserByEmail(email).isPresent()) {
            throw new IllegalArgumentException("User already exists with email: " + email);
        }

        Role userRole = roleRepository.findByAuthority("USER")
                .orElseThrow(() -> new RuntimeException("Default role USER not found"));

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRoles(Set.of(userRole));

        return userRepository.save(user);
    }
}
