package com.courseshubbackend.services;

import com.courseshubbackend.pojos.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User getUserByUsername(String username);
}
