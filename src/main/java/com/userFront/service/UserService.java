package com.userFront.service;

import com.userFront.domain.User;
import com.userFront.domain.security.UserRole;

import java.util.Set;

public interface UserService {
    boolean checkUserExists(String username, String email);

    boolean checkEmailExists(String email);

    boolean checkUsernameExists(String username);

    User findByUsername(String username);
    User findByEmail(String email);
    void saveUser(User user);

    User create(User user, Set<UserRole> userRoles);
}
