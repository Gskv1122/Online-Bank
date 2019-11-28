package com.userFront.service.impl;

import com.userFront.dao.RoleDao;
import com.userFront.dao.UserDao;
import com.userFront.domain.User;
import com.userFront.domain.security.UserRole;
import com.userFront.service.AccountService;
import com.userFront.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private AccountService accountService;

    @Override
    public boolean checkUserExists(String username, String email) {
        if (checkUsernameExists(username) || checkEmailExists(email)) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public void saveUser(User user) {
        userDao.save(user);
    }


    public User create(User user, Set<UserRole> userRoles) {
        User localUser = userDao.findByUsername(user.getUsername());

        if(localUser!=null){
            LOG.info("User with username {} already exists. Nothing will be done. ",user.getUsername());
        }else {
            String encryptedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encryptedPassword);

            for(UserRole ur : userRoles){
                roleDao.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);

            user.setPrimaryAccount(accountService.createPrimaryAccount());
            user.setSavingsAccount(accountService.createSavingsAccount());

            localUser = userDao.save(user);
        }
        return localUser;
    }

    @Override
    public User findByUsername(String username){
        return userDao.findByUsername(username);
    }

    @Override
    public User findByEmail(String email){
        return userDao.findByEmail(email);
    }

    @Override
    public boolean checkEmailExists(String email) {
        if(null!=findByEmail((email))){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkUsernameExists(String username) {
        if (null != findByUsername(username)) {
            return true;
        }
        return false;
    }
}
