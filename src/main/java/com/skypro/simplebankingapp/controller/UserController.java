package com.skypro.simplebankingapp.controller;
import com.skypro.simplebankingapp.dto.UserDTO;
import com.skypro.simplebankingapp.dto.request.ChangePasswordRequest;
import com.skypro.simplebankingapp.dto.request.CreateUserRequest;
import com.skypro.simplebankingapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDTO createUser (@RequestBody CreateUserRequest createUserRequest) {
        return userService.addUser(createUserRequest);
    }

    @GetMapping
    public Collection<UserDTO> getAllUsers () {
        return this.userService.getAllUser();
    }

    @PostMapping ("/changePassword")
    public void changePassword (@RequestBody ChangePasswordRequest changePasswordRequest) {
       this.userService.updatePassword("", changePasswordRequest.oldPassword(), changePasswordRequest.newPassword());
       ResponseEntity.accepted().build();
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable ("username") String userName) {
        this.userService.removeUser(userName);
        return ResponseEntity.noContent().build();
    }

}
