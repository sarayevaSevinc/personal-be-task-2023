package com.sarayeva.cybercubebetask.controller;

import com.sarayeva.cybercubebetask.dto.UserDto;
import com.sarayeva.cybercubebetask.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserDto> retrieveUser(@RequestHeader("id") Long id) {
        UserDto user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto userRequest) {
        UserDto user = userService.addUser(userRequest);
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestHeader("id") Long id, @RequestBody UserDto userRequest) {
        UserDto user = userService.updateUser(userRequest, id);
        return ResponseEntity.ok(user);
    }
}
