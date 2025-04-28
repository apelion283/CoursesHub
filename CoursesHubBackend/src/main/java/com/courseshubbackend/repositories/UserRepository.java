package com.courseshubbackend.repositories;


import com.courseshubbackend.pojos.User;

public interface UserRepository {
    User getUserByUsername(String username);
}