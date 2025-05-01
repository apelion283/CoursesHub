package com.courseshubbackend.services;

import com.courseshubbackend.pojos.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Map;

public interface UserService extends UserDetailsService {
    User getUserByUsername(String username);
    int countUsers(Map<String, String> params);
}
