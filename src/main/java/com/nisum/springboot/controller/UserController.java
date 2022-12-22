package com.nisum.springboot.controller;

import com.nisum.springboot.dto.UserDto;
import com.nisum.springboot.model.User;
import com.nisum.springboot.repository.UserRepository;
import com.nisum.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    // build create user REST API
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody User user) {


        UserDto userDto = new UserDto();

        UUID uuid = UUID.randomUUID();
        userDto.setId(String.valueOf(uuid));
        List<User> users = userService.findUsers();
        users.forEach(user1 -> {
            userDto.setCreated(String.valueOf(user1.getCreated()));
            userDto.setModified(String.valueOf(user1.getModified()));

            if (user.getEmail().equals(user1.getEmail())) {
                try {
                    throw new Exception("correo ya registrado");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
            else{
                userDto.setLastLogin(String.valueOf(user1.getCreated()));
            }
        });
        userDto.setToken(String.valueOf(uuid));
        userDto.setIsActive(true);
        userService.createUser(user);

        return new ResponseEntity<UserDto>(userDto, HttpStatus.CREATED);
    }

    @GetMapping
    public List<User> findUsers() {
        List<User> users = userService.findUsers();
        return users;
    }
}
