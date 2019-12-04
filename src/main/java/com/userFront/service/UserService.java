package com.userFront.service;

import com.userFront.domain.User;
import com.userFront.domain.security.UserRole;

import java.util.List;
import java.util.Set;

public interface UserService {
    boolean checkUserExists(String username, String email);

    boolean checkEmailExists(String email);

    boolean checkUsernameExists(String username);

    User findByUsername(String username);
    User findByEmail(String email);
    void saveUser(User user);

    User create(User user, Set<UserRole> userRoles);

    List<User> findUserList();

    void enableUser(String username);

    void disableUser(String username);
}
