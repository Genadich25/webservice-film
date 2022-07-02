package com.webservicewithapi.webservicefilm.controllers;

import com.webservicewithapi.webservicefilm.exceptions.BadRequestException;
import com.webservicewithapi.webservicefilm.models.User;
import com.webservicewithapi.webservicefilm.services.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl service) {
        this.userService = service;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<User> registerUser(@RequestParam String username,
                                       @RequestParam String email,
                                       @RequestParam(required = false) String name){
        return ResponseEntity.ok(userService.registerUser(username, email, name));
    }

    @GetMapping(value = "/get-info-user")
    public ResponseEntity<User> getInfoUser(@RequestHeader(name = "User-Id", required = true) Long userId){
        User user = userService.getInfoUser(userId);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping(value = "/edit-user")
    public ResponseEntity<User> editUser(@RequestHeader(name = "User-Id", required = true) Long userId,
                                         @RequestParam(required = false) String username,
                                         @RequestParam(required = false) String name){
        if(username == null && name == null) {
            throw new BadRequestException();
        }

        return ResponseEntity.ok(userService.editUser(userId, username, name));
    }

    @DeleteMapping(value = "/remove-user")
    public ResponseEntity removeUser(@RequestHeader(name = "User-Id", required = true) Long userId){
        userService.removeUser(userId);
        return ResponseEntity.ok().build();
    }

}
