package com.shankrish.crudpostgresemployee.service;

import com.shankrish.crudpostgresemployee.model.User;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id);
    User getUserByEmail(String email);
}
