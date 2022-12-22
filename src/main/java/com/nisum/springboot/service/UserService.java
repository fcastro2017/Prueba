package com.nisum.springboot.service;

import com.nisum.springboot.dto.UserDto;
import com.nisum.springboot.model.User;

import java.util.List;

public interface UserService {

    public void createUser(User user);

    public List<User> findUsers();
}
