package com.opticalarc.secure_web_app.controller;

import com.opticalarc.secure_web_app.dto.ApiResponse;
import com.opticalarc.secure_web_app.dto.UserDTO;
import com.opticalarc.secure_web_app.entity.User;
import com.opticalarc.secure_web_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO){
        UserDTO savedUser = userService.addUser(userDTO);
        return new ResponseEntity<UserDTO>(savedUser,HttpStatus.CREATED);
    }


    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }


    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId){
        UserDTO userDTO = userService.getUserById(userId);
        return new ResponseEntity<UserDTO>(userDTO,HttpStatus.OK);
    }


    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO){
        UserDTO updatedUser = userService.updateUser(userId, userDTO);
        return new ResponseEntity<UserDTO>(updatedUser, HttpStatus.OK);
    }


    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User with id " + userId + " deleted successfully",true),HttpStatus.OK);
    }

    @GetMapping("/admin")
    public ResponseEntity<String> getAdminPage(){
        return ResponseEntity.ok("Welcome to ADMIN page!!!");
    }

    @GetMapping("/normalUser")
    public ResponseEntity<String> getUserPage(){
        return ResponseEntity.ok("Welcome to USER page!!!");
    }

}