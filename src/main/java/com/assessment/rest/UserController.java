package com.assessment.rest;

import com.assessment.entity.h2.User;
import com.assessment.model.MySqlUserModel;
import com.assessment.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/mysql/{id}")
    public ResponseEntity<MySqlUserModel> getMySQLUser(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.getMySQLUserById(id));
    }

    @PostMapping("/mysql")
    public ResponseEntity<MySqlUserModel> createUser(@Valid @RequestBody MySqlUserModel  mySqlUserModel){

        MySqlUserModel mySQLUser = userService.createMySQLUser(mySqlUserModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(mySQLUser);
    }

}
