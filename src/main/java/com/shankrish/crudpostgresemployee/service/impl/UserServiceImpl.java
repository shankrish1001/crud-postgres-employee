package com.shankrish.crudpostgresemployee.service.impl;

import com.shankrish.crudpostgresemployee.model.User;
import com.shankrish.crudpostgresemployee.repository.UserRepository;
import com.shankrish.crudpostgresemployee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElse(null);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElse(null);
    }

}
