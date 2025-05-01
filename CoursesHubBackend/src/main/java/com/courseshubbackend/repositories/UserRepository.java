package com.courseshubbackend.repositories;


import com.courseshubbackend.pojos.User;

import java.util.Map;

public interface UserRepository {
    User getUserByUsername(String username);
    int countUsers(Map<String, String> params);

}