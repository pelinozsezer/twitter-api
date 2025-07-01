package com.workintech.twitter_clone_api.config;

import com.workintech.twitter_clone_api.entity.Role;
import com.workintech.twitter_clone_api.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleDataLoader {

    private final RoleRepository roleRepository;

    @PostConstruct
    public void init() {
        if (roleRepository.findByAuthority("USER").isEmpty()) {
            Role userRole = new Role();
            userRole.setAuthority("USER");
            roleRepository.save(userRole);
        }

        if (roleRepository.findByAuthority("ADMIN").isEmpty()) {
            Role adminRole = new Role();
            adminRole.setAuthority("ADMIN");
            roleRepository.save(adminRole);
        }
    }
}
