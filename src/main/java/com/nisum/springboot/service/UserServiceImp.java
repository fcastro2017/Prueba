package com.nisum.springboot.service;

import com.nisum.springboot.model.User;
import com.nisum.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository ;

    @Override
    public void createUser(User user) {
        user.getPhones().forEach(phone -> {
            phone.setUser(user);
        });
        userRepository.save(user);

    }
    @Override
    public List<User> findUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }
}
