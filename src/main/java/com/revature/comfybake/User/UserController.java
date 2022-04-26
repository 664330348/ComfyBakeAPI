package com.revature.comfybake.User;

import com.revature.comfybake.User.dtos.NewUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Register a new user as a customer
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = "application/json", consumes = "application/json")
    public HashMap<String, Object> register(@RequestBody NewUserRequest newUserRequest) {
        HashMap<String, Object> userList = new HashMap<String, Object>();

        User newUser = userService.register(newUserRequest);
       // UserResponse userResponse = new UserResponse(newUser);

        userList.put("endpoint", " /register");
        userList.put("status", "UP");
        //userList.put("providedValues", userResponse);

        return userList;
    }
}
